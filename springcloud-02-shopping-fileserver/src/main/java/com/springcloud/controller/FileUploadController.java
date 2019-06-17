package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValue;
/**
 * 	文件上传服务的控制层
 * @author Ya
 *
 */
@RestController
public class FileUploadController {
	// 加载配置文件中用户头像的地址
	@Value("${web.user-path}")
	private String userPath;
	// 加载配置文件中商品图片的地址
	@Value("${web.goods-path}")
	private String goodsPath;
	/**
	 * 	用户头像上传
	 * @param file 用户头像文件
	 * @return 返回结果类ResultValue的实例到视图层
	 */
	@RequestMapping(value="/userUpload")
	public ResultValue userUpload(@RequestParam("userImage") MultipartFile file) {
		ResultValue rv = new ResultValue();
		try {
			Map<String, Object> user = this.uploadFile(userPath, file);
			rv.setCode(0);
			rv.setDataMap(user);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	/**
	 * 	商品头像上传
	 * @param file 商品图片文件
	 * @return 返回结果类ResultValue的实例到视图层
	 */
	@RequestMapping(value="/goodsUpload")
	public ResultValue goodsUpload(@RequestParam("goodsImage") MultipartFile file) {
		ResultValue rv = new ResultValue();
		try {
			Map<String, Object> goods = this.uploadFile(goodsPath, file);
			rv.setCode(0);
			rv.setDataMap(goods);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	/**
	 * 	文件上传
	 * @param path 图片地址或图片路径
	 * @param file 图片文件
	 * @return 返回Map类型的数据，新文件名与文件扩展名
	 * @throws IOException
	 */
	private Map<String,Object> uploadFile(String path,MultipartFile file) throws IOException{
		String fileName = UploadUtils.getFileName();
		String extendedName = UploadUtils.getExendedName(file.getOriginalFilename());
		byte[] bytes = file.getBytes();
		File saveFile = new File(path + fileName + extendedName);
		FileCopyUtils.copy(bytes, saveFile);
		Map<String, Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendedName", extendedName);
		return map;
	}
	@RequestMapping(value = "/delectImage")
	public ResultValue delectImage(@RequestParam("goodsImage") String goodsImage) {
		ResultValue rv = new ResultValue();
		try {
			int indexOf = goodsImage.lastIndexOf("/");
			String fileName = null;
			if(indexOf != -1) {
				//当找到最后一个/时,从indexOf的下一个开始截取
				fileName = goodsImage.substring(indexOf+1);
			}
			
			if(fileName != null) {
				File file = new File(this.goodsPath+fileName);
				if(file.exists()) {
					file.delete(); //删除图片
					rv.setCode(0);
					return rv;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	
	@RequestMapping(value = "/delectUserPhoto")
	public ResultValue delectUserPhoto(@RequestParam("userPhoto") String userPhoto) {
		ResultValue rv = new ResultValue();
		try {
			int indexOf = userPhoto.lastIndexOf("/");
			String fileName = null;
			if(indexOf != -1) {
				//当找到最后一个/时,从indexOf的下一个开始截取
				fileName = userPhoto.substring(indexOf+1);
			}
			if(fileName != null) {
				File file = new File(this.userPath+fileName);
				if(file.exists()) {
					file.delete(); //删除图片
					rv.setCode(0);
					return rv;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
}
