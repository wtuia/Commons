package framework.quartz.listener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.listeners.SchedulerListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySchedulerListener extends SchedulerListenerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MySchedulerListener.class);
	
	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		logger.info("{}:triggerPaused", triggerKey.getName());
	}
	
	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		logger.info("{}:triggerResumed", triggerKey.getName());
	}
	
	
	@Override
	public void jobAdded(JobDetail jobDetail) {
		logger.info("{}:jobAdded", jobDetail.getKey().getName());
	}
	
	@Override
	public void jobDeleted(JobKey jobKey) {
		logger.info("{}:jobDeleted", jobKey.getName());
	}
	
	@Override
	public void jobPaused(JobKey jobKey) {
		logger.info("{}:jobPaused", jobKey.getName());
	}
	
	@Override
	public void jobsPaused(String jobGroup) {
		logger.info("{}:jobsPaused", jobGroup);
	}
	
	@Override
	public void jobResumed(JobKey jobKey) {
		logger.info("{}:jobResumed", jobKey.getName());
	}
	
	@Override
	public void jobsResumed(String jobGroup) {
		logger.info("{}:jobsResumed", jobGroup);
	}
	
	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		logger.error("scheduler error:{};",msg, cause);
	}
	
	@Override
	public void schedulerInStandbyMode() {
		logger.info("schedulerInStandbyMode");
	}
	
	@Override
	public void schedulerStarted() {
		logger.info("schedulerStarted");
	}
	
	
	@Override
	public void schedulerShutdown() {
		logger.info("scheduler已停止");
	}
	
}
