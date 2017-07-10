package cn.edu.guet.lym.domain;

import org.apache.ibatis.type.Alias;

/**
 * 部门信息
 * @author lym
 *
 */
@Alias("Department")
public class Department {
	private int id;
	private String name;
	private int parentid;		//	父亲部门id。根部门为1
	private int sequence;			//在父部门中的次序值。order值小的排序靠前。
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", parentid="
				+ parentid + ", sequence=" + sequence + "]";
	}
	
}
