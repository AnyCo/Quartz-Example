package com.anyco.test01;

import com.anyco.ifevetest.test01.DumbJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SimpleExample {

    public void run() throws Exception {
        System.out.println("------- 初始化 ----------------------");

        // 首先要实例化scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        System.out.println("------- 初始化完成 -----------");


        // 获取给定时间的下一个完整分钟的时间，例如给定时间 08:13:54 则返回 08:14:00
        Date runTime = DateBuilder.evenMinuteDate(new Date());

        System.out.println("------- Job安排 -------------------");

        // 获取job实例
        JobDetail job = JobBuilder.newJob(DumbJob.class).withIdentity("job1", "group1").build();

        // 在下一个完整分钟触发运行
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        // 告诉quartz使用某个trigger执行某个job
        scheduler.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " 将会运行于: " + runTime);

        // 启动scheduler
        scheduler.start();

        System.out.println("------- 开始安排 -----------------");

        System.out.println("------- 等待10秒 -------------");
        Thread.sleep(10L * 1000L);

        // 关闭scheduler
        System.out.println("------- 关闭 ---------------------");
        scheduler.shutdown(true);
        System.out.println("------- 关闭完成 -----------------");

    }

    public static void main(String args[]) {
        SimpleExample se = new SimpleExample();
        try {
            se.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
