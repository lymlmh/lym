package cn.edu.guet.lym.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.guet.lym.domain.AssetsFlow;
import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.FixedAssets;
import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.mapper.AssetsFlowMapper;
import cn.edu.guet.lym.mapper.DepartmentMapper;
import cn.edu.guet.lym.mapper.EmployeeMapper;
import cn.edu.guet.lym.service.AssetsFlowService;
import cn.edu.guet.lym.service.FixedAssetsService;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.AddressBookApi;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.UserContext;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("flow")
public class AssetsFlowController {
	private static final Logger logger=LoggerFactory.getLogger(AssetsFlowController.class);
	@Autowired
	private AssetsFlowService assetsFlowService;
	@Autowired
	private FixedAssetsService assetsService;
	@Autowired
	private DepartmentMapper deptMapper;
	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private WechatService wechatService;
	@Autowired
	private AssetsFlowMapper assetMapper;
	//企业审批返回
	@RequestMapping("audit")
	public String audit(Long id,Integer aduitAdvice){
		try {
			assetsFlowService.audit(id,aduitAdvice);
		} catch (Exception e) {
			logger.error("审核异常",e);
		}
		return "redirect:/flow/index.do?state=2";
	}
	//部门审批返回
	@RequestMapping("audit1")
	public String audit1(Long id,Integer aduitAdvice){
		try {
			assetsFlowService.audit1(id,aduitAdvice);
		} catch (Exception e) {
			logger.error("审核异常",e);
		}
		return "redirect:/flow/index2.do?state=2";
	}
	
	@RequestMapping("item")
	public void item(Long id,HttpServletResponse response){
		String deptna;
		String emp;
		AssetsFlow af=assetsFlowService.get(id);
		FixedAssets fixedAssets = assetsService.get(af.getAssetsId());
		//model.addAttribute("flow", af);
		//model.addAttribute("fixedAssets", fixedAssets);
		JSONObject json=new JSONObject();
		if(af!=null && fixedAssets!=null){
			Employee user = AddressBookApi.getUser(af.getApplyer());
			Employee user1 = empMapper.getItem(af.getEmpid());
			Department dept=deptMapper.get(af.getDeptid());
			if(user1==null||dept==null)
			{
				emp="无";
				deptna="无";		
			}
			else{
				emp=user1.getName();
				deptna=dept.getName();
			}
			json.put("applyer", user.getName());
			json.put("deptname", deptna);
			json.put("empname", emp);
			json.put("success", true);
			/*json.put("moveuser", moveuser);*/
			json.put("assetsFlow", af);
			json.put("fixedAssets", fixedAssets);
		}
		JsonUtil.printJson(response, json);
	}
	
	@RequestMapping("item1")//企业申请审批界面
	public String item1(Model model,long id){
		String deptna;
		String emp;
		AssetsFlow af=assetsFlowService.get(id);
		FixedAssets fixedAssets = assetsService.get(af.getAssetsId());
		if(af!=null && fixedAssets!=null){
			Employee user = AddressBookApi.getUser(af.getApplyer());
			Employee user1 = empMapper.getItem(af.getEmpid());
			Department dept=deptMapper.get(af.getDeptid());
			if(user1==null||dept==null)
			{
				emp="无";
				deptna="无";		
			}
			else{
				emp=user1.getName();
				deptna=dept.getName();
			}
		//model.addAttribute("flow", af);
		//model.addAttribute("fixedAssets", fixedAssets);
			model.addAttribute("applyer", user.getName());
			model.addAttribute("deptname", deptna);
			model.addAttribute("empname", emp);
			model.addAttribute("assetsFlow", af);
			model.addAttribute("fixedAssets", fixedAssets);
		if(af.getNewStatus()!=3){
			return "flow/qiye1";
		}
        	}
		return "flow/1";
	}
	
	@RequestMapping("item2")//部门资产详细信息
	public String item2(Model model,long id){
		String deptna;
		String emp;
		AssetsFlow af=assetsFlowService.get(id);
		FixedAssets fixedAssets = assetsService.get(af.getAssetsId());
		if(af!=null && fixedAssets!=null){
			Employee user = AddressBookApi.getUser(af.getApplyer());
			Employee user1 = empMapper.getItem(af.getEmpid());
			Department dept=deptMapper.get(af.getDeptid());
			if(user1==null||dept==null)
			{
				emp="无";
				deptna="无";		
			}
			else{
				emp=user1.getName();
				deptna=dept.getName();
			}
		//model.addAttribute("flow", af);
		//model.addAttribute("fixedAssets", fixedAssets);
			model.addAttribute("applyer", user.getName());
			model.addAttribute("deptname", deptna);
			model.addAttribute("empname", emp);
			model.addAttribute("assetsFlow", af);
			model.addAttribute("fixedAssets", fixedAssets);
			if(af.getNewStatus()!=3){
				return "flow/bumen3";
			}
        	}
		return "flow/3";
	}
	
	@RequestMapping("list")//企业审批列表入口
	public String list(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				Employee employee=wechatService.accessToken(code);
				if(employee!=null){
					UserContext.setCurrentUser(employee);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "redirect:index.do?state="+state;
	}
	//企业待审批列表
		@RequestMapping("index")
		public String index(Model model,String state){
			Employee currentUser = UserContext.getCurrentUser();
			List<Role> roles = currentUser.getRoles();
			List<AssetsFlow> flows=new ArrayList<>();
			for (int i = 0; i < roles.size(); i++) {
				if(roles.get(i).getDept().getId()==1){
					if(Integer.valueOf(state)==2)
					{
						flows=assetMapper.selectQueryFlow(0,2);
					}
					else
					{
						//flows=assetMapper.selectQueryFlow1(0,2);
						flows=assetMapper.selectQueryFlow1(0,2);
					}
					
					if(flows.size()<=0 || flows== null){
						model.addAttribute("noPage", true);
					}
					model.addAttribute("flows", flows);
					return "flow/index1";
				}		
			}
			//flows=assetsFlowService.selectQueryFlow(Integer.valueOf(state));		
			
			return "error.do";
		}
	
	@RequestMapping("listgeren")//个人申请列表入口
	public String listgeren(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				Employee employee=wechatService.accessToken(code);
				if(employee!=null){	
					state=employee.getUserid();
					UserContext.setCurrentUser(employee);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "redirect:indexgeren.do?state="+state;
	}
	//个人申请查看
		@RequestMapping("indexgeren")
		public String indexgeren(Model model,String state){
			Employee employee=UserContext.getCurrentUser();
			List<AssetsFlow> flows=assetMapper.selectByapplyer(employee.getUserid());
			if(flows.size()<=0 || flows== null){
				model.addAttribute("noPage", true);
			}
			model.addAttribute("flows", flows);
			return "flow/geren";
		}
	//部门审批列表入口
	@RequestMapping("list2")
	public String list2(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				Employee employee=wechatService.accessToken(code);
				if(employee!=null){
					UserContext.setCurrentUser(employee);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "redirect:index2.do?state="+state;
	}
	//部门审批列表
	@SuppressWarnings("unused")
	@RequestMapping("index2")
	public String index2(Model model,String state){
		Employee currentUser = UserContext.getCurrentUser();
		List<Role> roles = currentUser.getRoles();
		List<AssetsFlow> flows=new ArrayList<>();
	
		for (int i = 0; i < roles.size(); i++) {
			Integer deptId = roles.get(i).getDept().getId();
			if(Integer.valueOf(state)==2)
			{
				flows=assetMapper.selectQueryFlow2(2,deptId);
			}
			else
			{
				//flows=assetMapper.selectQueryFlow1(0,2);
				flows=assetMapper.selectQueryFlow3(2,deptId);
			}
			
			if(flows.size()<=0 || flows== null){
				model.addAttribute("noPage", true);
			}
			model.addAttribute("flows", flows);
			return "flow/index2";
			
		}
		//flows=assetsFlowService.selectQueryFlow(Integer.valueOf(state));		
		
		return "error.do";
	}
}
