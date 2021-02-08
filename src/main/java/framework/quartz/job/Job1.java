package framework.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Job1 implements Job {
	private static final Logger logger = LoggerFactory.getLogger(Job1.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("job1运行了");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("",e);
		}
	}
}
