package init;

import common.jobManager.QuartzScheduleManager;
import common.jobManager.QuartzScheduleManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import support.ApplicationContextHelper;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/7/30
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public QuartzScheduleManagerImpl quartzScheduleManager(){
        QuartzScheduleManagerImpl quartzScheduleManager = new QuartzScheduleManagerImpl();
        quartzScheduleManager.setJobs(new PathResource("/properties/quartzjobs.yml"));
        quartzScheduleManager.init();
        return  quartzScheduleManager;
    }

    @Bean
    public SchedulerFactoryBean SchedulerFactoryBean(){
        return new SchedulerFactoryBean();
    }

    @Bean
    public ApplicationContextHelper applicationContextHelper(){
        return new ApplicationContextHelper();
    }
}
