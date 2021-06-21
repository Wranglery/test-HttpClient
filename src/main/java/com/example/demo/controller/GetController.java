package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyd
 * @Description: Get对应接收示例
 * @date 10:38
 */
@RestController
public class GetController {

	@RequestMapping("doGetTestOne")
	public String doGetTestOne(){
		return "123";
	}

	@RequestMapping("doGetTestWayOne")
	public String doGetTestWayOne(String name,Integer age){
		return name+age+"岁";
	}

	@RequestMapping("doGetTestWayTwo")
	public String doGetTestWayTwo(String name,Integer age){
		return name+age+"岁";
	}


}