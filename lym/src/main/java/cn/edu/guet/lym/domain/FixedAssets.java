package cn.edu.guet.lym.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 固定资产
 * 2017年4月27日
 */
@Alias("FixedAssets")
public class FixedAssets implements Serializable{

	private static final long serialVersionUID = -8428960690105482225L;
	private Long id;
	private String name;
	private String info;
	private String code;			//编号
	private int type;
	private int status;				//0正在使用，1报废，2封存，3转移，4新增,5审核失败
	private String inTime;			//引进时间
	private Date scrapTime;		//报废时间
	private Employee user;
	private Department dept;
	private List<AssetsFlow> flows;
	private String codePath;
	private String oldUserid;
	private int oldDeptid;
	private int price;
	private String place;
	private Type1 type1;
	private Place place1;
	private int pandian;
	
	
	public int getPandian(){
		return pandian;
	}
	public void setPandian(int pandian){
		this.pandian=pandian;
	}
	
	public String getPandianStr(){
		String str="";
		switch (pandian) {
		case 0:
			str="盘点通过";
			break;
		case 1:
			str="盘点不通过";
			break;
		case 2:
			str="未盘点";
			break;

		default:
			str="异常";
			break;
		}
		return str;
	}
	
	public Place getPlace1(){
		return place1;
	}
	
	public void setPlace1(Place place1) {
		this.place1 = place1;
	}
	
	public Type1 getType1(){
		return type1;
	}
	
	public void setType1(Type1 type1) {
		this.type1 = type1;
	}
	
	public String getOldUserid() {
		return oldUserid;
	}
	public void setOldUserid(String oldUserid) {
		this.oldUserid = oldUserid;
	}
	public int getOldDeptid() {
		return oldDeptid;
	}
	public void setOldDeptid(int oldDeptid) {
		this.oldDeptid = oldDeptid;
	}
	public String getCodePath() {
		return codePath;
	}
	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<AssetsFlow> getFlows() {
		return flows;
	}
	public void setFlows(List<AssetsFlow> flows) {
		this.flows = flows;
	}
	public String getTypeStr(){
		String str="";
		switch (type) {
		case 0:
			str="办公";
			break;
		case 1:
			str="生产";
			break;
		case 2:
			str="其他";
			break;

		default:
			str="异常";
			break;
		}
		return str;
	}
	public String getStatusStr(){
		String str="";
		switch (status) {
		case 0:
			str="使用中";
			break;
		case 1:
			str="报废";
			break;
		case 2:
			str="封存";
			break;
		case 3:
			str="转移";
			break;
		case 4:
			str="审核中"; 
			break;
		case 5:
			str="审核失败"; 
			break;
		case 6:
			str="资产增加"; 
			break;
		default:
			str="异常状态";
			break;
		}
		return str;
	}
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public Date getScrapTime() {
		return scrapTime;
	}
	public void setScrapTime(Date scrapTime) {
		this.scrapTime = scrapTime;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "FixedAssets [id=" + id + ", name=" + name + ", info=" + info
				+ ", code=" + code + ", type=" + type + ", status=" + status
				+ ", inTime=" + inTime + ", scrapTime=" + scrapTime + ", user="
				+ user + ", dept=" + dept + ", flows=" + flows + ", codePath="
				+ codePath + ",price="+price+",place="+place+",type1="+type1+",place1="+place1+",pandian="+pandian+"]";
	}
}
