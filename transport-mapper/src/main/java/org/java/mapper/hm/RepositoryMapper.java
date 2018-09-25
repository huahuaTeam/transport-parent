package org.java.mapper.hm;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepositoryMapper {

    List<Map<String,Object>> getAllResponse()throws  Exception;

    List<Map<String,Object>> findByName(@Param("rname") String name)throws  Exception;

    void addRepository(Map<String,String> map) throws Exception;

    void updStatus(@Param("rid") String id,@Param("status") String status) throws  Exception;

    Map<String,Object> findByidRep(@Param("rid") String rid) throws  Exception;

}
