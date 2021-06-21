package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lyd
 * @Description: 传送文件测试
 * @date 14:19
 */
@RestController
public class FilesController {

	@PostMapping(value = "/transferFiles")
	public String transferFiles(@RequestParam String name,
								@RequestParam Integer age,
								@RequestParam List<MultipartFile> multipartFiles) {
		StringBuilder stringBuilder = new StringBuilder(64);
		stringBuilder
				.append("name=")
				.append(name)
				.append(",age=")
				.append(age);

		String fileName;
		for (MultipartFile file : multipartFiles) {
			stringBuilder.append("\n文件信息:\n");
			fileName = file.getOriginalFilename();
			if (fileName == null) {
				continue;
			}
			stringBuilder.append("文件名:" + fileName);
			stringBuilder.append("\n文件类型:" + file.getContentType());
		}

		return stringBuilder.toString();
	}


}