package com.jingluu.demo.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "role")
@DynamicInsert
@DynamicUpdate
@NamedQueries({@NamedQuery(name = "findAll",query = "from Role")})
public class Role {
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "NAME",length=36)
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "ENABLED", columnDefinition = "int(1) default 1")
	private Integer enabled;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME" ,nullable = true)
	private Date createdTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
