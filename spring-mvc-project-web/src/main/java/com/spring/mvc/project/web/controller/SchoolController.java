package com.spring.mvc.project.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.SchoolInfo;
import com.spring.mvc.project.domain.util.ExportField;
import com.spring.mvc.project.domain.util.ExportField.DataType;
import com.spring.mvc.project.service.SchoolService;
import com.spring.mvc.project.web.util.ExportExcel2007;
import com.spring.mvc.project.web.util.FileUtil.FileType;

@Controller
@RequestMapping(value = "/school")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@RequestMapping(value = "school.html", method = RequestMethod.GET)
	public String schoolHtml(Model model) {
		return "school/list";
	}

	@RequestMapping(value = "add.html", method = RequestMethod.GET)
	public String addHtml(Model model) {
		return "school/add";
	}

	@RequestMapping(value = "list/{provinceCode}", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findList(@PathVariable(value = "provinceCode") String provinceCode) {
		return schoolService.findList(provinceCode);
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SchoolInfo find(String id) {
		return schoolService.find(id);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(SchoolInfo schoolInfo) {
		return schoolService.update(schoolInfo);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolInfo> finList(String provinceCode, String keyword,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return schoolService.finList(provinceCode, keyword, pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findCount(String provinceCode, String keyword) {
		return schoolService.findCount(provinceCode, keyword);
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public ModelAndView export(String provinceCode, String keyword) {
		List<SchoolInfo> list = schoolService.finList(provinceCode, keyword, 0, 0);

		List<ExportField> fields = new ArrayList<ExportField>();
		setExportField(fields, list);
		String filename = "类别信息";//"就业统计";
		String sheetName = "sheet";
		return new ModelAndView(new ExportExcel2007(filename, FileType.XLSX, sheetName, list, fields));
	}

	/**
	 * 设置导处用户的信息项
	 * @param fields
	 * @param list
	 */
	public void setExportField(List<ExportField> fields, List<SchoolInfo> list) {
		fields.add(new ExportField("code", "院校代码", DataType.STRING));
		fields.add(new ExportField("name", "院校名称", DataType.STRING));
		fields.add(new ExportField("beUnderName", "隶属单位", DataType.STRING));
		fields.add(new ExportField("typeName", "学校性质", DataType.STRING));
		fields.add(new ExportField("buildTypeName", "办学类型", DataType.STRING));
		fields.add(new ExportField("is211", "211", DataType.BOOLEAN));
		fields.add(new ExportField("id985", "985", DataType.BOOLEAN));
		fields.add(new ExportField("isIndependent", "独立学院", DataType.BOOLEAN));
		fields.add(new ExportField("isNew", "新增本科", DataType.BOOLEAN));
		fields.add(new ExportField("isModelVocational", "示范高职", DataType.BOOLEAN));
		fields.add(new ExportField("isResearchInst", "科研机构", DataType.BOOLEAN));
		fields.add(new ExportField("isPrivate", "民办院校", DataType.BOOLEAN));
	}
}
