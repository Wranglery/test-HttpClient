package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyd
 * @Description: Post
 * @date 11:38
 */
@RestController
public class PostController {

	@RequestMapping(value = "/doPostTestOne",method = RequestMethod.POST)
	public String doPostTestOne(){
		return "这个Post请求没有任何参数";
	}

	/**
	 * 对象参数
	 * @return
	 */
	@RequestMapping(value = "/doPostTestTwo",method = RequestMethod.POST)
	public String doPostTestTwo(@RequestBody User user){
		return user.toString();
	}


}