package org.java.service.impl;


import org.java.service.dispatchingService;
import org.java.mapper.zwz.dispatchingmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class dispatchingServiceImpl   implements dispatchingService  {

    @Autowired
    dispatchingmapper mapper;


    public List<Map<String,Object>> getorderparticulars(String id){
        return mapper.getorderparticulars(id);
    };
    public List<Map<String,Object>> getorder(String id){return mapper.getorder(id);};
    public  void updatedelivery(String type,String id){mapper.updatedelivery(type,id); };
    public  void updatedeliveryss(String type,String id){mapper.updatedeliveryss(type,id); };
    public List<Map<String,Object>> getnoorder(String id){return mapper.getnoorder(id);};
    public List<Map<String,Object>> getorderparticularsno(String id){ return mapper.getorderparticularsno(id); };
    public  void updateorder(String type,String id){ mapper.updateorder(type,id); };
    public List<Map<String,Object>> seleteuser(String bid){return mapper.seleteuser(  bid);};
    public List<Map<String,Object>> selectorder(String bid){return mapper.selectorder(bid);};
    public  void addgoods(String uid,String oid){mapper.addgoods(uid, oid);};
    public List<Map<String,Object>> selectbid(){return mapper.selectbid();};
    public List<Map<String,Object>> selectreject(){ return  mapper.selectreject();  };
    public void  delegoods(String id){  mapper.delegoods(id);};
}
