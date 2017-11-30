package com.anyco.test01;

import com.anyco.ifevetest.test01.DumbJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SimpleExample {

    public void run() throws Exception {
        System.out.println("------- ��ʼ�� ----------------------");

        // ����Ҫʵ����scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        System.out.println("------- ��ʼ����� -----------");


        // ��ȡ����ʱ�����һ���������ӵ�ʱ�䣬�������ʱ�� 08:13:54 �򷵻� 08:14:00
        Date runTime = DateBuilder.evenMinuteDate(new Date());

        System.out.println("------- Job���� -------------------");

        // ��ȡjobʵ��
        JobDetail job = JobBuilder.newJob(DumbJob.class).withIdentity("job1", "group1").build();

        // ����һ���������Ӵ�������
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        // ����quartzʹ��ĳ��triggerִ��ĳ��job
        scheduler.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " ����������: " + runTime);

        // ����scheduler
        scheduler.start();

        System.out.println("------- ��ʼ���� -----------------");

        System.out.println("------- �ȴ�10�� -------------");
        Thread.sleep(10L * 1000L);

        // �ر�scheduler
        System.out.println("------- �ر� ---------------------");
        scheduler.shutdown(true);
        System.out.println("------- �ر���� -----------------");

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
