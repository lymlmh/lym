package cn.edu.guet.lym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.Quanxian;
import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.mapper.QuanxianMapper;
import cn.edu.guet.lym.service.RoleService;
import cn.edu.guet.lym.service.WechatService;
import cn.edu.guet.lym.util.WechatApi;

import com.alibaba.fastjson.JSONObject;
@Service
public class WechatServiceImpl implements WechatService {
	@Autowired
	private RoleService roleService;
	@Autowired
	private QuanxianMapper quanxianMapper;
	
	@Override
	public Employee accessToken(String code) {
		String access_token=WechatApi.getAccessToken();
		JSONObject userJson=WechatApi.getUserInfo(access_token,code);
		if(userJson.getString("user_ticket")!=null){
			Employee userDetail = WechatApi.getUserDetail(userJson.getString("user_ticket"));
			List<Role> roles=roleService.getRole(userDetail.getUserid());
			//Quanxian quanxian=quanxianMapper.getQaunxian(userDetail.getPartid());
			userDetail.setRoles(roles);
			//userDetail.setQuanxian(quanxian);
			return userDetail;
		}else{
			throw new RuntimeException("非企业成员");
		}
	}
	
}
