package com.spring.mvc.project.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.project.domain.EduPubCode;
import com.spring.mvc.project.domain.util.ExportField;
import com.spring.mvc.project.domain.util.ExportField.DataType;
import com.spring.mvc.project.service.EduPubCodeService;
import com.spring.mvc.project.web.util.ExportExcel2007;
import com.spring.mvc.project.web.util.FileUtil.FileType;

@Controller
@RequestMapping("/edupubcode")
public class EduPubCodeController {

	@Autowired
	private EduPubCodeService eduPubCodeService;

	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	public String edupubcodeHtml(Model model) {
		return "edupubcode/list";
	}

	@RequestMapping(value = "add.html", method = RequestMethod.GET)
	public String addEdupubcodeHtml(Model model) {
		return "edupubcode/add";
	}

	@RequestMapping(value = "zydl/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> findZYDLMap(String eduLevel, String keyword) {
		return eduPubCodeService.findZYDLMap(eduLevel, keyword);
	}

	@RequestMapping(value = "zyzl/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> findZYZLMap(String eduLevel, String firstCode, String keyword) {
		return eduPubCodeService.findZYZLMap(eduLevel, firstCode, keyword);
	}

	@RequestMapping(value = "zyxl/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> findZYXLMap(String eduLevel, String secondCode, String keyword) {
		return eduPubCodeService.findZYXLMap(eduLevel, secondCode, keyword);
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	public String find(@PathVariable(value = "id") String id, Model model) {
		EduPubCode eduPubCode = eduPubCodeService.find(id);
		model.addAttribute("edupubcode", eduPubCode);
		return "edupubcode/detail";
	}

	@RequestMapping(value = "exist", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExist(String eduLevel, String fieldName, String fieldValue) {
		return eduPubCodeService.isExist(eduLevel, fieldName, fieldValue);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(EduPubCode eduPubCode) {
		return eduPubCodeService.add(eduPubCode);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(EduPubCode eduPubCode) {
		return eduPubCodeService.update(eduPubCode);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<EduPubCode> findList(String eduLevel, String keyword,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return eduPubCodeService.findList(eduLevel, keyword, pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findCount(String eduLevel, String keyword) {
		return eduPubCodeService.findCount(eduLevel, keyword);
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public ModelAndView export(String eduLevel, String keyword) {
		List<EduPubCode> list = eduPubCodeService.findList(eduLevel, keyword, 0, 0);

		List<ExportField> fields = new ArrayList<ExportField>();
		setExportField(fields, list);
		String filename = "专业信息";//"就业统计";
		String sheetName = "sheet";
		return new ModelAndView(new ExportExcel2007(filename, FileType.XLSX, sheetName, list, fields));
	}

	/**
	 * 设置导处用户的信息项
	 * @param fields
	 * @param list
	 */
	public void setExportField(List<ExportField> fields, List<EduPubCode> list) {
		fields.add(new ExportField("category", "学历层次", DataType.STRING));
		fields.add(new ExportField("code", "专业代码", DataType.STRING));
		fields.add(new ExportField("name", "专业名称", DataType.STRING));
		fields.add(new ExportField("firstName", "专业学科大类名称", DataType.STRING));
		fields.add(new ExportField("secondName", "专业学科中类名称", DataType.STRING));
	}

}
