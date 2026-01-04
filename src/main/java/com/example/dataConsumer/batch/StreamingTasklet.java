package com.example.dataConsumer.batch;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.model.OrderLineEntity;
import com.example.dataConsumer.streaming.RowConsumer;
import com.example.dataConsumer.usecase.StreamingUseCase;
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
        RowConsumer<CustomerEntity> customerConsumer = row -> {
            // replace with domain logic; placeholder avoids accumulating results
        };
        RowConsumer<OrderLineEntity> orderLineConsumer = row -> {
            // replace with domain logic; placeholder avoids accumulating results
        };

        streamingUseCase.executeUseCase(2000, customerConsumer, orderLineConsumer);
        return RepeatStatus.FINISHED;
    }
}
