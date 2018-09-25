package org.java.mapper.xu;

import org.java.entity.TranClient;

public interface TranClientMapper {
    int deleteByPrimaryKey(String clientId);

    int insert(TranClient record);

    int insertSelective(TranClient record);

    TranClient selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(TranClient record);

    int updateByPrimaryKey(TranClient record);
}