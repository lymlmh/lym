package cn.edu.guet.lym.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 资产流动信息
 * 
 */
public class AssetsFlow implements Serializable{
	private static final long serialVersionUID = 8344335776619728917L;
	private Long id;
	private Long assetsId; 
	private String applyer;
	private Date applyTime;
	private String auditer;
	private Date auditTime;
	private int audit;				//0已审核，1未审核
	private int newStatus;			//新的状态
	private int auditStatus;		//1通过  2 不通过
	private FixedAssets fa;
	private int deptid;
	private String empid;
	private String applyername;
	private int oldStatus;
	private String reason;
	private String place;
	
	public void setPlace(String place){
		this.place=place;
	}
	public String getPlace(){
		return place;
	}
	
	
	public FixedAssets getFa() {
		return fa;
	}
	public void setFa(FixedAssets fa) {
		this.fa = fa;
	}
	public String getAuditStatusStr(){
		String str="";
		switch (auditStatus) {
		case 0:
			str="企业审核通过";
			break;

		case 1:
			str="企业审核不通过";
			break;
		case 2:
			str="企业未审核";
			break;
		default:
			str="未审核";
			break;
		}
		return str;
	}
	
	public String getAuditStr(){
		String str="";
		switch (audit) {
		case 0:
			str="部门审核通过";
			break;
		case 1:
			str="部门审核不通过";
			break;
		case 2:
			str="部门未审核";
			break;
		default:
			str="未审核";
			break;
		}
		return str;
	}
	
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getStatusStr(){
		String str="";
		switch (newStatus) {
		case 0:
			str="投入使用";
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}
	public String getApplyer() {
		return applyer;
	}
	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getAuditer() {
		return auditer;
	}
	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public int getAudit() {
		return audit;
	}
	public void setAudit(int audit) {
		this.audit = audit;
	}
	public int getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}
	public int getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(int oldStatus) {
		this.oldStatus = oldStatus;
	}
	
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getApplyername() {
		return applyername;
	}
	public void setApplyername(String applyername) {
		this.applyername = applyername;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
