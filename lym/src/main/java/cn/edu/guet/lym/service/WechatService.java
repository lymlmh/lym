package cn.edu.guet.lym.service;

import cn.edu.guet.lym.domain.Employee;

public interface WechatService {

	Employee accessToken(String code);

}
