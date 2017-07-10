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



import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;

import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.mapper.EmployeeMapper;

import cn.edu.guet.lym.service.RoleService;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.AddressBookApi;
import cn.edu.guet.lym.util.Consts;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.ResultJson;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
	private static final Logger logger=LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private WechatService wechatService;
	/*@Autowired
	private EmployeeMapper employee1;*/
/*	@Autowired
	private RoleMapper roleMapper;*/
	
	@RequestMapping("delete")
	public void delete(HttpServletResponse response,Long id){
		ResultJson json=new ResultJson();
		try {
			roleService.delete(id);
			json.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除角色异常",e);
		}
		JsonUtil.printJson(response, json);
	}
	
	
	@RequestMapping("save")
	public void save(HttpServletResponse response,Role role){
		ResultJson json=new ResultJson();
		try {
			//System.out.println(role.toString());
			roleService.save(role);
			json.setSuccess(true);
		} catch (Exception e) {
			logger.error("保存角色异常",e);
		}
		JsonUtil.printJson(response, json);
	}
	@RequestMapping("getCode")
	public String getCode(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				Employee employee=wechatService.accessToken(code);
				setSession(request, employee);
				Employee user=empMapper.selectbyuserid(employee.getUserid());
				if(user.getQuanxian().getIfjuese()==1)
				{
					return "redirect:/role/test.do";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "404.do";
		
	}
	//管理员设置页面进入接口
	@RequestMapping("test")
	public String index1(Model model){
		System.out.println(Consts.BASE_URL);
		List<Role> list=roleService.selectAllRole();
		model.addAttribute("roles", list);
		return "role/test";
	}	
	
	//创建角色首页
	@RequestMapping("save/index")
	public String saveIndex(Model model){
		List<Department> listDept = AddressBookApi.listDept("1");
		List<Role> roleList=roleService.selectAllRole();
		List<Department> newList=new ArrayList<>();
		for (Department dept : listDept) {
			boolean flag=true;
			for (Role role : roleList) {
				if(role.getDept().getId()==dept.getId()){
					flag=false;
					break;
				}
			}
			if(flag){
				newList.add(dept);
			}
		}
		model.addAttribute("depts", newList);
		return "role/save";
	}
	
	
	
	@RequestMapping("search")
	public void search(HttpServletResponse response,String search){
		List<Role> d=roleService.search(search);
		JsonUtil.printJson(response, d);
	}
	
}
