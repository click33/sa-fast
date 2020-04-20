package com.pj.project4sf.uploadfile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pj.current.config.SystemObject;

/**
 * 文件上传工具类(基于应用服务器的文件上传)
 * @author kong
 *
 */
@Component
public class UploadUtil {

	
	// 注入配置 
	public static UploadConfig uploadConfig;
	@Autowired
	public void setUploadConfig(UploadConfig uploadConfig) {
		UploadUtil.uploadConfig = uploadConfig;
	}
	
	
	
	// 将文件名保存在服务器硬盘上，并把文件对应的http地址返回给前台 
	public static String saveFile(MultipartFile file, String flieTypeFolder) {
		
		// 1、计算路径  
		String currDateFolder = getCurrDateFolder();		// 根据日期计算需要保存的文件夹 
		String fileName = getMarking28() + '.' + getSuffixName(file.getOriginalFilename());					// 文件名 
		String fileFolder = new File(uploadConfig.root_folder).getAbsolutePath() + "/" +
				uploadConfig.http_prefix + flieTypeFolder + currDateFolder + "/";	// 需要保存到的文件夹地址 
		String httpUrl = getDoMain() + uploadConfig.http_prefix + flieTypeFolder + currDateFolder + "/" + fileName;	// 对外暴露的http路径
		
		// 2、如果文件夹不存在，则先创建 
		File dirFile = new File(fileFolder);
		if(dirFile.exists() == false) {
			dirFile.mkdirs();
		}

		// 3、开始转存文件 
		try {
			File outFile = new File(fileFolder + fileName);	// 本地磁盘位置
	        file.transferTo(outFile);		// 转存文件
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		
		// 4、将文件外网地址返回给前台
		return httpUrl;
	}
	
	
	// 验证文件大小 
	static void checkFileSize(MultipartFile file) {
		long size = file.getSize(); // 文件大小(B)
        if (size > uploadConfig.max_size) {
        	throw new RuntimeException("文件大小超出限制");
        }
	}
	
	// 验证指定文件名是否存在于指定后缀列表中 
	// 参数：文件名、后缀列表	-- case：checkSubffix("123.jpg", "jpg,png,gif")	验证通过 
	static void checkSubffix(String fileName, String suffixList) {
		String ext = getSuffixName(fileName).toLowerCase();	// 获取后缀，并转为小写 
		String yx_suffix = suffixList.replace(" ", "") + ",";		// 去空格，加逗号   
		if(yx_suffix.indexOf(ext + ",") == -1) {
			throw new RuntimeException("文件后缀验证未通过：" + ext);
		}
	}
	
	// 获取路径信息
	// return ['文件需要保存的路径', '文件的http路径'] 

	// 返回随机生成的唯一标示28位唯一标示符
	static String getMarking28() {
		return System.currentTimeMillis() + "" + new Random().nextInt(Integer.MAX_VALUE);
	}

	// 取文件后缀
	static String getSuffixName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	// 返回今天的日期文件夹 
	static String getCurrDateFolder() {
		String currDateFolder = new SimpleDateFormat("/yyyy/MM-dd").format(new Date()); 
		return currDateFolder;
	}
	
	// 返回本服务器域名信息 
	static String getDoMain() {
		return SystemObject.config.getDomain();
	}
	
}
