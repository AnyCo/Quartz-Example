package com.anyco.test01;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements Job {

    /**
     * ��Ҫһ���޲ε�public�����Ա�scheduler������Ҫ��ʱ�����ʵ�������class.
     */
    public HelloJob() {
    }


    /**
     * Ҫִ�еĴ���
     *
     * @param context JobExecutionContext
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello World! - " + new Date());
    }
}
