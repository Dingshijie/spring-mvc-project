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
import com.spring.mvc.project.domain.Category;
import com.spring.mvc.project.domain.util.ExportField;
import com.spring.mvc.project.domain.util.ExportField.DataType;
import com.spring.mvc.project.service.CategoryService;
import com.spring.mvc.project.web.util.ExportExcel2007;
import com.spring.mvc.project.web.util.FileUtil.FileType;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	public String categoryListHtml(Model model) {
		//转到商品分类界面
		return "category/list";
	}

	@RequestMapping(value = "add.html", method = RequestMethod.GET)
	public String categoryAddHtml(Model model) {
		//进入添加界面
		return "category/add";
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	public String find(@PathVariable(value = "id") String id, Model model) {
		Category category = categoryService.find(id);
		model.addAttribute("category", category);
		return "category/detail";
	}

	@RequestMapping(value = "exist", method = RequestMethod.GET)
	@ResponseBody
	public boolean exist(String fieldName, String fieldValue) {
		return categoryService.exist(fieldName, fieldValue);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(Category category) {
		return categoryService.add(category);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(Category category) {
		return categoryService.update(category);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> findList(String categoryCode, @RequestParam(value = "hot", defaultValue = "10") int hot,
			@RequestParam(value = "enable", defaultValue = "10") int enable, String keyword,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return categoryService.findList(categoryCode, hot, enable, keyword, pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findList(String categoryCode, @RequestParam(value = "hot", defaultValue = "10") int hot,
			@RequestParam(value = "enable", defaultValue = "10") int enable, String keyword) {
		return categoryService.findCount(categoryCode, hot, enable, keyword);
	}

	@RequestMapping(value = "list/{code}", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findList(@PathVariable(value = "code") String code) {
		return categoryService.findList(code);
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public ModelAndView export(String categoryCode, @RequestParam(value = "hot", defaultValue = "10") int hot,
			@RequestParam(value = "enable", defaultValue = "10") int enable, String keyword) {
		List<Category> list = categoryService.findList(categoryCode, hot, enable, keyword, 0, 0);

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
	public void setExportField(List<ExportField> fields, List<Category> list) {
		fields.add(new ExportField("name", "类别名称", DataType.STRING));
		fields.add(new ExportField("code", "类别代码", DataType.STRING));
		fields.add(new ExportField("categoryName", "所在类别", DataType.STRING));
		fields.add(new ExportField("hot", "是否热门", DataType.INT));
		fields.add(new ExportField("enable", "是否在用", DataType.INT));
	}

}
