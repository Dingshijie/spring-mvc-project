package com.spring.mvc.project.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.CommodityInfo;
import com.spring.mvc.project.domain.util.ExportField;
import com.spring.mvc.project.domain.util.ExportField.DataType;
import com.spring.mvc.project.service.CommodityService;
import com.spring.mvc.project.web.util.ExportExcel2007;
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
	public List<JSONObject> findList(String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {
		return commodityService.findList(categoryCode, areaCode, schoolCode, status, recommend, used, keyword,
				pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findCount(String categoryCode, String areaCode, String schoolCode, int status, int recommend, int used,
			String keyword) {
		return commodityService.findCount(categoryCode, areaCode, schoolCode, status, recommend, used, keyword);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(MultipartFile file, CommodityInfo commodity, HttpServletRequest request, HttpSession session,
			Model model) {

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

				//将图片上传到tomcat服务器
				String path = session.getServletContext().getRealPath("resources")
						+ realPath.replace(WebUtils.uploadPath, "");
				String dirpath = path.substring(0, path.lastIndexOf("/"));
				File tempdir = new File(dirpath);
				File tempfile = new File(path);

				if (!tempdir.exists()) {
					tempdir.mkdirs();
				}
				tempfile.createNewFile();

				FileUtils.copyInputStreamToFile(file.getInputStream(), tempfile);//上传文件

				commodity.setPicture(realPath.replace(WebUtils.getUploadPath(), ""));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//回头将这两个情况给统成一个情况好了
		//如果添加成功返回列表页面
		if (commodityService.add(commodity)) {
			model.addAttribute("successMsg", "添加成功！");
			return "commodity/list";
		} else {
			//否则返回添加页面
			model.addAttribute("errorMsg", "添加失败！");
			return "commodity/add";
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(CommodityInfo commodity) {
		return commodityService.update(commodity);
	}

	@RequestMapping(value = "list/all", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findAllList(String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {
		List<JSONObject> list = commodityService.findAllList(categoryCode, areaCode, schoolCode, status, recommend,
				used, keyword);
		return list;
	}

	@RequestMapping(value = "count/all", method = RequestMethod.GET)
	@ResponseBody
	public int findAllCount(String categoryCode, String areaCode, String schoolCode, int status, int recommend,
			int used, String keyword) {
		return commodityService.findAllCount(categoryCode, areaCode, schoolCode, status, recommend, used, keyword);
	}

	/**
	 * 点击商品查看详情的时候用
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String findCommodityDetail(@PathVariable(value = "id") String id, Model model) {
		JSONObject commodity = commodityService.find(id);
		model.addAttribute("commodity", commodity);
		return "detail";
	}

	/**
	 * 管理中心点击查看详情的时候用
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	public String findCommodity(@PathVariable(value = "id") String id, Model model) {
		CommodityInfo commodityInfo = commodityService.findById(id);
		model.addAttribute("commodity", commodityInfo);
		return "commodity/detail";
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public ModelAndView export(String categoryCode, String areaCode, String schoolCode, int status, int recommend,
			int used, String keyword) {
		List<CommodityInfo> list = commodityService.findList(categoryCode, areaCode, schoolCode, status, recommend,
				used, keyword);

		List<ExportField> fields = new ArrayList<ExportField>();
		setExportField(fields, list);
		String filename = "商品信息";//"就业统计";
		String sheetName = "sheet";
		return new ModelAndView(new ExportExcel2007(filename, FileType.XLSX, sheetName, list, fields));
	}

	@RequestMapping(value = "addviews/{id}", method = RequestMethod.POST)
	@ResponseBody
	public boolean addViews(@PathVariable(value = "id") String id) {
		return commodityService.addViews(id);
	}

	/**
	 * 设置导处用户的信息项
	 * @param fields
	 * @param list
	 */
	public void setExportField(List<ExportField> fields, List<CommodityInfo> list) {
		fields.add(new ExportField("name", "商品名称", DataType.STRING));
		fields.add(new ExportField("category", "类别", DataType.STRING));
		fields.add(new ExportField("price", "价格", DataType.STRING));
		fields.add(new ExportField("unit", "计量单位", DataType.STRING));
		fields.add(new ExportField("status", "是否在售", DataType.INT));
		fields.add(new ExportField("recommend", "是否被推广", DataType.INT));
		fields.add(new ExportField("used", "是否二手", DataType.INT));
		fields.add(new ExportField("username", "发布用户", DataType.STRING));

	}

}
