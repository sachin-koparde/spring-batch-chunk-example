package demo.springbatch.chunkexample.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EmployeeJobListener implements JobExecutionListener {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void beforeJob(JobExecution jobExecution) {

        logger.info("Job execution started. Job name: " + jobExecution.getJobInstance().getJobName());

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        logger.info("Job execution completed. Job name: " + jobExecution.getJobInstance().getJobName());

    }
}
