package com.anyco.test06;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // ��ӡ��ǰ��ִ��ʱ�䣬��ʽΪ2017-01-01 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is : " + sf.format(date));
        System.out.println("Hello World");
    }

}