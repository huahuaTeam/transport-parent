package org.java.service.xu;

import org.java.entity.TranBranch;

import java.util.List;
import java.util.Map;

public interface BranchService {

    public void addBranch(TranBranch tranBranch);

    public List<TranBranch> selALL();

    public List<TranBranch> likeBranch(String place);

    //查询每个网点的详细信息
    List<Map<String,Object>> branchDetail();
    //查询的单个网点的信息
    TranBranch oneBranch(String id);
    //单个网点的揽件信息
    Map<String,Object> branchDetailById(String id);
    //查询出所有没有站点的派送员
    List<Map<String,Object>> allocationPeople();
    //查询站点有员工的员工个数
    List<Map<String,Object>> branchPeople();
    //没有员工的站点
    List<Map<String,Object>> branchNotPeople();
    //没有站点的派送员个数
    Integer allocationPeopleCount();
    //修改员工的站点id
    void updateBid(String bId,String userId);
    //查询网点的员工
    List<Map<String,Object>> branchPeopleById(String bId);
    //查询当前网点员工个数
    int selBranchPeopleCount(String bId);

}
