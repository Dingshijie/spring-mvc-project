package com.spring.mvc.project.serviceImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.CommodityInfo;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.domain.util.Constant;
import com.spring.mvc.project.repository.CommodityRepository;
import com.spring.mvc.project.service.CommodityService;

@Service
@Transactional(readOnly = true)
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private CommodityRepository commodityRepository;

	@Override
	@Transactional(readOnly = false)
	public boolean add(CommodityInfo commodity) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		commodity.setUsername(userInfo.getUsername());

		return commodityRepository.add(commodity);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(CommodityInfo commodity) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		//添加权限，管理员可以更新，用户自己也可以更新
		CommodityInfo commodityInfo = findById(commodity.getId());
		if (userInfo.isManager() || userInfo.getUsername().equals(commodityInfo.getUsername())) {

			if (!commodity.getName().equals(commodityInfo.getName())) {
				commodityInfo.setName(commodity.getName());
			}
			if (!commodity.getBrand().equals(commodityInfo.getBrand())) {
				commodityInfo.setBrand(commodity.getBrand());
			}
			if (!commodity.getTypeCode().equals(commodityInfo.getTypeCode())) {
				commodityInfo.setTypeCode(commodity.getTypeCode());
			}
			if (!commodity.getLink().equals(commodityInfo.getLink())) {
				commodityInfo.setLink(commodity.getLink());
			}
			if (commodity.getPrice() != commodityInfo.getPrice()) {
				commodityInfo.setPrice(commodity.getPrice());
			}
			if (!commodity.getUnit().equals(commodityInfo.getUnit())) {
				commodityInfo.setUnit(commodity.getUnit());
			}
			if (!commodity.getUnit().equals(commodityInfo.getUnit())) {
				commodityInfo.setUnit(commodity.getUnit());
			}
			if (!commodity.getGoods().equals(commodityInfo.getGoods())) {
				commodityInfo.setGoods(commodity.getGoods());
			}
			if (!commodity.getDescription().equals(commodityInfo.getDescription())) {
				commodityInfo.setDescription(commodity.getDescription());
			}
			if (commodity.getPicture() != null && !commodity.getPicture().equals(commodityInfo.getPicture())) {
				commodityInfo.setPicture(commodity.getPicture());
			}
			commodityRepository.update(commodityInfo);

		}
		return true;

	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = false)
	public boolean modify(String paramter, String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		CommodityInfo CommodityInfo = findById(id);
		if (userInfo.isManager() || userInfo.isAdministrator()
				|| CommodityInfo.getUsername().equals(userInfo.getUsername())) {
			StringBuffer sql = new StringBuffer();
			JSONObject jsonObject = new JSONObject();
			jsonObject = JSONObject.parseObject(paramter);
			Set set = jsonObject.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next().toString();
				String value = jsonObject.get(key).toString();
				sql.append(",");
				sql.append(key + "='" + value + "'");
			}
			return commodityRepository.update(sql.toString().replaceFirst(",", ""), id);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(CommodityInfo commodity) {
		commodityRepository.delete(commodity);

	}

	@Override
	public CommodityInfo findById(String id) {
		return commodityRepository.findById(id);
	}

	@Override
	public JSONObject find(String id) {
		Object[] obj = commodityRepository.find(id);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", obj[0]);
		jsonObj.put("name", obj[1]);
		jsonObj.put("brand", obj[2]);
		jsonObj.put("link", obj[3]);
		jsonObj.put("price", obj[4]);
		jsonObj.put("unit", obj[5]);
		jsonObj.put("picture", obj[6]);
		jsonObj.put("used", obj[7]);//
		jsonObj.put("newCondition", obj[8]);
		jsonObj.put("status", obj[9]);
		jsonObj.put("recommend", obj[10]);
		//将商品清单转换成列表形式
		List<String> goodlist = new ArrayList<String>();
		String[] goods = obj[11].toString().split(Constant.SEPARATO_SEMICOLON);
		for (String good : goods) {
			goodlist.add(good);
		}
		jsonObj.put("goods", goodlist);
		jsonObj.put("description", obj[12]);
		jsonObj.put("views", obj[13]);
		jsonObj.put("addTime", obj[14]);//
		jsonObj.put("username", obj[15]);
		jsonObj.put("realName", obj[16]);
		jsonObj.put("mobilePhone", obj[17]);
		jsonObj.put("telPhone", obj[18]);
		jsonObj.put("companyName", obj[19]);
		return jsonObj;
	}

	@Override
	public List<JSONObject> findList(String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword, int pageIndex, int pageSize) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		List<Object[]> valuelist = new ArrayList<Object[]>();
		if (userInfo.isManager()) {
			valuelist = commodityRepository.findList("", categoryCode, areaCode, schoolCode, status, recommend, used,
					keyword, pageIndex, pageSize);
		} else {
			valuelist = commodityRepository.findList(userInfo.getUsername(), categoryCode, areaCode, schoolCode,
					status, recommend, used, keyword, pageIndex, pageSize);
		}

		return changeEntityToJson(valuelist);
	}

	@Override
	public List<CommodityInfo> findAllList(String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword, int pageIndex, int pageSize) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return commodityRepository.findAllList("", categoryCode, areaCode, schoolCode, status, recommend, used,
					keyword, pageIndex, pageSize);
		} else {
			return commodityRepository.findAllList(userInfo.getUsername(), categoryCode, areaCode, schoolCode, status,
					recommend, used, keyword, pageIndex, pageSize);
		}
	}

	@Override
	public int findCount(String categoryCode, String areaCode, String schoolCode, int status, int recommend, int used,
			String keyword) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return commodityRepository.findCount("", categoryCode, areaCode, schoolCode, status, recommend, used,
					keyword);
		} else {
			return commodityRepository.findCount(userInfo.getUsername(), categoryCode, areaCode, schoolCode, status,
					recommend, used, keyword);
		}
	}

	/**
	 * 将返回类型转换成Json类型
	 * @param valuelist
	 * @return
	 */
	public List<JSONObject> changeEntityToJson(List<Object[]> valuelist) {
		List<JSONObject> objList = new ArrayList<JSONObject>();
		for (Object[] value : valuelist) {
			JSONObject obj = new JSONObject();
			obj.put("id", value[0]);
			obj.put("name", value[1]);
			obj.put("category",
					value[2] == null ? "" : value[2].toString() + value[3] == null ? "" : value[3].toString());
			obj.put("price", value[4]);
			obj.put("unit", value[5]);
			obj.put("status", value[6]);
			obj.put("recommend", value[7]);
			obj.put("used", value[8]);
			obj.put("username", value[9]);
			objList.add(obj);
		}
		return objList;
	}

	@Override
	public List<JSONObject> findCommodityList(String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword, int pageSize, int pageIndex) {
		List<CommodityInfo> list = commodityRepository.findAllList("", categoryCode, areaCode, schoolCode, status,
				recommend, used, keyword, pageSize, pageIndex);
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", list.get(i).getId());
			obj.put("name", list.get(i).getName());
			obj.put("brand", list.get(i).getBrand());
			DecimalFormat decimalFormat = new DecimalFormat("#,###,#00.00");
			obj.put("price", decimalFormat.format(list.get(i).getPrice()));
			obj.put("unit", list.get(i).getUnit());
			obj.put("picture", list.get(i).getPicture());
			obj.put("typeCode", list.get(i).getTypeCode());
			obj.put("used", list.get(i).getUsed());
			obj.put("views", list.get(i).getViews());
			obj.put("recommend", list.get(i).getRecommend());
			obj.put("username", list.get(i).getUsername());
			jsonList.add(obj);
		}
		return jsonList;
	}

	@Override
	public int findAllCount(String categoryCode, String areaCode, String schoolCode, int status, int recommend,
			int used, String keyword) {
		return commodityRepository.findCount("", categoryCode, areaCode, schoolCode, status, recommend, used, keyword);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean addViews(String id) {
		return commodityRepository.addViews(id);
	}

}
