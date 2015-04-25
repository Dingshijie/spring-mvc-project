package com.spring.mvc.project.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.domain.UserInfo.Role;
import com.spring.mvc.project.domain.util.ExportField;
import com.spring.mvc.project.domain.util.ExportField.DataType;
import com.spring.mvc.project.service.UserService;
import com.spring.mvc.project.web.util.ExportExcel2007;
import com.spring.mvc.project.web.util.FileUtil;
import com.spring.mvc.project.web.util.FileUtil.FileType;
import com.spring.mvc.project.web.util.WebUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String addHtml(Model model) {
		//进入到注册的页面
		return "user/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(UserInfo user) {
		return userService.add(user);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(String paramter, String id) {
		return userService.update(paramter, id);
	}

	@RequestMapping(value = "update/img", method = RequestMethod.POST)
	public String update(MultipartFile file, String attribute, String id, HttpServletRequest request,
			HttpSession session, Model model) {
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

				userService
						.update("{'" + attribute + "':'" + realPath.replace(WebUtils.getUploadPath(), "") + "'}", id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/user/selfinfo.html";
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String userListHtml(Model model) {
		//进入到注册的页面
		return "user/list";
	}

	@RequestMapping(value = "exist", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExist(String fieldName, String fieldValue) {
		return userService.isExist(fieldName, fieldValue);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<UserInfo> findList(Role role, String areaCode, String schoolCode, String keyword,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return userService.findList(role, areaCode, schoolCode, keyword, pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findCount(Role role, String areaCode, String schoolCode, String keyword) {
		return userService.findCount(role, areaCode, schoolCode, keyword);
	}

	/**
	 * 进入到个人信息的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "selfinfo.html", method = RequestMethod.GET)
	public String selfInfo(Model model) {

		Subject currentUser = SecurityUtils.getSubject();
		UserInfo user = (UserInfo) currentUser.getPrincipal();
		UserInfo userInfo = userService.find(user.getId());
		model.addAttribute("user", userInfo);
		return "user/selfinfo";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detial(@PathVariable(value = "id") String id, Model model) {

		UserInfo user = userService.find(id);
		model.addAttribute("user", user);
		return "user/detail";
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public ModelAndView export(Role role, String areaCode, String schoolCode, String keyword) {
		List<UserInfo> list = userService.findList(role, areaCode, schoolCode, keyword, 0, 0);

		List<ExportField> fields = new ArrayList<ExportField>();
		setExportField(fields, list);
		String filename = "用户信息";//"就业统计";
		String sheetName = "sheet";
		return new ModelAndView(new ExportExcel2007(filename, FileType.XLSX, sheetName, list, fields));
	}

	/**
	 * 设置到处用户的信息项
	 * @param fields
	 * @param list
	 */
	public void setExportField(List<ExportField> fields, List<UserInfo> list) {
		fields.add(new ExportField("username", "用户名", DataType.STRING));
		fields.add(new ExportField("mobilPhone", "手机号码", DataType.STRING));
		fields.add(new ExportField("areaCode", "所在地区代码", DataType.STRING));
		fields.add(new ExportField("areaName", "所在地区名称", DataType.STRING));
		fields.add(new ExportField("address", "详细地址", DataType.STRING));
		fields.add(new ExportField("role", "角色", DataType.STRING));
		fields.add(new ExportField("enable", "是否在用", DataType.INT));
		fields.add(new ExportField("authentication", "认证信息", DataType.STRING));
	}

	@RequestMapping(value = "password.html", method = RequestMethod.GET)
	public String passwordHTML(Model model) {
		return "user/password";
	}

	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "verifypassword", method = RequestMethod.GET)
	@ResponseBody
	public boolean verifyPassword(String password) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (password.equals(userInfo.getPassword()))
			return true;
		return false;

	}

	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "modifypassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean ModifyPassword(String password) {
		return userService.ModifyPassword(password);
	}

	/**
	 * 重置密码
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "resetpassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean ResetPassword(String id) {
		return userService.ResetPassword(id);
	}

}
