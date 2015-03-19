package com.spring.mvc.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

/**
 * 学校
 */
@Entity
@Table(name = "SCHOOL_INFO")
public class SchoolInfo implements Serializable {

	private static final long serialVersionUID = -4267825753799240947L;

	/**
	 * 是否211院校
	 */
	public static int SCHOOL_211 = 1;

	/**
	 * 是否985院校
	 */
	public static int SCHOOL_985 = 2;
	/**
	 * 是否独立学院
	 */
	public static int IS_INDEPENDENT = 4;
	/**
	 * 是否新增本科
	 */
	public static int IS_NEW = 8;
	/**
	 * 是否示范高职
	 */
	private static int IS_MODEL_VOCATIONAL = 16;
	/**
	 * 是否科研机构
	 */
	private static int IS_RESERACH_INST = 32;
	/**
	 * 是否民办院校
	 */
	private static int IS_PRIVATE = 64;

	/**
	 * 主键
	 */
	@Id
	@Column(name = "ID", length = 128)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private String id;
	/**
	 * 院校代码
	 */
	@NotBlank
	@Length(max = 8)
	@Column(name = "CODE", length = 8, nullable = false, updatable = false)
	private String code;
	/**
	 * 院校名称
	 */
	@Length(max = 32)
	@Column(name = "NAME", length = 32)
	private String name;

	/**
	 * 隶属单位名称
	 */
	@Length(max = 32)
	@Column(name = "BE_UNDER_NAME", length = 32)
	private String beUnderName;
	/**
	 * 学校性质代码
	 */
	@Length(max = 2)
	@Column(name = "TYPE", length = 2)
	private String type;
	/**
	 * 学校性质名称
	 */
	@Length(max = 32)
	@Column(name = "TYPE_NAME", length = 32)
	private String typeName;
	/**
	 * 办学类型
	 */
	@Length(max = 1)
	@Column(name = "BUILD_TYPE", length = 1)
	private String buildType;
	/**
	 * 办学类型名称
	 */
	@Length(max = 32)
	@Column(name = "BUILD_TYPE_NAME", length = 32)
	private String buildTypeName;
	/**
	 * 所在地代码
	 */
	@Length(max = 2)
	@Column(name = "AREA_CODE", length = 2)
	private String areaCode;

	/**
	 * 标签（二进制）
	 */
	@Column(name = "TAGS")
	private int tags;

	/**
	 * 获取学校主键
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置学校主键
	 * @param schoolId
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取学校代码
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置学校代码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = StringUtils.trimAllWhitespace(code);
	}

	/**
	 * 获取学校名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置学校名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = StringUtils.trimAllWhitespace(name);
	}

	/**
	 * 获取学校性质代码（学校类型）
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置学校性质代码（学校类型）
	 * @param type
	 */
	public void setType(String type) {
		this.type = StringUtils.trimAllWhitespace(type);
	}

	/**
	 * 获取学校所在地代码
	 * @return
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置学校所在地代码
	 * @param areaCode
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = StringUtils.trimAllWhitespace(areaCode);
	}

	/**
	 * 获取所在地名称
	 */
	@Transient
	public String getAreaName() {
		return PubCode.getProvinceMap().get(this.areaCode);
	}

	/**
	 *获取是否是211
	 * @return
	 */
	@Transient
	public boolean getIs211() {
		return explainTags(SCHOOL_211);
	}

	/**
	 * 获取是否为985
	 * @return
	 */
	@Transient
	public boolean getIs985() {
		return explainTags(SCHOOL_985);
	}

	/**
	 * 获取是否为独立学院
	 * @return
	 */
	@Transient
	public boolean getIsIndependent() {
		return explainTags(IS_INDEPENDENT);
	}

	/**
	 * 获取是否为新增本科
	 * @return
	 */
	@Transient
	public boolean getIsNew() {
		return explainTags(IS_NEW);
	}

	/**
	 * 获取是否为示范高职
	 * @return
	 */
	@Transient
	public boolean getIsModelVocational() {
		return explainTags(IS_MODEL_VOCATIONAL);
	}

	/**
	 * 获取是否为科研机构
	 * @return
	 */
	@Transient
	public boolean getIsResearchInst() {
		return explainTags(IS_RESERACH_INST);
	}

	/**
	 * 获取是否为民办院校
	 * @return
	 */
	@Transient
	public boolean getIsPrivate() {
		return explainTags(IS_PRIVATE);
	}

	public int getTags() {
		return tags;
	}

	public void setTags(int tags) {
		this.tags = tags;
	}

	/**
	 * 获取办校类型
	 * @return
	 */
	public String getBuildType() {
		return buildType;
	}

	/**
	 * 设置办校类型
	 * @param buildType
	 */
	public void setBuildType(String buildType) {
		this.buildType = StringUtils.trimAllWhitespace(buildType);
	}

	public String getBeUnderName() {
		return beUnderName;
	}

	public void setBeUnderName(String beUnderName) {
		this.beUnderName = StringUtils.trimAllWhitespace(beUnderName);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = StringUtils.trimAllWhitespace(typeName);
	}

	public String getBuildTypeName() {
		return buildTypeName;
	}

	public void setBuildTypeName(String buildTypeName) {
		this.buildTypeName = StringUtils.trimAllWhitespace(buildTypeName);
	}

	public String getProvinceName() {
		return PubCode.getProvinceMap().get(areaCode);
	}

	/**
	 * 分解tag判断各个是否（是否是211，是否是985。。。。。）是true或false
	 * @param tag
	 * @return
	 */
	private boolean explainTags(int tag) {
		return (this.tags & tag) == tag;
	}

}
