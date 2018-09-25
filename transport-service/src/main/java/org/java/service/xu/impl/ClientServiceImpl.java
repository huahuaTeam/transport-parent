package org.java.service.xu.impl;

import org.java.entity.TranClient;
import org.java.mapper.xu.TranClientCustomMapper;
import org.java.mapper.xu.TranClientMapper;
import org.java.service.xu.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private TranClientMapper mapper;

    @Autowired
    private TranClientCustomMapper customMapper;

    @Override
    public void addClient(TranClient client) {
            mapper.insertSelective(client);
    }

    @Override
    public List<Map<String, Object>> selAllClient() {
        return customMapper.selAllClient();
    }
}
