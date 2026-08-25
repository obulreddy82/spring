package com.rajtechnologies.springbootbatchfile.config;

import com.rajtechnologies.springbootbatchfile.listener.JobCompletionListener;
import com.rajtechnologies.springbootbatchfile.model.Customer;
import com.rajtechnologies.springbootbatchfile.processor.CustomerProcessor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.xml.StaxEventItemReader;
import org.springframework.batch.infrastructure.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {
    //Item Reader
//    @Bean
//    public FlatFileItemReader<Customer> customerReader(){
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerReader")
//                .resource(new ClassPathResource("customers.csv"))
//                .linesToSkip(1)
//                .delimited()
//                .names(new String[]{"id", "name", "email", "age"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{setTargetType(Customer.class);}})
//                .build();
//    }

//    @Bean
//    public JsonItemReader<Customer> customerReader(){
//        return new JsonItemReaderBuilder<Customer>()
//                .name("customerReader")
//                .resource(new ClassPathResource("customers.json"))
//                .jsonObjectReader(new JacksonJsonObjectReader<>(Customer.class))
//                .build();
//    }

//    //Dynamic Code
//    @Bean
//    @StepScope
//    public JsonItemReader<Customer> customerReader(
//            @Value("#{jobParameters['fileName']}") String fileName
//    ){
//
//        return new JsonItemReaderBuilder<Customer>()
//                .name("customerReader")
//                .resource(new ClassPathResource(fileName))
//                .jsonObjectReader(new JacksonJsonObjectReader<>(Customer.class))
//                .build();
//    }

    @Bean
    public StaxEventItemReader<Customer> customerReader() {
        return new StaxEventItemReaderBuilder<Customer>().name("customerXmlReader")
                .resource(new ClassPathResource("customers.xml"))
                .addFragmentRootElements("customer")
                .unmarshaller(customerUnmarshaller())
                .build();
    }

    @Bean
    public Jaxb2Marshaller customerUnmarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Customer.class);
        return marshaller;
    }

    @Bean
    public CustomerProcessor customerProcessor() {
        return new CustomerProcessor();
    }

    //Item writer
    @Bean
    public JdbcBatchItemWriter<Customer> customerWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Customer> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("insert into customer(id, name, email, age) values(:id, :name, :email, :age)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(BeanPropertySqlParameterSource::new);
        return jdbcBatchItemWriter;
    }

    @Bean
    public Step customerStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            StaxEventItemReader<Customer> customerReader,
            CustomerProcessor customerProcessor,
            JdbcBatchItemWriter<Customer> customerWriter) {
        return new StepBuilder("customerStep", jobRepository)
                .<Customer, Customer>chunk(10)
                .reader(customerReader)
                .processor(customerProcessor)
                .writer(customerWriter)
                .skip(IllegalArgumentException.class)
                .skipLimit(10)
                .build();
    }

    @Bean
    public Job customerJob(JobRepository jobRepository,
                           Step customerStep,
                           JobCompletionListener jobCompletionListener) {
        return new JobBuilder("customerJob", jobRepository)
                .listener(jobCompletionListener)
                .start(customerStep).build();
    }


}
