package common.jobManager;

import bean.SchedularJob;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/7/30
 */
public interface QuartzScheduleManager {

    void start();

    void shutDown();

    boolean addJob(SchedularJob schedularJob);
}
