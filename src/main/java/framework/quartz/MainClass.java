package framework.quartz;

import framework.quartz.job.Job1;
import framework.quartz.job.Job2;
import framework.quartz.listener.MyJobLister;
import framework.quartz.listener.MySchedulerListener;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;

public class MainClass {
	
	public static void main(String[] args) {
			Configurator.initialize("log4j2.xml",
					System.getProperty("user.dir") + File.separator + "log4j2.xml");
			TaskScheduler.getInstance()
					// 超过repeatCount 则删除job 不再执行 否则每天重复执行
					.addDayDailyJob(Job1.class, "job1", "9:00", "10:00", 10, 10)
					.addJob(Job2.class, "job2", "0 11 11 * * ?")
					.addSchedulerListener(new MySchedulerListener())
					.addJobListener(new MyJobLister())
					.start();
	}
}
