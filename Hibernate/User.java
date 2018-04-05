package com.ss.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

//@Entity
//@Table(name="user")
//@DynamicInsert
public class User {
	
	//@Id
	//@Column(name="id")
	public Long id;
	//@Column(name="name",columnDefinition="varchar(20) default 'guest'")
	public String username;
	//@Column(name="password",columnDefinition="varchar(20) default '111111'")
	public String password;
	//@Column(name="enable",columnDefinition="int(1)")
	public int enable;
	//@Column(name="create_time",columnDefinition="timestamp")
	public Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enable=" + enable
				+ ", createTime=" + createTime + "]";
	}
	
}
