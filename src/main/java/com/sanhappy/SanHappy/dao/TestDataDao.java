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

	/*
	 * 執行原生SQL語法將接收到的參數執行insert語法
	 * @param String,String,String
	 * @return void
	 * */
	@Query(value = "insert into test_data values(:test_data_chinese,:test_data_english,:test_data_number)",nativeQuery = true)
	@Modifying
	public void saveData(@Param("test_data_number") String number,@Param("test_data_chinese") String chinese,@Param("test_data_english") String english);
	
	/*
	 * 使用HQL語法查詢TestData(java class)中的所有資料
	 * @param none
	 * @return List<TestData>
	 * */
	@Query("from TestData")
	public List<TestData> listAllData();
	
}
