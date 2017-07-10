package cn.edu.guet.lym.domain;

import org.apache.ibatis.type.Alias;

/**
 * 使用地点信息
 * @author lym
 *
 */
@Alias("Place")
public class Place {
	private int id;
	private String name;
	
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
	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + "]";
	}
	
}
