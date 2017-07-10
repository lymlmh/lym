package cn.edu.guet.lym.controller;

import java.net.URLDecoder;
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

import com.alibaba.fastjson.JSONObject;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.Place;
import cn.edu.guet.lym.domain.Quanxian;
import cn.edu.guet.lym.mapper.EmployeeMapper;
import cn.edu.guet.lym.mapper.QuanxianMapper;
import cn.edu.guet.lym.mapper.RoleMapper;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.Consts;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.ResultJson;

@Controller
@RequestMapping("quanxian")
public class QuanxianController extends BaseController{
	private static final Logger logger=LoggerFactory.getLogger(QuanxianController.class);
	@Autowired
    private QuanxianMapper quanxianMapper;
	@Autowired
    private EmployeeMapper empMapper;
	@Autowired
	private WechatService wechatService;
	@Autowired
	private EmployeeMapper employee1;
	
	@RequestMapping("getCode")
	public String getCode(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				Employee employee=wechatService.accessToken(code);
				setSession(request, employee);
				Employee user=employee1.selectbyuserid(employee.getUserid());
				String st=user.getUserid();
				String id="1";
				if(user.getQuanxian().getIfjuese()==1||st.equals(id))
				{
					return "redirect:/quanxian/index.do";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "404.do";
		
	}
	
	@RequestMapping("index")
	public String quanxian(Model model){
		System.out.println(Consts.BASE_URL);
		List<Quanxian> quanxian=quanxianMapper.search();
		model.addAttribute("quanxian", quanxian);
		return "other/quanxian";
	}
	@RequestMapping("delete")
	public void delete(HttpServletResponse response,Integer id){
		ResultJson json=new ResultJson();
		try {
			//roleMapper.deleteplace(id);
			quanxianMapper.delete(id);
			json.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除角色异常",e);
		}
		JsonUtil.printJson(response, json);
	}
	
	@RequestMapping("save")
	public void save(HttpServletResponse response,Quanxian quanxian){
		ResultJson json=new ResultJson();
		try {
			quanxianMapper.save(quanxian);
			//System.out.println(role.toString());
			//roleMapper.updateplace(place);
			json.setSuccess(true);
		} catch (Exception e) {
			logger.error("保存角色异常",e);
		}
		JsonUtil.printJson(response, json);
	}
	
	@RequestMapping("getCodepower")
	public String getCodepower(HttpServletRequest request,String code,String state){
		if(StringUtils.hasText(code)){
			try {
				Employee employee=wechatService.accessToken(code);
				setSession(request, employee);
				Employee user=employee1.selectbyuserid(employee.getUserid());
				String st=user.getUserid();
				String id="1";
				if(user.getQuanxian().getIfquanxian()==1||st.equals(id))
				{
					return "redirect:/quanxian/power.do";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "404.do";
		
	}
	@RequestMapping("power")
	public String power(Model model){
		System.out.println(Consts.BASE_URL);
		List<Quanxian> quanxian=quanxianMapper.search();
		model.addAttribute("quanxian", quanxian);
		return "other/fenpei";
	}
	@RequestMapping("update")
	public void update(Quanxian quanxian,HttpServletResponse response){
		ResultJson result=new ResultJson();
		try {
			quanxianMapper.update(quanxian);
			result.setMsg("更新权限成功");
			result.setSuccess(true);
		} catch (Exception e2) {
			result.setMsg("更新权限失败");
			result.setSuccess(false);
			logger.error("更新权限失败",e2);
		}
		JsonUtil.printJson(response, result);
	}
	
	@RequestMapping("part")
	public String part(Model model){
		System.out.println(Consts.BASE_URL);
		List<Quanxian> quanxian=quanxianMapper.search();
		List<Employee> employee=empMapper.selectall();
		model.addAttribute("quanxian", quanxian);
		model.addAttribute("employee", employee);
		return "other/part";
	}
	
	@RequestMapping("updateemp")
	public void updatempe(Employee e,HttpServletResponse response){
		ResultJson result=new ResultJson();
		try {
			Employee user = empMapper.getItem(e.getUserid());
			user.setPartid(e.getPartid());
			empMapper.updatepart(e);
			result.setSuccess(true);
		} catch (Exception e2) {
			result.setMsg("角色分配失败1");
			result.setSuccess(false);
			logger.error("角色分配失败",e2);
		}
		JsonUtil.printJson(response, result);
	}
	@RequestMapping("jueseupdate/index")
	public String updateIndex(Integer id,Model model){
		Quanxian juese=quanxianMapper.getQaunxian(id);
		model.addAttribute("juese", juese);
		return "other/update";
	}
	
	@RequestMapping("jueseupdate")
	public void update(Quanxian q,
			HttpServletResponse response,HttpServletRequest request){
		ResultJson result=new ResultJson();
		try {
			request.setCharacterEncoding("UTF-8");
			String name=URLDecoder.decode(request.getParameter("name"),"UTF-8");
			q.setName(name);
			quanxianMapper.nameupdate(q);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("更新角色出现异常");
			logger.error("更新角色异常");
		}
		JsonUtil.printJson(response, result);
	}
}
