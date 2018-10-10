package org.java.mapper.zwz;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface dispatchingmapper {
    public List<Map<String,Object>> getorder(String id);
    public List<Map<String,Object>> getorderparticulars(String id);
    public  void updatedelivery(@Param("type") String type,@Param("id")  String id);
    public  void updatedeliveryss(@Param("type") String type,@Param("id")  String id);
    public List<Map<String,Object>> getnoorder(String id);
    public List<Map<String,Object>> getorderparticularsno(String id);
    public  void updateorder(@Param("type") String type,@Param("id")  String id);
    public List<Map<String,Object>> seleteuser(@Param("bid") String bid);
    public List<Map<String,Object>> selectorder(@Param("bid") String bid);
    public  void addgoods(@Param("uid") String uid,@Param("oid")String oid);
    public List<Map<String,Object>> selectbid();
    public List<Map<String,Object>> selectreject();
    public void  delegoods(String id);
}
