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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.mapper.EmployeeMapper;
import cn.edu.guet.lym.service.DepartmentService;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.AddressBookApi;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.ResultJson;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("dept")
public class DepartmentController extends BaseController {
	private static final Logger logger=LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private WechatService wechatService;
	@Autowired
	private EmployeeMapper employee1;
	@RequestMapping("delete")
	public void delete(Integer id,HttpServletResponse response){
		ResultJson result=new ResultJson();
		try {
			JSONObject json=departmentService.delete(id);
			if(json.getInteger("errcode")==0){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setMsg(json.getString("errmsg"));
			}
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("删除部门异常",e);
		}
		JsonUtil.printJson(response, result);
		
	}
	
	@RequestMapping("save")
	public void save(Department d,HttpServletResponse response){
		ResultJson result=new ResultJson();
		try {
			System.out.println(d.toString());
			JSONObject json=departmentService.saveToWeixin(d);
			if(json.getInteger("errcode")==0){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setMsg(json.getString("errmsg"));
			}
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("保存部门异常",e);
		}
		JsonUtil.printJson(response, result);
	}
	
	@RequestMapping("save/index")
	public String saveIndex(Model model){
		List<Department> depts=departmentService.selectAll(null);
		model.addAttribute("depts", depts);
		return "dept/save";
	}
	
	@RequestMapping("update/index")
	public String updateIndex(Integer id,Model model){
		List<Department> depts=departmentService.selectAll(id);
		model.addAttribute("depts", depts);
		model.addAttribute("dept",departmentService.get(id));
		return "dept/update";
	}
	
	@RequestMapping("update")
	public void update(Department d,
			HttpServletResponse response,HttpServletRequest request){
		ResultJson result=new ResultJson();
		try {
			request.setCharacterEncoding("UTF-8");
			String name=URLDecoder.decode(request.getParameter("name"),"UTF-8");
			//name=new String(name.getBytes(),"UTF-8");
			d.setName(name);
			JSONObject json= departmentService.update(d);
			if(json.getInteger("errcode")==0){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setMsg(json.getString("errmsg"));
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("更新部门出现异常");
			logger.error("更新部门异常");
		}
		JsonUtil.printJson(response, result);
	}
	
	@RequestMapping("getCode")
	public String getCode(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				
				Employee employee=wechatService.accessToken(code);
				setSession(request, employee);
				Employee user=employee1.selectbyuserid(employee.getUserid());
				if(user.getQuanxian().getIfbumen()==1)
				{
					return "redirect:/dept/index.do";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		
		return "404.do";
	}
	
	@RequestMapping("index")
	public String index(Model model){
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
		return "dept/index";
	}
	
	@RequestMapping("search")
	public void search(HttpServletResponse response,String search){
		List<Department> d=departmentService.search(search);
		JsonUtil.printJson(response, d);
	}
}
