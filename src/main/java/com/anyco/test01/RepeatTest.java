package com.anyco.test01;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RepeatTest {

    public static void main(String args[]) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myjob", "mygroup1").build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "mygroup1").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();


        //实例化scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start(); // 启动scheduler

        java.util.Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //输出当前时间
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
