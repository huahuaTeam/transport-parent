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

    TranBranch oneBranch(String id);
    //单个网点的揽件信息
    Map<String,Object> branchDetailById(String id);

}
