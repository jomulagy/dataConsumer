package com.example.dataConsumer.batch;

import com.example.dataConsumer.streaming.RowConsumer;
import com.example.dataConsumer.usecase.StreamingUseCase;
import java.util.Map;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StreamingTasklet implements Tasklet {

    private final StreamingUseCase streamingUseCase;

    public StreamingTasklet(StreamingUseCase streamingUseCase) {
        this.streamingUseCase = streamingUseCase;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        RowConsumer<Map<String, Object>> loggingConsumer = row -> {
            // replace with domain logic; placeholder avoids accumulating results
        };
        streamingUseCase.streamWithMyBatis("sampleMapper.selectForStreaming", Map.of(), 1000, loggingConsumer);
        return RepeatStatus.FINISHED;
    }
}
