package com.anyco.test06;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time Is : " + sf.format(date));
        // ����һ��JobDetailʵ��������ʵ����HelloJob Class��
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob").build();
        CronTrigger trigger = (CronTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();

        // ����Schedulerʵ��
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        System.out.println("scheduled time is :"
                + sf.format(scheduler.scheduleJob(jobDetail, trigger)));
        //schedulerִ��3������
        Thread.sleep(3000L);
        //shutdown(true)��ʾ�ȴ���������ִ�е�jobִ�����֮���ٹر�scheduler
        //shutdown(false)��shutdown()��ʾֱ�ӹر�scheduler
        scheduler.shutdown(false);
        System.out.println("scheduler is shut down? " + scheduler.isShutdown());
    }

}
