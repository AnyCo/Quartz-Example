package com.anyco.test02;

import com.anyco.test01.HelloJob;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * ��д ���������
 *
 */
public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {

        // ����һ�� JobDetail ʵ��������ʵ���� HelloJob ʵ����
        JobDetail jobDeatil = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob", "jobgroup1")// �����ʶ��
                .build();

        System.out.println("jobDetail's name : " + jobDeatil.getKey().getName());
        System.out.println("jobDetail's group : " + jobDeatil.getKey().getGroup());
        System.out.println("jobDetail's jobClass : " + jobDeatil.getJobClass().getName());

        // ����һ�� Trigger ʵ��������� job ����ִ�У�����ÿ�������ظ�ִ��һ�Σ�ֱ����Զ
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "trigroup1")// �����ʶ��
                .startNow()// ��������ִ��
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

        // ��ӡ��ǰ��ִ��ʱ�䣬��ʽΪ2017-01-01 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time Is : " + sf.format(date));
    }

}

