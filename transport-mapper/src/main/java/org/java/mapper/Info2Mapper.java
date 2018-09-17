package org.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Info2;

@Mapper
public interface Info2Mapper {
   
	void insertInfo2(Info2 info2);
	
	List<Info2> getList();
}