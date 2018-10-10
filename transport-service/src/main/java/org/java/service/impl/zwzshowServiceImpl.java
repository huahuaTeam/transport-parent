package org.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.java.service.zwzshowService;
import org.java.mapper.zwz.zwzshowmapper;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class zwzshowServiceImpl implements  zwzshowService {

    @Autowired
    zwzshowmapper mapper;

    public List<Map<String,Object>> selectforpurchase(){  return mapper.selectforpurchase();  };
    public List<Map<String,Object>> selectpurchaseorder(){ return  mapper.selectpurchaseorder();};
    public List<Map<String,Object>> selectenterstorage(){ return  mapper.selectenterstorage();};
    public List<Map<String,Object>> selectoutstorage(){ return  mapper.selectoutstorage();};
    public List<Map<String,Object>> selectdispatching(){ return  mapper.selectdispatching();};
    public List<Map<String,Object>> selectclientorder(){ return  mapper.selectclientorder();};


}
