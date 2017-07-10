package cn.edu.guet.lym.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import cn.edu.guet.lym.util.WechatApi;

/**
 * 员工信息类
 * @author lym
 *
 */
@Alias("Employee")
public class Employee implements Serializable{
	private static final long serialVersionUID = 1374909247136138413L;
	private int id;
	private String name;		//name
	private String userid;		//userid
	private int gender;				//性别
	private String weixinid;
	private String mobile;
	private String email;
	private String deptName; 		//部门名称
	private String label;			//标签
	private String position;	    //职位
	private int deptId;
	private List<Integer> department;
	private String avatar;			//头像图片
	private int status;				// 	关注状态: 1=已关注，2=已冻结，4=未关注 
	private int ifbumen;
	private int ifrenyuan;
	private int partid;
	private Quanxian quanxian;
	
	public String getStatusStr(){
		String str="";
		switch (status) {
		case 1:
			str="已关注";
			break;
		case 2:
			str="已冻结";
			break;
		case 4:
			str="未关注";
			break;

		default:
			str="未知错误";
			break;
		}
		return str;
	}
	
	public int getPartid(){
		return partid;
	}
	public void setPartid(int partid){
		this.partid=partid;
	}
	
	public int getIfbumen(){
		return ifbumen;
	}
	public void setIfbumen(int ifbumen){
		this.ifbumen=ifbumen;
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
	public int getIfrenyuan(){
		return ifrenyuan;
	}
	public void setIfrenyuan(int ifrenyuan){
		this.ifrenyuan=ifrenyuan;
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
	
	private List<Role> roles=new ArrayList<>();
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
  
	
	public Quanxian getQuanxian() {
		return quanxian;
	}

	public void setQuanxian(Quanxian quanxian) {
		this.quanxian = quanxian;
	}

	public String getGenderStr(){
		return WechatApi.getGender(gender);
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<Integer> getDepartment() {
		return department;
	}

	public void setDepartment(List<Integer> department) {
		this.department = department;
	}

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", userid=" + userid
				+ ", gender=" + gender + ", weixinid=" + weixinid + ", mobile="
				+ mobile + ", email=" + email + ", deptName=" + deptName
				+ ", label=" + label + ", position=" + position
				+ ", department=" + department + ", avatar=" + avatar
				+ ", status=" + status + ",deptId="+deptId+",ifbumen="+ifbumen+",ifrenyuan="+ifrenyuan+",partid="+partid+"]";
	}
	
}
