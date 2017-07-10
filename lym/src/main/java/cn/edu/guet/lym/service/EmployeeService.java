package cn.edu.guet.lym.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.edu.guet.lym.domain.Employee;

public interface EmployeeService {

	List<Employee> selectAllEmployees();

	void save(Employee employee);

	Employee getItem(String userid);

	List<Employee> search(String search);

	void update(Employee weixin);
	
	JSONObject delete(String id);
	
}
