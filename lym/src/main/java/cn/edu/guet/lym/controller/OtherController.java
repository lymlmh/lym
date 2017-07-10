package cn.edu.guet.lym.controller;

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

import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.Place;
import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.mapper.EmployeeMapper;
import cn.edu.guet.lym.mapper.RoleMapper;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.Consts;
import cn.edu.guet.lym.util.JsonUtil;
import cn.edu.guet.lym.util.ResultJson;

@Controller
@RequestMapping("other")
public class OtherController extends BaseController{
	private static final Logger logger=LoggerFactory.getLogger(OtherController.class);
	@Autowired
    private RoleMapper roleMapper;
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
				if(user.getQuanxian().getIfdidian()==1)
				{
					return "redirect:/other/didian.do";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "404.do";
		
	}
	@RequestMapping("didian")
	public String didian(Model model){
		System.out.println(Consts.BASE_URL);
		List<Place> place=roleMapper.searchplace();
		model.addAttribute("place", place);
		return "other/didian";
	}
	@RequestMapping("delete")
	public void delete(HttpServletResponse response,Long id){
		ResultJson json=new ResultJson();
		try {
			roleMapper.deleteplace(id);
			json.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除地点异常",e);
		}
		JsonUtil.printJson(response, json);
	}
	
	@RequestMapping("update")
	public void update(HttpServletResponse response,Place place){
		ResultJson json=new ResultJson();
		try {
			//System.out.println(role.toString());
			roleMapper.updateplace(place);
			json.setSuccess(true);
		} catch (Exception e) {
			logger.error("保存地点异常",e);
		}
		JsonUtil.printJson(response, json);
	}
}
