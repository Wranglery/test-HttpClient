package com.example.demo.TestHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyd
 * @Description: Get测试
 * @date 10:21
 */
public class TestGet {


	/**
	 * 无参测试
	 */
	@Test
	public void doGetTestOne() {

		// 创建Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Get请求
		HttpGet httpGet = new HttpGet("http://localhost:8080/doGetTestOne");
		// HttpGet httpGet = new HttpGet("http://java.sun.com");

		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 客户端发送Get请求
			response = httpClient.execute(httpGet);
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
				if (httpClient != null) {
					httpClient.close();
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
	 * 有参测试：直接拼接url
	 */
	@Test
	public void doGetTestWayOne() {
		// 参数
		StringBuffer params = new StringBuffer();
		params.append("name=liu");
		params.append("&");
		params.append("age=24");


		// 创建Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Get请求
		// HttpGet httpGet = new HttpGet("http://localhost:8080/doGetTestWayOne?" + params);
		HttpGet httpGet = new HttpGet("https://haoma.baidu.com/phoneSearch?search=18460364031");


		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 配置信息
			RequestConfig requestConfig = RequestConfig.custom()
					// 设置连接超时时间
					.setConnectTimeout(5000)
					// 设置请求超时时间
					.setConnectionRequestTimeout(5000)
					// 设置读写超时时间
					.setSocketTimeout(5000)
					// 设置是否允许重定向
					.setRedirectsEnabled(true).build();

			// 添加配置信息
			httpGet.setConfig(requestConfig);

			// 客户端发送Get请求
			response = httpClient.execute(httpGet);

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
				if (httpClient != null) {
					httpClient.close();
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
	 * 有参测试:动态生成url
	 */
	@Test
	public void doGetTestWayTwo() {
		// 参数
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("name", "liu"));
		params.add(new BasicNameValuePair("age", "12"));

		/**
		 * URL是一种具体的URI，它是URI的一个子集，它不仅唯一标识资源，而且还提供了定位该资源的信息
		 */
		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath("/doGetTestWayTwo")
					.setParameters(params)
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// 创建Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Get请求
		HttpGet httpGet = new HttpGet(uri);

		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 配置信息
			RequestConfig requestConfig = RequestConfig.custom()
					// 设置连接超时时间
					.setConnectTimeout(5000)
					// 设置请求超时时间
					.setConnectionRequestTimeout(5000)
					// 设置读写超时时间
					.setSocketTimeout(5000)
					// 设置是否允许重定向
					.setRedirectsEnabled(true).build();

			// 添加配置信息
			httpGet.setConfig(requestConfig);

			// 客户端发送Get请求
			response = httpClient.execute(httpGet);

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
				if (httpClient != null) {
					httpClient.close();
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


