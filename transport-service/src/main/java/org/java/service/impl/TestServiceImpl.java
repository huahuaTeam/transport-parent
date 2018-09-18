package org.java.service.impl;

import org.java.mapper.xu.TestMapper;
import org.java.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper mapper;

    @Override
    public Map<String, Object> selUser(String login, String pwd) {
        System.out.println(mapper.selAll(login,pwd)+"============");
        return mapper.selAll(login,pwd);
    }
}
