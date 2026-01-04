package com.example.dataConsumer.mybatis.mapper;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.model.OrderLineEntity;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StreamingTestMapper {

    CustomerEntity selectSingleCustomer(Long id);

    OrderLineEntity selectSingleOrderLine(Map<String, Object> key);
}
