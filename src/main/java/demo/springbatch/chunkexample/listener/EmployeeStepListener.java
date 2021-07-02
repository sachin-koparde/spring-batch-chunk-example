package demo.springbatch.chunkexample.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EmployeeStepListener implements StepExecutionListener {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void beforeStep(StepExecution stepExecution) {

        logger.info("Step execution started. Step Name: " + stepExecution.getStepName());

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        logger.info("Step execution completed. Step Name: " + stepExecution.getStepName());

        return ExitStatus.COMPLETED;
    }
}
