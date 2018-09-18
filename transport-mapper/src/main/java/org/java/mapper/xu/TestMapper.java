package org.java.mapper.xu;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
   /* @Select("select * from user where user_name =#{login} and user_password =#{pwd}")*/
    public Map<String,Object> selAll(@Param("login") String login ,@Param("pwd") String pwd);
}
