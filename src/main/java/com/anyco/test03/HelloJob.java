package com.anyco.test03;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {

    // ��ʽ����getter��setter��ȡ
    // ��Ա���� �� ���������keyһ��
    private String message;
    private Float floatJobValue;
    private Double doubleTriggerValue;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Float getFloatJobValue() {
        return floatJobValue;
    }
    public void setFloatJobValue(Float floatJobValue) {
        this.floatJobValue = floatJobValue;
    }
    public Double getDoubleTriggerValue() {
        return doubleTriggerValue;
    }
    public void setDoubleTriggerValue(Double doubleTriggerValue) {
        this.doubleTriggerValue = doubleTriggerValue;
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // ��ӡ��ǰ��ִ��ʱ�䣬��ʽΪ2017-01-01 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is : " + sf.format(date));

        // ��д�����ҵ���߼�
        //System.out.println("Hello World!");

        JobKey key = context.getJobDetail().getKey();
        System.out.println("My name and group are : " + key.getName() + " : " + key.getGroup());

        TriggerKey trkey = context.getTrigger().getKey();
        System.out.println("My Trigger name and group are : " + trkey.getName() + " : " + trkey.getGroup());

        // ��ʽһ��Map��ֱ��  ��ȡ�Զ������
        JobDataMap jdataMap = context.getJobDetail().getJobDataMap();
        JobDataMap tdataMap = context.getTrigger().getJobDataMap();
        String jobMsg = jdataMap.getString("message");
        Float jobFloatValue = jdataMap.getFloat("floatJobValue");

        String triMsg = tdataMap.getString("message");
        Double triDoubleValue = tdataMap.getDouble("doubleTriggerValue");

        System.out.println("jobMsg is : " + jobMsg);
        System.out.println("jobFloatValue is : " + jobFloatValue);
        System.out.println("triMsg is : " + triMsg);
        System.out.println("triDoubleValue is : " + triDoubleValue);

        // ��ʽһ��Map��ֱ�ӻ�ȡ ��ȡ�Զ������
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        jobMsg = jobDataMap.getString("message");
        jobFloatValue = jobDataMap.getFloat("floatJobValue");

        triMsg = jobDataMap.getString("message");
        triDoubleValue = jobDataMap.getDouble("doubleTriggerValue");

        System.out.println("jobMsg is : " + jobMsg);
        System.out.println("jobFloatValue is : " + jobFloatValue);
        System.out.println("triMsg is : " + triMsg);
        System.out.println("triDoubleValue is : " + triDoubleValue);

        System.out.println("============================");

        // ��ʽ����getter��setter��ȡ
        System.out.println("message is : " + this.message);
        System.out.println("jobFloatValue is : " + this.floatJobValue);
        System.out.println("triDoubleValue is : " + this.doubleTriggerValue);
    }

}
