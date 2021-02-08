package framework.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Job2 implements Job {
	private static final Logger logger = LoggerFactory.getLogger(Job2.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("job2运行了");
	}
}
