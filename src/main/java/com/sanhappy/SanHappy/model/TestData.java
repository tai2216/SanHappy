package com.sanhappy.SanHappy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_data")
public class TestData {
	
	@Id
	@Column(name = "test_data_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//輸入的數字欄位
	@Column(name="test_data_number",columnDefinition = "nvarchar(max)")
	private String number;
	//輸入的中文欄位
	@Column(name="test_data_chinese",columnDefinition = "nvarchar(max)")
	private String chinese;
	//輸入的英文欄位
	@Column(name = "test_data_english",columnDefinition = "nvarchar(max)")
	private String english;
	
	
	

	public TestData() {
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	




	




	public String getNumber() {
		return number;
	}




	public void setNumber(String number) {
		this.number = number;
	}




	public String getChinese() {
		return chinese;
	}




	public void setChinese(String chinese) {
		this.chinese = chinese;
	}




	public String getEnglish() {
		return english;
	}




	public void setEnglish(String english) {
		this.english = english;
	}




	@Override
	public String toString() {
		return "TestData [id=" + id + ", number=" + number + ", chinese=" + chinese + ", english=" + english + "]";
	}
	
	
	
	
	

}
