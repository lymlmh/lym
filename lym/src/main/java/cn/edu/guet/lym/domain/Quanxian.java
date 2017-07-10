package cn.edu.guet.lym.domain;

import org.apache.ibatis.type.Alias;

/**
 * 权限配置表信息
 * @author lym
 *
 */
@Alias("Quanxian")
public class Quanxian {
	private int id;
	private String name;
	private int ifbumen;
	private int ifrenyuan;
	private int ifdidian;
	private int ifquanxian;
	private int ifjuese;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setIfbumen(int ifbumen) {
		this.ifbumen = ifbumen;
	}
	public int getIfbumen() {
		return ifbumen;
	}
	public void setIfrenyuan(int ifrenyuan) {
		this.ifrenyuan = ifrenyuan;
	}
	public int getIfrenyuan() {
		return ifrenyuan;
	}
	public void setIfdidian(int ifdidian) {
		this.ifdidian = ifdidian;
	}
	public int getIfdidian() {
		return ifdidian;
	}
	public void setIfquanxian(int ifquanxian) {
		this.ifquanxian = ifquanxian;
	}
	public int getIfquanxian() {
		return ifquanxian;
	}
	public void setIfjuese(int ifjuese) {
		this.ifjuese = ifjuese;
	}
	public int getIfjuese() {
		return ifjuese;
	}
	public String getIfbumenStr(){
		String str="";
		switch (ifbumen) {
		case 1:
			str="允许";
			break;
		case 2:
			str="不允许";
			break;

		default:
			str="未设置";
			break;
		}
		return str;
	}
	public String getIfrenyuanStr(){
		String str="";
		switch (ifrenyuan) {
		case 1:
			str="允许";
			break;
		case 2:
			str="不允许";
			break;

		default:
			str="未设置";
			break;
		}
		return str;
	}
	public String getIfdidianStr(){
		String str="";
		switch (ifdidian) {
		case 1:
			str="允许";
			break;
		case 2:
			str="不允许";
			break;

		default:
			str="未设置";
			break;
		}
		return str;
	}
	public String getIfquanxianStr(){
		String str="";
		switch (ifquanxian) {
		case 1:
			str="允许";
			break;
		case 2:
			str="不允许";
			break;

		default:
			str="未设置";
			break;
		}
		return str;
	}
	public String getIfjueseStr(){
		String str="";
		switch (ifjuese) {
		case 1:
			str="允许";
			break;
		case 2:
			str="不允许";
			break;

		default:
			str="未设置";
			break;
		}
		return str;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Type1 [id=" + id + ", name=" + name + ", ifbumen=" + ifbumen + ", ifrenyuan=" + ifrenyuan + ","
				+ " ifdidian=" + ifdidian + ", ifquanxian=" + ifquanxian + ",ifjuese="+ifjuese+"]";
	}
	
}
