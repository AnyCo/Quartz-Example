package com.anyco.test01;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SimpleTriggerExample {

    public static void main(String[] args) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

        // 在下一轮分钟触发运行
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").
                startAt(new Date(System.currentTimeMillis() + 1000)).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
