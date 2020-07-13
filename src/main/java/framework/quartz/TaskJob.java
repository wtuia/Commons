package framework.quartz;

import org.apache.logging.log4j.core.config.Configurator;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;

public class TaskJob implements Job {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskJob.class);


	public static void main(String[] args) {
		Configurator.initialize("log4j2.xml",System.getProperty("user.dir") + File.separator + "log4j2.xml");
		new TaskJob().execute(null);
	}

	@Override
	public void execute(JobExecutionContext context) {
	}

	public static String expression() {
		return "0 0 0 * * ?";
	}

}
