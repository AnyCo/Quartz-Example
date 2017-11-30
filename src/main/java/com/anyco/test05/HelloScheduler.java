package com.anyco.test05;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time Is : " + sf.format(date));

        // ����һ�� JobDetail ʵ��������ʵ���� HelloJob ʵ����
        JobDetail jobDeatil = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob", "jobgroup1")// �����ʶ��
                .build();

        // ��ȡ���뵱ǰʱ��4����֮��ľ���ʱ��
        date.setTime(date.getTime() + 4000);
        // ��ȡ���뵱ǰʱ��6����֮��ľ���ʱ��
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000);

        // ���뵱ǰʱ��4���Ӻ��״�ִ������֮��ÿ��2�����ظ�ִ��һ������
        // ���뵱ǰʱ��6����֮��Ϊֹ
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "trigroup1")// �����ʶ��
                .startAt(date)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))
                .build();

        // ���� Scheduler ʵ��
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.scheduleJob(jobDeatil, trigger);
        scheduler.start();
    }

}

