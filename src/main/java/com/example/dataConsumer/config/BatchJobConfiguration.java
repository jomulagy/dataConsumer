package com.example.dataConsumer.config;

import com.example.dataConsumer.batch.StreamingTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final StreamingTasklet streamingTasklet;

    public BatchJobConfiguration(JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            StreamingTasklet streamingTasklet) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.streamingTasklet = streamingTasklet;
    }

    @Bean
    public Job streamingJob() {
        return new JobBuilder("streamingJob", jobRepository)
                .start(streamingStep())
                .build();
    }

    @Bean
    public Step streamingStep() {
        return new StepBuilder("streamingStep", jobRepository)
                .tasklet(streamingTasklet, transactionManager)
                .build();
    }
}
