package cn.edu.guet.lym.domain;

import java.io.Serializable;

/**
 * 角色信息
 * 2017年4月26日
 */
public class Role implements Serializable{
	private static final long serialVersionUID = 4427399874527206395L;
	private Long id;
	private String name;
	private Employee user;
	private Department dept;
	
	public Employee getUser() {
		return user;
	}
	public void setUser(Employee user) {
		this.user = user;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", user=" + user
				+ ", dept=" + dept + "]";
	}
}
