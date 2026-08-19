package com.rajtechnologies.springbootbatchfile.config;

import com.rajtechnologies.springbootbatchfile.listener.JobCompletionListener;
import com.rajtechnologies.springbootbatchfile.model.Employee;
import com.rajtechnologies.springbootbatchfile.processor.EmployeeProcessor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchEmployeeConfig {

    @Bean
    public FlatFileItemReader<Employee> employeeReader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeReader")
                .resource(new ClassPathResource("employees.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[]{"id","name","age","email","phone"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{setTargetType(Employee.class);}})
                .build();

    }
    @Bean
    public EmployeeProcessor employeeProcessor() {
        return new EmployeeProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Employee> employeeWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Employee> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("insert into employee(id, name, age, email, phone) values(:id, :name, :age, :email,:phone)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(BeanPropertySqlParameterSource::new);
        return jdbcBatchItemWriter;
    }


    @Bean
    public Step employeeStep(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager,
                             FlatFileItemReader<Employee> employeeReader,
                             EmployeeProcessor employeeProcessor,
                             JdbcBatchItemWriter<Employee> employeeWriter
    ) {
        return new StepBuilder("employeeStep", jobRepository)
                .<Employee, Employee>chunk(5)
                .reader(employeeReader)
                .processor(employeeProcessor)
                .writer(employeeWriter)
                .skip(IllegalArgumentException.class)
                .skipLimit(5)
                .build();
    }


    @Bean
    public Job employeeJob(JobRepository jobRepository,
                           Step employeeStep,
                           JobCompletionListener jobCompletionListener) {
        return new JobBuilder("employeeJob", jobRepository)
                .listener(jobCompletionListener)
                .start(employeeStep).build();
    }
}
