package org.java.service.hm;

import java.util.List;
import java.util.Map;

public interface RepositoryService {


    List<Map<String,Object>> getAllRepository() throws  Exception;

    List<Map<String,Object>> findByName(String name)throws  Exception;

    void addRepository(Map<String,String> map) throws Exception;

    void updStatus(String id,String status) throws  Exception;

    Map<String,Object> findByidRep(String rid) throws  Exception;


}
