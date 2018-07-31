package common.jobManager;

import bean.SchedularJob;
import bean.SchedularJobWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import support.ApplicationContextHelper;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2018/7/30
 */
public class QuartzScheduleManagerImpl implements QuartzScheduleManager{

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    /**
     * 默认任务组名称
     */
    private final static String DEFAULT_JOB_GROUP = "default_job_group";

    /**
     * 默认触发器组名称
     */
    private final static String DEFAULT_TRIGGER_GROUP = "default_trigger_group";

    private List<SchedularJob> jobs = Lists.newArrayListWithExpectedSize(1);

    public void setJobs(Resource path){
        Constructor constructor = new Constructor(SchedularJobWrapper.class);
        TypeDescription typeDescription = new TypeDescription(SchedularJobWrapper.class);
        typeDescription.putListPropertyType("jobs",SchedularJob.class);
        constructor.addTypeDescription(typeDescription);
        Yaml yaml = new Yaml(constructor);
        try {
            SchedularJobWrapper schedularJobWrapper = yaml.loadAs(IOUtils.toString(path.getURI(), "utf-8"), SchedularJobWrapper.class);
            this.jobs = schedularJobWrapper.getJobs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        if(! CollectionUtils.isEmpty(jobs)){
            for (SchedularJob job : jobs){
                addJob(job);
            }
        }
    }
    @Override
    public void start() {

    }

    @Override
    public void shutDown() {

    }

    @Override
    public boolean addJob(SchedularJob schedularJob) {
        String clazzName = schedularJob.getClazz();
        List args = schedularJob.getArgs();
        String method = schedularJob.getMethod();
        String time = schedularJob.getTime();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Class<?> clazz = null;
        Object jobBean = null;
        try {
            clazz = Class.forName(clazzName);
            jobBean = ApplicationContextHelper.getContext().getBean(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        MethodInvokingJobDetailFactoryBean factoryBean = BeanUtils.instantiate(MethodInvokingJobDetailFactoryBean.class);
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(factoryBean);
        beanWrapper.setPropertyValue("targetObject",jobBean);
        beanWrapper.setPropertyValue("targetMethod",method);
        beanWrapper.setPropertyValue("arguments",args.toArray());

        try {
            factoryBean.prepare();
            JobDetail jobDetail = JobBuilder.newJob(MethodInvokingJobDetailFactoryBean.MethodInvokingJob.class)
                    .withIdentity(schedularJob.getName(), DEFAULT_JOB_GROUP)
                    .storeDurably(true).build();
            jobDetail.getJobDataMap().put("methodInvoker",factoryBean);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(newTriggerId(), DEFAULT_JOB_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule(time))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * new trigger id
     *
     * @return
     */
    private Random random = new Random();
    private String newTriggerId() {
        long r = random.nextLong();
        if (r < 0) {
            r = -r;
        }
        return "MT_"
                + Long.toString(r, 30 + (int) (System.currentTimeMillis() % 7));
    }
}
