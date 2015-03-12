package com.spring.mvc.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

/**
 * 专业
 */
@Entity
@Table(name = "EDU_PUBCODE")
public class EduPubCode implements Serializable {
	private static final long serialVersionUID = -18658536167762319L;

	/**
	 * 研究生
	 */
	public static final String POSTGRADUATE_MAJOR = "1";
	/**
	 * 本科生
	 */
	public static final String UNDERGRADUATE_MAJOR = "2";
	/**
	 * 专科生
	 */
	public static final String JUNIOR_COLLEGE_MAJOR = "3";
	/**
	 * ID
	 */
	@Id
	@Column(name = "ID", length = 128)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private String id;
	/**
	 * 专业类别(学历层次)
	 */
	@NotBlank
	@Column(name = "EDU_LEVEL", length = 1, nullable = false, updatable = false)
	private String eduLevel;
	/**
	 * 专业代码
	 */
	@NotBlank
	@Column(name = "CODE", length = 8, nullable = false, updatable = false)
	private String code;
	/**
	 * 专业名称
	 */
	@NotBlank
	@Column(name = "NAME", length = 32, nullable = false, updatable = false)
	private String name;

	/**
	 * 专业学科大类代码
	 */
	@Column(name = "FIRST_CODE", length = 2, nullable = false, updatable = false)
	private String firstCode;
	/**
	 * 专业学科大类名称
	 */
	@Column(name = "FIRST_NAME", length = 16, nullable = false, updatable = false)
	private String firstName;
	/**
	 * 专业学科中类代码
	 */
	@Column(name = "SECOND_CODE", length = 4, nullable = false, updatable = false)
	private String secondCode;
	/**
	 * 专业学科中类名称
	 */
	@Column(name = "SECOND_NAME", length = 32, nullable = false, updatable = false)
	private String secondName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = StringUtils.trimAllWhitespace(eduLevel);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = StringUtils.trimAllWhitespace(code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trimAllWhitespace(name);
	}

	public String getFirstCode() {
		return firstCode;
	}

	public void setFirstCode(String firstCode) {
		this.firstCode = StringUtils.trimAllWhitespace(firstCode);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = StringUtils.trimAllWhitespace(firstName);
	}

	public String getSecondCode() {
		return secondCode;
	}

	public void setSecondCode(String secondCode) {
		this.secondCode = StringUtils.trimAllWhitespace(secondCode);
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = StringUtils.trimAllWhitespace(secondName);
	}

	public String getCategory() {
		if (POSTGRADUATE_MAJOR.equals(this.eduLevel)) {
			return "研究生专业";
		} else if (UNDERGRADUATE_MAJOR.equals(this.eduLevel)) {
			return "本科专业";
		} else if (JUNIOR_COLLEGE_MAJOR.equals(this.eduLevel)) {
			return "专科专业";
		}
		return "";
	}
}
