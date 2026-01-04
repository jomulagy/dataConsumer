package com.example.dataConsumer.mybatis.mapper;

import com.example.dataConsumer.domain.model.CustomerRecord;
import com.example.dataConsumer.domain.model.OrderLineRecord;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StreamingTestMapper {

    CustomerRecord selectSingleCustomer(Long id);

    OrderLineRecord selectSingleOrderLine(Map<String, Object> key);
}
