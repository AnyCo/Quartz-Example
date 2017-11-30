package com.anyco.test04;



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

        // ��ȡ���뵱ǰʱ��3����ʱ��
        date.setTime(date.getTime() + 3000);
        // ��ȡ���뵱ǰʱ��6����ʱ��
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000);

        // ����һ�� Trigger ʵ��������� job ����ִ�У�����ÿ�������ظ�ִ��һ�Σ�ֱ����Զ
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","trigroup1")
                .startAt(date)// ����3���ִ��
                .endAt(endDate)// ����6������
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())// ����ִ��Ƶ��
                .build();

        // ���� Scheduler ʵ��
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();

        // �� JobDetail �� trigger
        scheduler.scheduleJob(jobDeatil, trigger);

        // ִ������
        scheduler.start();
    }

}

