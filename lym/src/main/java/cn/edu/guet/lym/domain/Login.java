package cn.edu.guet.lym.domain;

import org.apache.ibatis.type.Alias;

@Alias("Login")
public class Login {
	private int id;
	private String name;
	private String pass;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", pass=" + pass + "]";
	}

}
