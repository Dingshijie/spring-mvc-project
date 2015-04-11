package com.spring.mvc.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.mvc.project.domain.util.Constant;

@Entity
@Table(name = "COMMODITY_INFO")
public class CommodityInfo implements Serializable {

	private static final long serialVersionUID = -3292989269837471516L;

	@Id
	@Column(name = "ID", length = 128)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private String id;

	@NotBlank(message = "com.spring.mvc.project.validator.NotBlank.message")
	@Length(min = 2, max = 128, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "NAME", length = 128, nullable = false)
	private String name;//商品名称

	@Length(min = 0, max = 64, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "BRAND", length = 64)
	private String brand;//商品品牌

	@Length(min = 0, max = 64, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "TYPE_CODE", length = 64)
	private String typeCode;//商品型号

	@Length(min = 1, max = 6, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "CATEGORY", length = 6, nullable = false)
	private String category;//商品类别

	@Length(min = 0, max = 128, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "LINK", length = 128)
	private String link;//商品链接

	@Column(name = "PRICE", precision = 11, scale = 2)
	private double price;//价格

	@Length(min = 0, max = 16, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "UNIT", length = 16, nullable = false)
	private String unit;//计量单位

	@Length(min = 1, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "GOODS", nullable = false)
	@JsonIgnore
	private String goods;//商品清单，两个内容之间用“;”隔开

	@Length(min = 0, max = 512, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "DESCRIPTION", length = 512)
	private String description;//描述

	@Length(min = 0, max = 128, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "PICTURE", length = 128)
	private String picture;//商品图片

	@Column(name = "STATUS", nullable = false)
	private int status = 1;//0表示下架，1表示在售，2表示被强制下架(不可再次销售)，10表示全部

	@Column(name = "RECOMMEND", nullable = false)
	private int recommend = 0;//推广，0表示不被推广，1表示被推广,10表示全部， 由于mysql不支持boolean值

	@Column(name = "USED", nullable = false)
	private int used = 0;//0,表示全新，1表示是二手,10表示全部

	@Length(min = 0, max = 16, message = "com.spring.mvc.project.validator.Length.message")
	@Column(name = "NEW_CONDITION", length = 16)
	private String newCondition;//全新，九成新，八成新，六成新，六成以下

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "ADD_TIME")
	private Date addTime = new DateTime().toDate();//添加日期

	@Column(name = "VIEWS", nullable = false)
	private int views = 0;//浏览次数

	@NotBlank(message = "com.spring.mvc.project.validator.NotBlank.message")
	@Column(name = "USERNAME", length = 24)
	@Length(min = 2, max = 24, message = "com.spring.mvc.project.validator.Length.message")
	private String username;//上传的用户的用户名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public String getNewCondition() {
		return newCondition;
	}

	public void setNewCondition(String newCondition) {
		this.newCondition = newCondition;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//获取包装清单列表形式
	@Transient
	public List<String> getGoodslist() {
		List<String> goodslist = new ArrayList<String>();
		String[] Goods = this.goods.split(Constant.SEPARATO_SEMICOLON);
		for (String good : Goods) {
			if (StringUtils.hasText(good)) {
				goodslist.add(good);
			}
		}
		return goodslist;
	}

}
