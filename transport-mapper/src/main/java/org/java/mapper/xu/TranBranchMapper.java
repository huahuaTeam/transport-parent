package org.java.mapper.xu;

import com.sun.org.apache.bcel.internal.generic.I2B;
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
    //查询出所有没有站点的派送员
    List<Map<String,Object>> allocationPeople();
    //没有站点的派送员个数
    Integer allocationPeopleCount();
    //查询站点有员工的员工个数
    List<Map<String,Object>> branchPeople();
    //没有员工的站点
    List<Map<String,Object>> branchNotPeople();
    //修改员工的站点id
    void updateBid(@Param("bId")String bId,@Param("userId")String userId);
    //查询网点的员工
    List<Map<String,Object>> branchPeopleById(@Param("bId")String bId);
    //查询当前网点员工个数
    int selBranchPeopleCount(@Param("bId")String bId);

}