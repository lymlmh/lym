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
import cn.edu.guet.lym.mapper.DepartmentMapper;
import cn.edu.guet.lym.mapper.EmployeeMapper;
import cn.edu.guet.lym.service.DepartmentService;
import cn.edu.guet.lym.service.EmployeeService;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.AddressBookApi;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.ResultJson;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("Employee")
public class EmployeeController extends BaseController{
	private static final Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private WechatService wechatService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeMapper employee1;
	
	@RequestMapping("getUserSimpleList")
	public void getUserSimpleList(String deptId,HttpServletResponse response){
		JSONObject json=new JSONObject();
		try {
			int[] status={0};
			List<Employee> userSimpleList = AddressBookApi.getUserSimpleList(deptId, "0", status);
			json.put("success", true);
			json.put("employees", userSimpleList);
		} catch (Exception e) {
			json.put("success", false);
			logger.error("获取成员列表异常",e);
		}
		JsonUtil.printJson(response, json);
	}
	@RequestMapping("search")
	public void search(HttpServletResponse response,String search){
		List<Employee> e=employeeService.search(search);
		JsonUtil.printJson(response, e);
	}
	
	
	@RequestMapping("save")
	public void save(HttpServletResponse response,Employee e){
		ResultJson result=new ResultJson();
		try {
			boolean daleteUser = AddressBookApi.createUser(e);
			if(daleteUser){
				result.setMsg("新增成员成功");
				result.setSuccess(true);
			}
		} catch (Exception e1) {
			result.setSuccess(false);
			logger.error("新增成员失败",e1);
		}
		JsonUtil.printJson(response, result);
	}
	
	@RequestMapping("save/index")
	public String saveIndex(Model model){
		List<Department> depts=departmentService.selectAll(null);
		model.addAttribute("depts", depts);
		return "employee/save";
	}
	
	@RequestMapping("delete")
	public void delete(HttpServletResponse response,String userid){
		ResultJson result=new ResultJson();
		try {
			JSONObject daleteUser = employeeService.delete(userid);
			if(daleteUser.getInteger("errcode")==0){
				result.setMsg("删除成员成功");
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("删除成员失败",e);
		}
		JsonUtil.printJson(response, result);
	}
	
	
	@RequestMapping("update")
	public void update(Employee e,HttpServletResponse response){
		ResultJson result=new ResultJson();
		try {
			Employee user = AddressBookApi.getUser(e.getUserid());
			user.setEmail(e.getEmail());
			user.setName(e.getName());
			user.setGender(e.getGender());
			user.setWeixinid(e.getWeixinid());
			user.setMobile(e.getMobile());
			user.setPosition(e.getPosition());
			user.setDepartment(e.getDepartment());
			user.setDeptName(e.getDeptName());
			//user.setDepartment(e.getDepartment());
			user.setDeptId(e.getDeptId());
			employee1.update(user);
			boolean updateUser = AddressBookApi.updateUser(e);
			if(updateUser){
				result.setMsg("更新成员成功");
				result.setSuccess(true);
			}
		} catch (Exception e2) {
			result.setMsg("更新成员失败");
			result.setSuccess(false);
			logger.error("更新成员失败",e2);
		}
		JsonUtil.printJson(response, result);
	}
	
	@RequestMapping("update/index")
	public String updateIndex(String userid,Model model){
		if(userid!=null){
			Employee e=employeeService.getItem(userid);
			model.addAttribute("employee", e);
		}
		List<Department> depts=departmentService.selectAll(null);
		model.addAttribute("depts", depts);
		return "employee/update";
	}
	
	@RequestMapping("getCode")
	public String getCode(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				Employee employee=wechatService.accessToken(code);
				setSession(request, employee);
				Employee user=employee1.selectbyuserid(employee.getUserid());
				if(user.getQuanxian().getIfrenyuan()==1)
				{
					return "redirect:/Employee/index.do";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "404.do";
		
	}
	
	@RequestMapping("item")
	public String item(Model model,String userid){
		if(userid!=null){
			Employee e=employeeService.getItem(userid);
			model.addAttribute("employee", e);
		}
		return "employee/item";
	}
	
	@RequestMapping("index")
	public String index(Model model){
		List<Employee> employees=employeeService.selectAllEmployees();
		int[] status={0};
		List<Employee> weixinList = AddressBookApi.getUserList("1", "1", status);
		List<Employee> newList=new ArrayList<>();
		//同步
		try {
		if(employees==null || employees.size()==0){
			newList=weixinList;
		}else{
			for (Employee weixin : weixinList) {
				boolean flag=true;
				for (Employee employee : employees) {
					if(employee.getUserid().equals(weixin.getUserid())){
						List<Department> listDept = AddressBookApi.listDept(String.valueOf(weixin.getDepartment().get(0)));
						String dept=listDept.get(0).getName();
						weixin.setDeptName(dept);
						weixin.setDeptId(weixin.getDepartment().get(0));
						employeeService.update(weixin);
						flag=false;
						break;
					}
				}
				if(flag){
					newList.add(weixin);
					flag=false;
				}
			}
		}
		for (int i = 0; i < newList.size(); i++) {
			Employee user = newList.get(i);
			List<Department> listDept = AddressBookApi.listDept(String.valueOf(user.getDepartment().get(0)));
			//List<Department> depts=departmentMapper.selectByUserid(userid);
			String dept=listDept.get(0).getName();
			user.setDeptName(dept);
			user.setDeptId(listDept.get(0).getId());
			employeeService.save(user);
		}
		} catch (Exception e) {
			logger.error("保存异常",e);
		}
		model.addAttribute("employees", weixinList);
		return "employee/index";
	}
}
