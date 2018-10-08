package org.java.service;

import java.util.List;
import java.util.Map;

public interface dispatchingService {
    public List<Map<String,Object>> getorder(String id);

    public List<Map<String,Object>> getorderparticulars(String id);
    public  void updatedelivery(String type,String id);
    public  void updatedeliveryss( String type, String id);
    public List<Map<String,Object>> getnoorder(String id);
    public List<Map<String,Object>> getorderparticularsno(String id);
    public  void updateorder(String type,String id);
    public List<Map<String,Object>> seleteuser(String bid);
    public List<Map<String,Object>> selectorder(String bid);
    public  void addgoods(String uid,String oid);
    public List<Map<String,Object>> selectbid();
    public List<Map<String,Object>> selectreject();
    public void  delegoods(String id);
}
