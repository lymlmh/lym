package cn.edu.guet.lym.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.FixedAssets;
import cn.edu.guet.lym.domain.Login;
import cn.edu.guet.lym.domain.Place;
import cn.edu.guet.lym.domain.Quanxian;
import cn.edu.guet.lym.mapper.EmployeeMapper;
import cn.edu.guet.lym.mapper.FixedAssetsMapper;
import cn.edu.guet.lym.mapper.QuanxianMapper;
import cn.edu.guet.lym.mapper.RoleMapper;
import cn.edu.guet.lym.service.DepartmentService;
import cn.edu.guet.lym.service.EmployeeService;
import cn.edu.guet.lym.util.AddressBookApi;
import cn.edu.guet.lym.util.Consts;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.ResultJson;

@Controller
@RequestMapping("index")
public class IndexController extends BaseController{
	private static final Logger logger=LoggerFactory.getLogger(OtherController.class);
	@Autowired
    private RoleMapper roleMapper;
	@Autowired
	private FixedAssetsMapper assetsMapper;
	@Autowired
	private DepartmentService departmentService;;
	@Autowired
    private EmployeeMapper empMapper;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
    private QuanxianMapper quanxianMapper;
	private int num;
	@RequestMapping("login")
	public void login(HttpServletResponse response,Login login){
		ResultJson json=new ResultJson();
		try {
			//System.out.println(role.toString());
			Login denglu=roleMapper.searchlogin(login.getName());
			if(denglu!=null)
			{
				if(login.getPass().equals(denglu.getPass())){
					num=1;
					json.setSuccess(true);
				}
				else{
					json.setSuccess(false);
				}
			}
			else{
				json.setSuccess(false);
			}
			
		} catch (Exception e) {
			logger.error("登录异常",e);
		}
		JsonUtil.printJson(response, json);
	}
	
	@RequestMapping("main")
	public String main(Model model){
		System.out.println(Consts.BASE_URL);
		List<FixedAssets> list=new ArrayList<>();
		List<FixedAssets> assets=assetsMapper.seearchall();
		list.addAll(assets);
		
		model.addAttribute("assets", list);
		return "../asset";
	}
	@RequestMapping("code")
	public String code(@ModelAttribute("codePath")String codePath){
		return "../code";
	}
	@RequestMapping("deptindex")
	public String deptindex(Model model){
		try {
			List<Department> depts=departmentService.selectAll(null);
			List<Department> listDept = AddressBookApi.listDept("1");
			List<Department> newList=new ArrayList<>();
			//同步
			if(depts==null || depts.size()==0){
				newList=listDept;
			}else{
				for (Department weixin : listDept) {
					boolean flag=true;
					for (Department dept : depts) {
						if(dept.getId()==weixin.getId()){
							departmentService.updateLocal(weixin);
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
				departmentService.save(newList.get(i));
			}
			model.addAttribute("depts", listDept);
		} catch (Exception e) {
			logger.error("保存异常",e);
		}
		return "../dept";
	}
	
	@RequestMapping("empindex")
	public String empindex(Model model){
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
		return "../emp";
	}
	@RequestMapping("empsave/index")
	public String empsave(Model model){
		List<Department> depts=departmentService.selectAll(null);
		model.addAttribute("depts", depts);
		return "../empsave";
	}
	@RequestMapping("empupdate/index")
	public String empupdate(String userid,Model model){
		if(userid!=null){
			Employee e=employeeService.getItem(userid);
			model.addAttribute("employee", e);
		}
		List<Department> depts=departmentService.selectAll(null);
		model.addAttribute("depts", depts);
		return "../empupdate";
	}
	@RequestMapping("deptsave/index")
	public String deptsave(Model model){
		List<Department> depts=departmentService.selectAll(null);
		model.addAttribute("depts", depts);
		return "../deptsave";
	}
	@RequestMapping("deptupdate/index")
	public String deptupdate(Integer id,Model model){
		List<Department> depts=departmentService.selectAll(id);
		model.addAttribute("depts", depts);
		model.addAttribute("dept",departmentService.get(id));
		return "../deptupdate";
	}
	@RequestMapping("jueseindex")
	public String juese(Model model){
		System.out.println(Consts.BASE_URL);
		List<Quanxian> quanxian=quanxianMapper.search();
		model.addAttribute("juese", quanxian);
		return "../juese";
	}
	@RequestMapping("juesesave/index")
	public String juesesave(Model model){
		return "../juesesave";
	}
	@RequestMapping("jueseupdate/index")
	public String jueseupdate(Integer id,Model model){
		Quanxian juese=quanxianMapper.getQaunxian(id);
		model.addAttribute("juese", juese);
		return "../jueseupdate";
	}
	@RequestMapping("part")
	public String part(Model model){
		System.out.println(Consts.BASE_URL);
		List<Quanxian> quanxian=quanxianMapper.search();
		List<Employee> employee=empMapper.selectall();
		model.addAttribute("quanxian", quanxian);
		model.addAttribute("employee", employee);
		return "../juesefenpei";
	}
	@RequestMapping("quanxian")
	public String power(Model model){
		System.out.println(Consts.BASE_URL);
		List<Quanxian> quanxian=quanxianMapper.search();
		model.addAttribute("quanxian", quanxian);
		return "../quanxian";
	}
}
