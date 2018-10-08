package org.java.service.xu.impl;

import org.java.entity.TranOrder;
import org.java.mapper.xu.TranOrderMapper;
import org.java.service.xu.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private TranOrderMapper mapper;

    @Override
    public int updateByPrimaryKeySelective(TranOrder record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public TranOrder selectByPrimaryKey(String orderId) {
        return mapper.selectByPrimaryKey(orderId);
    }
}
