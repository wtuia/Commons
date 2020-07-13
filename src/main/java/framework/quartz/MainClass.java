package framework.quartz;

import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;

// 定时任务
public class MainClass {

	public static void main(String[] args) {
		Configurator.initialize("log4j2.xml",System.getProperty("user.dir") + File.separator + "log4j2.xml");
		TaskScheduler.getInstance()
				.addJob(TaskJob.class, TaskJob.expression())
				.startTask();
	}
}
