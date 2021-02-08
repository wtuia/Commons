package framework.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
	private Scheduler scheduler;
	private static TaskScheduler taskScheduler;

	private TaskScheduler() {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			logger.error("",e);
		}
	}

	public static TaskScheduler getInstance() {
		if (taskScheduler == null) {
			taskScheduler = new TaskScheduler();
		}
		return taskScheduler;
	}


	/**
     * 添加任务
     * @param taskClass 执行任务
     * @param taskName 任务名称
     * @param triggerExpression 任务执行规则
	 *
     */
    public TaskScheduler addJob(Class<? extends Job> taskClass, String taskName, String triggerExpression) {
	    String className = taskClass.getSimpleName();
	    String group = className + "Group";
        try {
            JobDetail job = JobBuilder.newJob(taskClass)
                        .withIdentity(taskName, group)
                        .build();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                                    .withIdentity(className + "Trigger", group)
                                    .withSchedule(CronScheduleBuilder.cronSchedule(triggerExpression))
                                    .build();
            scheduler.scheduleJob(job, trigger);
            logger.error("添加任务:{}, expression:{}", taskName, triggerExpression);
        } catch (SchedulerException e) {
            logger.error("安排任务失败:{}", taskName, e);
        }
        return this;
    }
	
	/**
	 * 超过repeatCount则停止执行, 否则每天重复执行
	 * @param taskClass 执行任务
	 * @param taskName 任务名称
	 * @param startTime 任务开始时间
	 * @param endTime 任务结束时间
	 * @param interval 重复周期 分
	 * @param repeatCount 重复次数 -1无限重复 0为1次 实际重复 N+1次
	 */
	public TaskScheduler addDayDailyJob(Class<? extends Job> taskClass, String taskName,
	                                    String startTime, String endTime,
	                                    int interval, int repeatCount) {
		String className = taskClass.getSimpleName();
		String group = className + "Group";
		try {
			JobDetail job = JobBuilder.newJob(taskClass).withIdentity(taskName, group).build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(className + "Trigger", group)
					// 开始时间 .startAt(new Date())
					// 结束时间 .endAt(new Date())
					.withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
							// 一天的开始时间
							.startingDailyAt(strTime4TimeOfDay(startTime))
							// 一天的结束时间
							.endingDailyAt(strTime4TimeOfDay(endTime))
							// 任务周期
							.withIntervalInMinutes(interval)
							// 执行次数
							.withRepeatCount(repeatCount))
					.build();
			scheduler.scheduleJob(job, trigger);
			logger.error("添加任务:{}", taskName);
		} catch (SchedulerException e) {
			logger.error("安排任务失败:{}", taskName, e);
		}
		return this;
	}
	
	public TaskScheduler addSchedulerListener(SchedulerListener schedulerListener) {
		try {
			scheduler.getListenerManager().addSchedulerListener(schedulerListener);
		} catch (SchedulerException e) {
			logger.error("",e);
		}
		return this;
	}
	
	public TaskScheduler addJobListener(JobListener jobListener) {
		try {
			scheduler.getListenerManager().addJobListener(jobListener);
		} catch (SchedulerException e) {
			logger.error("",e);
		}
		return this;
	}
	
	public TaskScheduler addTriggerListener(TriggerListener triggerListener) {
		try {
			scheduler.getListenerManager().addTriggerListener(triggerListener);
		} catch (SchedulerException e) {
			logger.error("",e);
		}
		return this;
	}
	
	private TimeOfDay strTime4TimeOfDay(String time) {
		String[] times = time.split(":");
		return TimeOfDay.hourAndMinuteOfDay(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
	}
	
	public void start() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error("",e);
		}
	}
}
