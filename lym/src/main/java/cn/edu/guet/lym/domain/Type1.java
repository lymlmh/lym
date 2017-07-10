package cn.edu.guet.lym.domain;

import org.apache.ibatis.type.Alias;

/**
 * 资产类型信息
 * @author lym
 *
 */
@Alias("Type1")
public class Type1 {
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
		return "Type1 [id=" + id + ", name=" + name + "]";
	}
	
}
