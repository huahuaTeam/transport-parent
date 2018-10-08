package org.java.mapper.xu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.java.entity.TranBranch;

import java.util.List;
import java.util.Map;

@Mapper
public interface TranBranchMapper {
    int deleteByPrimaryKey(String bId);

    int insert(TranBranch record);

    int insertSelective(TranBranch record);

    TranBranch selectByPrimaryKey(String bId);

    int updateByPrimaryKeySelective(TranBranch record);

    int updateByPrimaryKey(TranBranch record);
    //查询所有网点
    List<TranBranch> selAll();
    //根据地区查询网点
    List<TranBranch> likeBranch(@Param("place") String place);
    //查询每个网点的详细信息
    List<Map<String,Object>> branchDetail();
    //查询单个网点
    TranBranch oneBranch(@Param("id")String id);
    //单个网点的揽件信息
    Map<String,Object> branchDetailById(@Param("id")String id);
}