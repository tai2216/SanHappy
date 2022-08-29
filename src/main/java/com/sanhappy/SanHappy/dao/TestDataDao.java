package com.sanhappy.SanHappy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sanhappy.SanHappy.model.TestData;

@Repository
public interface TestDataDao extends JpaRepository<TestData, Integer>{

	@Query(value = "insert into test_data values(:test_data_chinese,:test_data_english,:test_data_number)",nativeQuery = true)
	@Modifying
	public void saveData(@Param("test_data_number") String number,@Param("test_data_chinese") String chinese,@Param("test_data_english") String english);
	
	@Query("from TestData")
	public List<TestData> listAllData();
	
}
