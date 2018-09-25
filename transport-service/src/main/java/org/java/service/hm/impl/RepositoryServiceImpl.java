package org.java.service.hm.impl;

import org.java.mapper.hm.RepositoryMapper;
import org.java.service.hm.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepositoryServiceImpl implements RepositoryService{
    @Autowired
    private RepositoryMapper mapper;

    @Override
    public List<Map<String, Object>> getAllRepository() throws Exception {
        return mapper.getAllResponse();
    }

    @Override
    public List<Map<String, Object>> findByName(String name) throws Exception {
        return mapper.findByName(name);
    }

    @Override
    public void addRepository(Map<String, String> map) throws Exception {
        mapper.addRepository(map);
    }

    @Override
    public void updStatus(String id, String status) throws Exception {
        mapper.updStatus(id,status);
    }

    @Override
    public Map<String, Object> findByidRep(String rid) throws Exception {
        return mapper.findByidRep(rid);
    }
}
