package com.sanhappy.SanHappy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanhappy.SanHappy.dao.TestDataDao;
import com.sanhappy.SanHappy.model.TestData;
import com.sanhappy.SanHappy.service.TestDataService;

@Controller
public class SanHappyController {
	
	@Autowired
	private TestDataDao dao;
	@Autowired
	private TestDataService service;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String restTest() {
		return "test";
	}
	
	@RequestMapping(value="/getResult",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Page<TestData> getResult(@RequestParam(name="p",defaultValue = "1")Integer pageNumber,@RequestParam("number") String number,@RequestParam("chinese") String chinese, @RequestParam("english") String english) {
		Page<TestData> allData = service.getAllData(pageNumber,number, chinese, english);
		System.out.println(allData.toList());
	
		return allData;
	}
	
	

}
