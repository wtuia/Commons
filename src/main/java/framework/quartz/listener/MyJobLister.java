package framework.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJobLister extends JobListenerSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(MyJobLister.class);
	
	@Override
	public String getName() {
		return "Job监听器";
	}
	
	/**
	 * 任务执行之前调用
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		logger.info("任务{}准备执行", context.getJobDetail().getKey().getName());
	}
	
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		logger.info("任务{}运行被取消", context.getJobDetail().getKey().getName());
	}
	
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		if(jobException != null) {
			logger.error("",jobException);
		}
		logger.info("任务{}执行完成", context.getJobDetail().getKey().getName());
	}
}
