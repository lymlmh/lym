package cn.edu.guet.lym.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.guet.lym.domain.AssetsFlow;
import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.FixedAssets;
import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.mapper.AssetsFlowMapper;
import cn.edu.guet.lym.mapper.FixedAssetsMapper;
import cn.edu.guet.lym.mapper.RoleMapper;
import cn.edu.guet.lym.service.AssetsFlowService;
import cn.edu.guet.lym.util.UserContext;
import cn.edu.guet.lym.util.WechatApi;
@Service
public class AssetsFlowServiceImpl implements AssetsFlowService {
	@Autowired
	private AssetsFlowMapper assetsFlowMapper;
	@Autowired
	private FixedAssetsMapper assetsMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	public void savecreate(Long id,int newStatus,Integer deptId, String userid,int oldStatus,String reason,Integer audit) {
		Employee currentUser = UserContext.getCurrentUser();
		AssetsFlow flow=new AssetsFlow();
		//List<Role> roles = currentUser.getRoles();
		flow.setAudit(0);		
		flow.setApplyer(currentUser.getUserid());
		flow.setApplyTime(new Date());
		flow.setAuditTime(new Date());
		flow.setAuditer(currentUser.getName());
		flow.setAssetsId(id);
		flow.setOldStatus(oldStatus);
		flow.setNewStatus(newStatus);
		flow.setReason(reason);
		flow.setAuditStatus(0);
		flow.setDeptid(deptId);
		flow.setEmpid(userid);
		assetsFlowMapper.save1(flow);
	}
	
	public void save(Long id,int newStatus,int oldStatus,String reason,Integer audit) {
		Employee currentUser = UserContext.getCurrentUser();
		AssetsFlow flow=new AssetsFlow();
		//List<Role> roles = currentUser.getRoles();
		flow.setAudit(2);
		int a=7;
		String user="";
		if(audit==0)//来自部门或企业申请
		{
			a=7;
			//for (int i = 0; i < roles.size(); i++) {
				flow.setAudit(0);
				flow.setAuditer(currentUser.getName());
				Role r=roleMapper.getemp(1);
				if(r!=null)
				{
					user=r.getUser().getUserid();
				}
                
			//}
		}
		
		if(audit==1){//来自员工个人的资产变动申请
            a=11;
            FixedAssets asset = assetsMapper.get(id);
			Role r=roleMapper.getemp(asset.getDept().getId());
			if(r!=null){
				user=r.getUser().getUserid();	
			}
			
			
		}
		String msg="你有新的审批申请，请及时处理！";
		
		WechatApi.sendActivityMsg(msg,a,user);
		
		flow.setApplyer(currentUser.getUserid());
		flow.setApplyTime(new Date());
		flow.setAssetsId(id);
		flow.setOldStatus(oldStatus);
		flow.setNewStatus(newStatus);
		flow.setReason(reason);
		flow.setAuditStatus(2);
		assetsFlowMapper.save(flow);
	}
	@Override
	public void save1(Long id,int newStatus,Integer deptId, String userid,int oldStatus,String reason,String place,Integer audit) {
		
		AssetsFlow flow=new AssetsFlow();
		Employee currentUser = UserContext.getCurrentUser();
		//List<Role> roles = currentUser.getRoles();
		flow.setAudit(2);
		int a=7;
		String user="";
		if(audit==0)//来自部门或公司的资产转移申请
		{
			//for (int i = 0; i < roles.size(); i++) {
			
				flow.setAudit(0);
				flow.setAuditer(currentUser.getName());
				Role r=roleMapper.getemp(1);
				if(r!=null){
				    user=r.getUser().getUserid();
				}
			//}
		}
		if(audit==1){//来自员工个人的资产转移申请
			a=11;
			FixedAssets asset = assetsMapper.get(id);
			Role r=roleMapper.getemp(asset.getDept().getId());
			if(r!=null){
				 user=r.getUser().getUserid();
			}
			   
		}
		String msg="你有新的审批申请，请及时处理！";
		WechatApi.sendActivityMsg(msg,a,user);
		flow.setApplyer(currentUser.getUserid());
		flow.setApplyTime(new Date());
		flow.setAssetsId(id);
		flow.setPlace(place);
		flow.setNewStatus(newStatus);
		flow.setOldStatus(oldStatus);
		//flow.setAudit(audit);//1代表未审批
		flow.setDeptid(deptId);
		flow.setEmpid(userid);
		flow.setAuditStatus(2);
		//reason="shabi";
		flow.setReason(reason);
		assetsFlowMapper.save1(flow);
	}

	@Override
	public List<AssetsFlow> selectByAssetsId(Long id) {
		return assetsFlowMapper.selectByAssetsId(id);
	}

	@Override
	public List<AssetsFlow> selectQueryFlow(Integer audit) {
		return assetsFlowMapper.selectQueryFlow(audit,null);
	}

	@Override
	public AssetsFlow get(Long id) {
		return assetsFlowMapper.get(id);
	}
//企业审批流程
	@Override
	public void audit(Long id,Integer aduitAdvice) {
		AssetsFlow assetsFlow = this.get(id);
		Employee currentUser = UserContext.getCurrentUser();
		//assetsFlow.setAudit(0);
		String olduser=assetsFlow.getAuditer();
		assetsFlow.setAuditer(olduser+"--"+currentUser.getName());
		assetsFlow.setAuditTime(new Date());
		assetsFlow.setAuditStatus(aduitAdvice);
		FixedAssets fixedAssets = assetsMapper.get(assetsFlow.getAssetsId());
		if(aduitAdvice==0){//审核成功
			if(assetsFlow.getNewStatus()==1){
				fixedAssets.setScrapTime(new Date());
			}
			if(assetsFlow.getNewStatus()==6){//增加资产成功，初始未封存
				
				fixedAssets.setStatus(2);
			}
			else if(assetsFlow.getNewStatus()==3){
				fixedAssets.setStatus(assetsFlow.getOldStatus());
				
				Employee user=new Employee();
				user.setUserid(assetsFlow.getEmpid());
			    Department dept=new Department();
				dept.setId(assetsFlow.getDeptid());
				fixedAssets.setUser(user);
				fixedAssets.setDept(dept);
				fixedAssets.setPlace(assetsFlow.getPlace());
			}
			else{
				fixedAssets.setStatus(assetsFlow.getNewStatus());
			}
			
		}else{//审核失败
			if(assetsFlow.getNewStatus()==6){
				fixedAssets.setScrapTime(new Date());
			}
			fixedAssets.setStatus(assetsFlow.getOldStatus());
/*			if(assetsFlow.getNewStatus()==3){//转移失败
				fixedAssets.setStatus(0);*/
/*				Employee user=new Employee();
				user.setUserid(fixedAssets.getOldUserid());*/
/*				Department dept=new Department();
				dept.setId(fixedAssets.getOldDeptid());
				fixedAssets.setUser(user);
				fixedAssets.setDept(dept);*/
			//}
		}
		int a=10;
		String user=assetsFlow.getApplyer();
		String msg="你的申请已审批，请查看";
		WechatApi.sendActivityMsg(msg,a,user);
		assetsMapper.update(fixedAssets);
		assetsFlowMapper.updateAudit(assetsFlow);
	}
	
	//部门管理员审批
	@Override
	public void audit1(Long id,Integer aduitAdvice) {
		AssetsFlow assetsFlow = this.get(id);
		Employee currentUser = UserContext.getCurrentUser();
		//assetsFlow.setAudit(0);
		assetsFlow.setAuditer(currentUser.getName());
		//assetsFlow.setAuditTime(new Date());
		assetsFlow.setAudit(aduitAdvice);
		FixedAssets fixedAssets = assetsMapper.get(assetsFlow.getAssetsId());
		int a=7;
		String user="";
		String msg="你有新的审批申请，请及时处理！";
		if(aduitAdvice==0){//审核成功
			Role r=roleMapper.getemp(1);
			if(r!=null){
			    user=r.getUser().getUserid();
			}
		}else{//审核失败
			if(assetsFlow.getNewStatus()==6){
				fixedAssets.setScrapTime(new Date());
			}
			fixedAssets.setStatus(assetsFlow.getOldStatus());
			assetsFlow.setAuditStatus(1);
			a=10;
			user=assetsFlow.getApplyer();
			msg="你的申请已审批，请查看";
			assetsFlow.setAuditTime(new Date());
		}
		WechatApi.sendActivityMsg(msg,a,user);
		assetsMapper.update(fixedAssets);
		assetsFlowMapper.updateAudit(assetsFlow);
	}
	@Override
	public void sendmsg(){
		
	}
}
