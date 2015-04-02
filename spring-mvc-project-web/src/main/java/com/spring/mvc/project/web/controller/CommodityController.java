package com.spring.mvc.project.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.mvc.project.domain.CommodityInfo;
import com.spring.mvc.project.service.CommodityService;
import com.spring.mvc.project.web.util.FileUtil;
import com.spring.mvc.project.web.util.FileUtil.FileType;
import com.spring.mvc.project.web.util.WebUtils;

@Controller
@RequestMapping(value = "/commodity")
public class CommodityController {

	@Autowired
	private CommodityService commodityService;

	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	public String commodityLitsHtml(Model model) {
		//show list jsp
		return "commodity/list";
	}

	@RequestMapping(value = "add.html", method = RequestMethod.GET)
	public String commodityAddHtml(Model model) {
		//show add jsp
		return "commodity/add";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<CommodityInfo> findList(String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {
		return commodityService.findList(categoryCode, areaCode, schoolCode, status, recommend, used, keyword,
				pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findList(String categoryCode, String areaCode, String schoolCode, int status, int recommend, int used,
			String keyword) {
		return commodityService.findCount(categoryCode, areaCode, schoolCode, status, recommend, used, keyword);
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommodityInfo find(@PathVariable(value = "id") String id) {
		return commodityService.find(id);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(MultipartFile file, CommodityInfo commodity, HttpSession session, Model model) {

		FileType fileType = FileUtil.getFileType(file);
		long size = file.getSize();
		if (fileType == null) {
			//为上传文件
		} else if (size > 4 * 1024 * 1024) {
			//文件大小超限制

		} else {
			String realPath = WebUtils.generateFileUploadPath(fileType);
			File temp = new File(realPath);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), temp);//上传文件
				String description = "成功上传[" + file.getOriginalFilename() + "]";
				String object = realPath.substring(WebUtils.getUploadPath().length());
				System.out.println(description + ":" + object);

				commodity.setPicture(realPath.replace(WebUtils.getUploadPath(), ""));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return commodityService.add(commodity);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(CommodityInfo commodity) {
		return commodityService.update(commodity);
	}
}
