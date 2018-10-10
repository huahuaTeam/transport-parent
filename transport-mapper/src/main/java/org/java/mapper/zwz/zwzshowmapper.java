package org.java.mapper.zwz;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface zwzshowmapper {

    public List<Map<String,Object>> selectforpurchase();
    public List<Map<String,Object>> selectpurchaseorder();
    public List<Map<String,Object>> selectenterstorage();
    public List<Map<String,Object>> selectoutstorage();
    public List<Map<String,Object>> selectdispatching();
    public List<Map<String,Object>> selectclientorder();
}
