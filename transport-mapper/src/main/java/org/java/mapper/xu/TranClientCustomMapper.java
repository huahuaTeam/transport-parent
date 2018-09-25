package org.java.mapper.xu;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TranClientCustomMapper {

    @Select("select * from tran_client")
    public List<Map<String,Object>> selAllClient();
}
