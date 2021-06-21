package com.example.demo.TestHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @author lyd
 * @Description: 测试发送文件
 * @date 13:58
 */
public class TestTransferFiles {

	@Test
	public void transferFiles() {
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://localhost:8080/transferFiles");
		CloseableHttpResponse closeableHttpResponse = null;

		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		// 多个文件可以同使用一个key,后端用集合或数组接收即可
		String filesKey = "files";

		// 第一个文件
		File file1 = new File("D:\\IdeaProjects\\test-HttpClient\\src\\main\\resources\\verifyImg\\1.png");
		multipartEntityBuilder.addBinaryBody(filesKey, file1);

		// 第二个文件
		File file2 = new File("D:\\IdeaProjects\\test-HttpClient\\src\\main\\resources\\verifyImg\\2.png");
		try {
			// 防止服务端收到的文件名乱码。 我们这里可以先将文件名URLEncode，然后服务端拿到文件名时在URLDecode。就能避免乱码问题。
			multipartEntityBuilder.addBinaryBody(filesKey, file2, ContentType.DEFAULT_BINARY, URLEncoder.encode(file2.getName(), "utf8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// text/plain的意思是将文件设置为纯文本的形式，浏览器在获取到这种文件时并不会对其进行处理。
		ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
		multipartEntityBuilder.addTextBody("name", "关羽", contentType);
		multipartEntityBuilder.addTextBody("age", "20", contentType);

		HttpEntity httpEntity = multipartEntityBuilder.build();
		httpPost.setEntity(httpEntity);

		try {
			// 客户端发送post请求
			closeableHttpResponse = closeableHttpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = closeableHttpResponse.getEntity();
			System.out.println("响应状态为：" + closeableHttpResponse.getStatusLine());
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

				if (closeableHttpResponse != null) {
					closeableHttpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


}