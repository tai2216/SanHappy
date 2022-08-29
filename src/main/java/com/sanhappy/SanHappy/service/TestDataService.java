package com.sanhappy.SanHappy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sanhappy.SanHappy.dao.TestDataDao;
import com.sanhappy.SanHappy.model.TestData;

@Service
@Transactional
public class TestDataService {
	@Autowired
	private TestDataDao dao;
	
	public Page<TestData> getAllData(Integer pageNumber,String number, String chinese, String english){
		dao.saveData(number, chinese, english);
		PageRequest pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
		Page<TestData> findAll = dao.findAll(pgb);
		return findAll;
	}
	
	

}
