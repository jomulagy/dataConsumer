package com.example.dataConsumer.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/streaming")
public class StreamingController {

    private final JobLauncher jobLauncher;
    private final Job streamingJob;

    public StreamingController(JobLauncher jobLauncher, Job streamingJob) {
        this.jobLauncher = jobLauncher;
        this.streamingJob = streamingJob;
    }

    @PostMapping("/tasklet")
    public ResponseEntity<String> runStreamingTasklet() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(streamingJob, jobParameters);
        String message = "Streaming tasklet executed with status: " + execution.getStatus();
        return ResponseEntity.ok(message);
    }
}
