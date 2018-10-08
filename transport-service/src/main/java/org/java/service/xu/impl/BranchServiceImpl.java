package org.java.service.xu.impl;

import org.java.entity.TranBranch;
import org.java.mapper.xu.TranBranchMapper;
import org.java.service.xu.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private TranBranchMapper mapper;

    @Override
    public Map<String, Object> branchDetailById(String id) {
        return mapper.branchDetailById(id);
    }

    @Override
    public TranBranch oneBranch(String id) {
        return mapper.oneBranch(id);
    }

    @Override
    public List<Map<String, Object>> branchDetail() {
        return mapper.branchDetail();
    }

    @Override
    public List<TranBranch> likeBranch(String place) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+mapper.likeBranch(place));
        return mapper.likeBranch(place);
    }

    @Override
    public void addBranch(TranBranch tranBranch) {
        mapper.insertSelective(tranBranch);
    }

    @Override
    public List<TranBranch> selALL() {
        return mapper.selAll();
    }

}
