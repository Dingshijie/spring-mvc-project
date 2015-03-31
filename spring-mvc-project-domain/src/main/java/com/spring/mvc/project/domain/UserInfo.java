package com.spring.mvc.project.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER_INFO")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 6833744696715572860L;

	@Id
	@Column(name = "ID", length = 128)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private String id;

	@NotBlank(message = "com.spring.mvc.project.validator.NotBlank.message")
	@Column(name = "USERNAME", length = 24, unique = true, nullable = false)
	@Length(min = 2, max = 24, message = "com.spring.mvc.project.validator.Length.message")
	private String username;

	@NotBlank(message = "com.spring.mvc.project.validator.NotBlank.message")
	@Column(name = "PASSWORD", length = 128)
	@Length(min = 6, max = 128, message = "com.spring.mvc.project.validator.Length.message")
	@JsonIgnore
	private String password;

	@Column(name = "REAL_NAME", length = 16)
	@Length(min = 0, max = 16, message = "com.spring.mvc.project.validator.Length.message")
	private String realName;

	@Column(name = "COMPANY_NAME", length = 64)
	@Length(min = 0, max = 64, message = "com.spring.mvc.project.validator.Length.message")
	private String companyName;

	@Column(name = "ID_CARD_NUM", length = 18)
	@Length(min = 0, max = 18, message = "com.spring.mvc.project.validator.Length.message")
	private String idCardNum;

	@Column(name = "ID_CARD_HEAD", length = 512)
	@Length(min = 0, max = 512, message = "com.spring.mvc.project.validator.Length.message")
	private String idCardHead;

	@Column(name = "ID_CARD_BACK", length = 512)
	@Length(min = 0, max = 512, message = "com.spring.mvc.project.validator.Length.message")
	private String idCardBack;

	@Column(name = "PHOTO", length = 512)
	@Length(min = 0, max = 512, message = "com.spring.mvc.project.validator.Length.message")
	private String photo;

	@Column(name = "COMPANY_PICTURE", length = 512)
	@Length(min = 0, max = 512, message = "com.spring.mvc.project.validator.Length.message")
	private String companyPicture;

	@Column(name = "TEL_PHONE", length = 24)
	@Length(min = 0, max = 24, message = "com.spring.mvc.project.validator.Length.message")
	private String telPhone;

	@Column(name = "MOBILE_PHONE", length = 11, unique = true, nullable = false)
	@Length(min = 0, max = 11, message = "com.spring.mvc.project.validator.Length.message")
	private String mobilPhone;

	@Column(name = "EMAIL", length = 32)
	@Length(min = 0, max = 32, message = "com.spring.mvc.project.validator.Length.message")
	private String email;

	/**
	 * 地区代码
	 */
	@NotBlank
	@Column(name = "AREA_CODE", length = 6, nullable = false)
	@Length(min = 1, max = 6, message = "com.spring.mvc.project.validator.Length.message")
	private String areaCode;

	@NotBlank
	@Column(name = "AREA_NAME", length = 64, nullable = false)
	@Length(min = 1, max = 64, message = "com.spring.mvc.project.validator.Length.message")
	private String areaName;

	/**
	 * 院校代码
	 */
	@Column(name = "SCHOOL_CODE", length = 8)
	@Length(min = 0, max = 8, message = "com.spring.mvc.project.validator.Length.message")
	private String schoolCode;

	@Column(name = "SCHOOL_NAME", length = 24)
	@Length(min = 0, max = 24, message = "com.spring.mvc.project.validator.Length.message")
	private String schoolName;

	/**
	 * 学历层次，1：研究生，2：本科生，3：专科生
	 */
	@Column(name = "EDU_LEVEL", length = 1)
	@Length(min = 0, max = 1, message = "com.spring.mvc.project.validator.Length.message")
	private String eduLevel;

	/**
	 * 专业代码
	 */
	@Column(name = "EDU_PUBCODE", length = 8)
	@Length(min = 0, max = 8, message = "com.spring.mvc.project.validator.Length.message")
	private String eduPubCode;

	@Column(name = "EDU_PUBNAME", length = 24)
	@Length(min = 0, max = 24, message = "com.spring.mvc.project.validator.Length.message")
	private String eduPubName;

	@Column(name = "DORMITORY", length = 128)
	@Length(min = 0, max = 128, message = "com.spring.mvc.project.validator.Length.message")
	private String dormitory;

	@Column(name = "ADDRESS", length = 128)
	@Length(min = 0, max = 128, message = "com.spring.mvc.project.validator.Length.message")
	private String address;

	@Column(name = "PRODUCTS_NUM")
	private int productsNum = 0;

	@Column(name = "IP", length = 32)
	private String ip;

	@Column(name = "REGISTER_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date registerDate = new DateTime().toDate();

	@Column(name = "LAST_LOGIN_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date lastLoginDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "AUTHENTICATION", length = 16)
	private Authentication authentication = Authentication.NOT_APPLY;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE", length = 16)
	private Role role = Role.VISITOR;

	@Column(name = "ENABLE")
	private int enable = 1;//（仅限于修改Role 为manager的用户，其余用户全部默认为1）1表示账户在用，

	public enum Authentication {
		/**
		 * 未申请
		 */
		NOT_APPLY,
		/**
		 * 已审核
		 */
		AUDITD,
		/**
		 * 审核中
		 */
		AUDIT,
		/**
		 * 未通过
		 */
		NOT_PASS
	}

	public enum Role {
		/**
		 * 超级管理员
		 */
		ADMIN,
		/**
		 * 管理员
		 */
		MANAGER,
		/**
		 * 商家
		 */
		BUSSINESS,
		/**
		 * 学生
		 */
		STUDENT,
		/**
		 * 游客(未登录)
		 */
		VISITOR
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getIdCardHead() {
		return idCardHead;
	}

	public void setIdCardHead(String idCardHead) {
		this.idCardHead = idCardHead;
	}

	public String getIdCardBack() {
		return idCardBack;
	}

	public void setIdCardBack(String idCardBack) {
		this.idCardBack = idCardBack;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCompanyPicture() {
		return companyPicture;
	}

	public void setCompanyPicture(String companyPicture) {
		this.companyPicture = companyPicture;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getMobilPhone() {
		return mobilPhone;
	}

	public void setMobilPhone(String mobilPhone) {
		this.mobilPhone = mobilPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getProductsNum() {
		return productsNum;
	}

	public void setProductsNum(int productsNum) {
		this.productsNum = productsNum;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}

	@Transactional
	public String getEduLevelName() {
		if ("1".equals(this.eduLevel)) {
			return "研究生";
		} else if ("2".equals(this.eduLevel)) {
			return "本科";
		} else if ("3".equals(this.eduLevel)) {
			return "专科";
		}
		return "";
	}

	public String getEduPubCode() {
		return eduPubCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getEduPubName() {
		return eduPubName;
	}

	public void setEduPubName(String eduPubName) {
		this.eduPubName = eduPubName;
	}

	public void setEduPubCode(String eduPubCode) {
		this.eduPubCode = eduPubCode;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	/**
	 * 判断是否是管理员（包括超级管理员）
	 * @return
	 */
	@Transient
	public boolean isManager() {
		return Role.MANAGER.name().equals(role) || Role.ADMIN.name().equals(role);
	}

	/**
	 * 判断是否是超级管理员（包括超级管理员）
	 * @return
	 */
	@Transient
	public boolean isAdministrator() {
		return Role.ADMIN.name().equals(role);
	}

	@Override
	public String toString() {

		return this.username;
	}

}
