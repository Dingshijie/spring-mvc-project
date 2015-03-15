package com.spring.mvc.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = -1371742329941754114L;

	@Id
	@Column(name = "ID", length = 22)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private String id;

	@NotBlank
	@Column(name = "CODE", length = 4, nullable = false, updatable = false)
	private String code;

	@NotBlank
	@Column(name = "NAME", length = 32, nullable = false)
	private String name;

	@Column(name = "HOT", nullable = false)
	private int hot = 0;//是否是热门 0 ，表示不是热门

	@Column(name = "ENABLE")
	private int enable = 1;//是否可用，1表示该分类可用,0表示不可用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

}
