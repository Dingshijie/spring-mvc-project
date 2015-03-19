package com.spring.mvc.project.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.Area;
import com.spring.mvc.project.domain.util.ExportField;
import com.spring.mvc.project.domain.util.ExportField.DataType;
import com.spring.mvc.project.service.AreaService;
import com.spring.mvc.project.web.util.ExportExcel2007;
import com.spring.mvc.project.web.util.FileUtil.FileType;

@Controller
@RequestMapping(value = "/area")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "area.html", method = RequestMethod.GET)
	public String areaListHtml(Model model) {
		return "area/list";
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Area find(String id) {
		return areaService.find(id);
	}

	@RequestMapping(value = "exist", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExist(String fieldName, String fieldValue) {
		return areaService.isExist(fieldName, fieldValue);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(Area area) {
		return areaService.add(area);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(Area area) {
		return areaService.update(area);
	}

	@RequestMapping(value = "list/city", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findCityList(String areaCode) {
		return areaService.findCityList(areaCode);
	}

	@RequestMapping(value = "list/area", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findAreaList(String areaCode) {
		return areaService.findAreaList(areaCode);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<Area> findList(String areaCode, String keyword,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return areaService.findList(areaCode, keyword, pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findCount(String areaCode, String keyword) {
		return areaService.findCount(areaCode, keyword);
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public ModelAndView export(String areaCode, String keyword) {
		List<Area> list = areaService.findList(areaCode, keyword, 0, 0);

		List<ExportField> fields = new ArrayList<ExportField>();
		setExportField(fields, list);
		String filename = "地区信息";//"就业统计";
		String sheetName = "sheet";
		return new ModelAndView(new ExportExcel2007(filename, FileType.XLSX, sheetName, list, fields));
	}

	/**
	 * 设置导处用户的信息项
	 * @param fields
	 * @param list
	 */
	public void setExportField(List<ExportField> fields, List<Area> list) {
		fields.add(new ExportField("code", "地区代码", DataType.STRING));
		fields.add(new ExportField("name", "地区名称", DataType.STRING));
		fields.add(new ExportField("display", "显示名称", DataType.STRING));
		fields.add(new ExportField("typeCode", "地区类型代码", DataType.STRING));
		fields.add(new ExportField("typeName", "地区类型名称", DataType.STRING));
	}

}
