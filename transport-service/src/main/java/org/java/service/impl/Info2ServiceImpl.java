package org.java.service.impl;

import org.java.entity.Info2;
import org.java.mapper.Info2Mapper;
import org.java.service.Info2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Info2ServiceImpl implements Info2Service{
	
	@Autowired
	private Info2Mapper info2Mapper;

	@Override
	public void insertInfo2(Info2 info2) {
		info2Mapper.insertInfo2(info2);
		
	}

}
