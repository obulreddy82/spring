package com.rajtechnologies.springbootbatchfile.listener;

import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution){
       System.out.println("Job is starting");
    }

    @Override
    public void afterJob(JobExecution jobExecution){
        System.out.println("Job is completed");
        System.out.println(
                "Status: " + jobExecution.getStatus()
        );
    }



}
