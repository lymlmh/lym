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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.guet.lym.domain.AssetsFlow;
import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.FixedAssets;
import cn.edu.guet.lym.domain.Place;
import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.domain.Type1;
import cn.edu.guet.lym.mapper.FixedAssetsMapper;
import cn.edu.guet.lym.mapper.RoleMapper;
import cn.edu.guet.lym.service.AssetsFlowService;
import cn.edu.guet.lym.service.FixedAssetsService;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.AddressBookApi;
import cn.edu.guet.lym.util.Consts;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.MatrixToImageWriter;
import cn.edu.guet.lym.util.ResultJson;
import cn.edu.guet.lym.util.UserContext;
import cn.edu.guet.lym.util.WechatApi;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("assets")
public class FixedAssetsController extends BaseController{
	private static final Logger logger=LoggerFactory.getLogger(FixedAssetsController.class);
	@Autowired
	private WechatService wechatService;
	@Autowired
	private FixedAssetsService fixedAssetsService;
	@Autowired
	private AssetsFlowService assetsFlowService;
	
	@Autowired
	private RoleMapper roletype;
	@Autowired
	private FixedAssetsMapper assetsMapper;
	
	@RequestMapping("search")
	public void search(HttpServletResponse response,
			String search){
		List<FixedAssets> list=fixedAssetsService.search(search);
		JsonUtil.printJson(response, list);
	}
	
	@RequestMapping("searchbyuserid")
	public void searchbyuserid(HttpServletResponse response,
			String search){
		Employee currentUser = UserContext.getCurrentUser();
		List<FixedAssets> list=assetsMapper.searchbyuser(search,currentUser.getUserid());
		JsonUtil.printJson(response, list);
	}
	
	//转移
	@RequestMapping("transfer")
	public void transfer(HttpServletResponse response,
			Long id,Integer deptId,String userid,String place,String reason,Integer audit){
		ResultJson result=new ResultJson();
		try {
			//System.out.println("deptId="+deptId+"|userid="+userid);

			//reason="垃圾";
			//String st="123";
			FixedAssets fa=fixedAssetsService.get(id);
			assetsFlowService.save1(id,3,deptId,userid,fa.getStatus(),reason,place,audit);
			fixedAssetsService.transfer(id,deptId,userid);
			result.setSuccess(true);
			
		} catch (Exception e) {
			logger.error("转移异常",e);
			result.setMsg("转移出现异常");
		}
		JsonUtil.printJson(response, result);
	}
	//转移
	@RequestMapping("transfer/dept")
	public void transferData(HttpServletResponse response){
		List<Department> listDept = AddressBookApi.listDept("1");
		JSONObject json=new JSONObject();
		List<Place> place=roletype.searchplace();
		json.put("success", true);
		json.put("place", place);
		json.put("list", listDept);
		JsonUtil.printJson(response, json);
	}
	
	//部门资产
	@RequestMapping("other")
	public String other(HttpServletRequest request,String code,String state){
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
		return "redirect:other/list.do?state="+state;
	}
	@RequestMapping("other/list")
	public String otherList(Model model,String state){
		try {
			Employee currentUser = UserContext.getCurrentUser();
			List<Role> roles = currentUser.getRoles();
			List<FixedAssets> list=new ArrayList<>();
			for (int i = 0; i < roles.size(); i++) {
				Integer deptId = roles.get(i).getDept().getId();
				if(roles.get(i).getDept().getId()==1){
					/*model.addAttribute("isCompany", true);*/
					/*deptId=null;*/
				}
				List<FixedAssets> fixedAssets= fixedAssetsService
						.getAssetsInfoByDeptId(deptId);
				list.addAll(fixedAssets);
				model.addAttribute("fa", list);
				return "assets/listbumen";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "error.do";
	}
	//企业资产
	@RequestMapping("qiye")
	public String qiye(HttpServletRequest request,String code,String state){
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
		return "redirect:qiye/list.do?state="+state;
	}
	@RequestMapping("qiye/list")
	public String qiyeList(Model model,String state){
		try {
			Employee currentUser = UserContext.getCurrentUser();
			List<Role> roles = currentUser.getRoles();
			List<FixedAssets> list=new ArrayList<>();
			for (int i = 0; i < roles.size(); i++) {
				Integer deptId = roles.get(i).getDept().getId();
				if(roles.get(i).getDept().getId()==1){
					model.addAttribute("isCompany", true);
					deptId=null;
					
					List<FixedAssets> fixedAssets= fixedAssetsService
							.getAssetsInfoByDeptId(deptId);
					list.addAll(fixedAssets);
					model.addAttribute("fa", list);
					return "assets/list";
				}

			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "error.do";
	}
	
	//个人资产
	@RequestMapping("personal")
	public String personal(HttpServletRequest request,String code,String state){
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
		return "redirect:personal/list.do";
	}
	@RequestMapping("personal/list")
	public String list(Model model){
		try {
			Employee currentUser = UserContext.getCurrentUser();
			List<FixedAssets> fixedAssets= fixedAssetsService.getAssetsInfoByUserid(currentUser.getUserid());
			model.addAttribute("fa", fixedAssets);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "assets/listgeren";
	}
	//
	@RequestMapping("assetsItem")
	public String assetsItem(Model model,Long id){
		FixedAssets fa=fixedAssetsService.get(id);
		model.addAttribute("fa", fa);
		return "assets/item";
	}
	//二维码
	@RequestMapping("sweep")
	public String sweep(@ModelAttribute("codePath")String codePath){
		return "assets/sweep";
	}
	
	@RequestMapping("save")//新增资产
	public void save(FixedAssets fa,Model model,HttpServletRequest request,
			HttpServletResponse response){
		ResultJson result=new ResultJson();
		try {
			System.out.println(fa.toString());
			fa.setPandian(2);
			fixedAssetsService.save(fa);
			if(fa.getId()!=null){
				assetsFlowService.savecreate(fa.getId(),6,fa.getDept().getId(),fa.getUser().getUserid(),1,null,0);
				model.addAttribute("id", fa.getId());
			}
			String realPath=request.getServletContext().getRealPath("/qrcode");
			String fileName="/qrcode"+fa.getId()+".jpg";
			String codePath=realPath+fileName;
			String text=Consts.BASE_URL+"assets/url.do?id="+fa.getId();
			MatrixToImageWriter.buildQrcode(codePath, text);
			fa.setCodePath("qrcode/"+fileName);
			fixedAssetsService.update(fa);
			
			result.setSuccess(true);
			result.setMsg("qrcode/"+fileName);
		} catch (Exception e) {
			logger.error("保存异常",e);
			result.setSuccess(false);
		}
		JsonUtil.printJson(response, result);
	}
	@RequestMapping("create")//重新生成二维码
	public void create(Model model,HttpServletResponse response,HttpServletRequest request,Integer id){
		ResultJson result=new ResultJson();
		try {
			List<FixedAssets> assets=assetsMapper.getAssetsInfoByDeptId(null);
			FixedAssets fa;
			for(int i=0;i<assets.size();i++)
			{   fa=assets.get(i);
				String realPath=request.getServletContext().getRealPath("/qrcode");
				String fileName="/qrcode"+fa.getId()+".jpg";
				String codePath=realPath+fileName;
				String text=Consts.BASE_URL+"assets/url.do?id="+fa.getId();
				MatrixToImageWriter.buildQrcode(codePath, text);
				fa.setCodePath("qrcode/"+fileName);
				fixedAssetsService.update(fa);
				result.setSuccess(true);
				result.setMsg("qrcode/"+fileName);
				
			}
		} catch (Exception e) {
			logger.error("生成异常",e);
			result.setSuccess(false);
		}
		JsonUtil.printJson(response, result);
	}
	
	@RequestMapping("save/index")
	public String add(Model model){
		List<Department> listDept = AddressBookApi.listDept("1");
		List<Type1> type=roletype.searchtype();
	    List<Place> place=roletype.searchplace();
		model.addAttribute("depts", listDept);
		model.addAttribute("types", type);
		model.addAttribute("places", place);
		model.addAttribute("code", "ET"+System.currentTimeMillis());
		return "assets/addAssets";
	}
	
	@RequestMapping("url")
	public String sweed(@ModelAttribute("id")Integer id,Model model){
		model.addAttribute("corpid", WechatApi.CORPID);
		return "assets/url";
	}
	/**
	 * 二维码上的URL最终跳转到的资产详情页
	 * @param request
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping("index")
	public String getCode(HttpServletRequest request,String code,String state,Model model){
		if(StringUtils.hasText(code)){
			try {
				Employee employee;//=(Employee) getSession(request,super.employee_session);
				//if(employee==null){
					employee=wechatService.accessToken(code);
					UserContext.setCurrentUser(employee);

				/*	List<Integer> ro=employee.getDepartment();
					if(ro.get(0)!=1)
					{
					  return "redirect:/404.do";
					}*/
					
				//}
				if(employee.getDepartment().get(0)!=1)
				{
					return "redirect:/404.do";//不是企业资产管理部门员工不能查看
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "redirect:/404.do";
			}
		}
		return "redirect:/assets/assets.do?id="+state;
	}
	
	//二维码上资产信息
	@RequestMapping("index1")
	public String getCode1(HttpServletRequest request,String code,String state,Model model){
		if(StringUtils.hasText(code)){
			try {
				Employee employee;//=(Employee) getSession(request,super.employee_session);
				//if(employee==null){
					employee=wechatService.accessToken(code);
					UserContext.setCurrentUser(employee);

				/*	List<Integer> ro=employee.getDepartment();
					if(ro.get(0)!=1)
					{
					  return "redirect:/404.do";
					}*/
					
				//}
				if(employee.getDepartment().get(0)!=1)
				{
					return "redirect:/404.do";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "redirect:/404.do";
			}
		}
		return "redirect:/assets/pandian.do?id="+state;
	}
	
	@RequestMapping("pandian")//通过扫码获得资产信息
	public String assetspandian(Model model,String id){
		FixedAssets fixedAssets= fixedAssetsService.getAssetsInfo(id);
		List<AssetsFlow> flows=assetsFlowService.selectByAssetsId(fixedAssets.getId());
		for (AssetsFlow assetsFlow : flows) {
			if(assetsFlow.getAuditStatus()==2){
				model.addAttribute("audit", true);
				break;
			}
		}
		model.addAttribute("flows", flows);
		model.addAttribute("fa", fixedAssets);
		return "assets/pandian";
	}
	
	@RequestMapping("pandian1")//执行盘点操作
	public void pandianup(Integer status,Long id,String reason,HttpServletResponse response,Integer audit){
		ResultJson result=new ResultJson();
		try {		
			FixedAssets fa=fixedAssetsService.get(id);
			fa.setPandian(0);
			assetsMapper.updatepandian(fa);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMsg("盘点出现异常");
			logger.error("盘点异常",e);
		}
		JsonUtil.printJson(response, result);
	}
	@RequestMapping("clean")//重新盘点
	public String clean(Model model){
		try {
			assetsMapper.clean(2);
			model.addAttribute("isCompany", true);			
			List<FixedAssets> fixedAssets= fixedAssetsService.getAssetsInfoByDeptId(null);
			model.addAttribute("fa", fixedAssets);			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "assets/list";
	}
	
	/**
	 * 资产详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("assets")
	public String assets(Model model,String id){
		FixedAssets fixedAssets= fixedAssetsService.getAssetsInfo(id);
		List<AssetsFlow> flows=assetsFlowService.selectByAssetsId(fixedAssets.getId());
		List<AssetsFlow> flow=new ArrayList<>();
		for (AssetsFlow assetsFlow : flows) {
			if(assetsFlow.getAuditStatus()==0){
				flow.add(assetsFlow);
			}
			if(assetsFlow.getAuditStatus()==2){
				model.addAttribute("audit", true);
				//break;
			}
		}
		model.addAttribute("flows", flow);
		model.addAttribute("fa", fixedAssets);
		return "assets/assets";
	}
	@RequestMapping("assetsbumen")
	public String assetsbumen(Model model,String id){
		FixedAssets fixedAssets= fixedAssetsService.getAssetsInfo(id);
		List<AssetsFlow> flows=assetsFlowService.selectByAssetsId(fixedAssets.getId());
		List<AssetsFlow> flow=new ArrayList<>();
		for (AssetsFlow assetsFlow : flows) {
			if(assetsFlow.getAuditStatus()==0){
				flow.add(assetsFlow);
			}
			if(assetsFlow.getAuditStatus()==2){
				model.addAttribute("audit", true);
				//break;
			}
		}
		model.addAttribute("flows", flow);
		model.addAttribute("fa", fixedAssets);
		return "assets/assetsbumen";
	}
	
	@RequestMapping("assetsgeren")
	public String assetsgeren(Model model,String id){
		FixedAssets fixedAssets= fixedAssetsService.getAssetsInfo(id);
		List<AssetsFlow> flows=assetsFlowService.selectByAssetsId(fixedAssets.getId());
		List<AssetsFlow> flow=new ArrayList<>();
		for (AssetsFlow assetsFlow : flows) {
			if(assetsFlow.getAuditStatus()==0){
				flow.add(assetsFlow);
			}
			if(assetsFlow.getAuditStatus()==2){
				model.addAttribute("audit", true);
				//break;
			}
		}
		model.addAttribute("flows", flow);
		model.addAttribute("fa", fixedAssets);
		return "assets/assetsgeren";
	}
	
	@RequestMapping("updateStatus")
	public void updateStatus(Integer status,Long id,String reason,HttpServletResponse response,Integer audit){
		ResultJson result=new ResultJson();
		try {
			//fixedAssetsService.updateStatus(status,id);
			
			FixedAssets fa=fixedAssetsService.get(id);
			//String reason="0";
			assetsFlowService.save(id,status,fa.getStatus(),reason,audit);
			fixedAssetsService.transfer(id,null,null);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMsg("更新出现异常");
			logger.error("更新异常",e);
		}
		JsonUtil.printJson(response, result);
	}
	
}
