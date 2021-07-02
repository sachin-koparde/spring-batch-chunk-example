package demo.springbatch.chunkexample.config;

import demo.springbatch.chunkexample.listener.EmployeeJobListener;
import demo.springbatch.chunkexample.mapper.EmployeeFieldSetMapper;
import demo.springbatch.chunkexample.model.Employee;
import demo.springbatch.chunkexample.processor.EmployeeProcessor;
import demo.springbatch.chunkexample.listener.EmployeeStepListener;
import demo.springbatch.chunkexample.writer.EmployeeWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EmployeeJobListener jobListener;

    @Autowired
    private EmployeeStepListener stepListener;

    private final String[] employeeData = new String[]{"employeeId", "employeeName", "employeeDesignation"};

    private final String QUERY_INSERT = "INSERT INTO employees " +
            "(employee_id, employee_Name, employee_Designation) VALUES (:employeeId, :employeeName, :employeeDesignation)";

    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("EmployeeItemReader")
                .resource(new ClassPathResource("csv/employees.csv"))
                .linesToSkip(1)
                .delimited()
                .names(employeeData)
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Employee.class);
                }}).build();
    }

    @Bean
    public LineMapper<Employee> lineMapper() {
        final DefaultLineMapper<Employee> defaultLineMapper = new
                DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(employeeData);

        final EmployeeFieldSetMapper fieldSetMapper = new EmployeeFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public EmployeeProcessor processor() {
        return new EmployeeProcessor();
    }

    @Bean
    public EmployeeWriter writer() {
        return new EmployeeWriter();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("chunkProcessingStep")
                .listener(stepListener)
                .<Employee, Employee>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job(Step step) {
        return jobBuilderFactory.get("chunkProcessingJob")
                .listener(jobListener)
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();

    }

}
