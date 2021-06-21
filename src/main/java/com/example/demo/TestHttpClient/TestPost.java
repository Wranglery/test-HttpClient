package com.example.demo.TestHttpClient;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author lyd
 * @Description: Post测试
 * @date 11:32
 */
public class TestPost {

	/**
	 * Post无参测试
	 */
	@Test
	public void doPostTestOne() {
		// 创建Http客户端
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		HttpPost httpPost = new HttpPost("http://localhost:8080/doPostTestOne");

		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 客户端发送Get请求
			response = closeableHttpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为：" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为：" + responseEntity.getContentLength());
				System.out.println("响应内容为：" + responseEntity);
				System.out.println("响应内容为：" + EntityUtils.toString(responseEntity));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				if (closeableHttpClient != null) {
					closeableHttpClient.close();
				}

				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Post对象参数测试
	 */
	@Test
	public void doPostTestTwo() {
		// 创建Http客户端
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		HttpPost httpPost = new HttpPost("http://localhost:8080/doPostTestTwo");

		User user = new User();
		user.setName("liuliu");
		user.setGender("boy");
		user.setMotto("要帅");
		user.setAge(13);

		String jsonStr = JSON.toJSONString(user);

		StringEntity stringEntity = new StringEntity(jsonStr,"utf8");

		httpPost.setHeader("Content-Type","application/json;charset=utf8");
		httpPost.setEntity(stringEntity);

		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 客户端发送Get请求
			response = closeableHttpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为：" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为：" + responseEntity.getContentLength());
				System.out.println("响应内容为：" + responseEntity);
				System.out.println("响应内容为：" + EntityUtils.toString(responseEntity));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				if (closeableHttpClient != null) {
					closeableHttpClient.close();
				}

				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}