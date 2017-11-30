package com.anyco.test01;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements Job {

    /**
     * 需要一个无参的public构造以便scheduler在它需要的时候可以实例化这个class.
     */
    public HelloJob() {
    }


    /**
     * 要执行的代码
     *
     * @param context JobExecutionContext
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello World! - " + new Date());
    }
}
