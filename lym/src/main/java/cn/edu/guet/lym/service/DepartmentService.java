package cn.edu.guet.lym.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;

public interface DepartmentService {

	List<Department> selectAll(Integer id);

	void save(Department department);

	Department get(Integer id);

	JSONObject update(Department d);

	JSONObject saveToWeixin(Department d);

	JSONObject delete(Integer id);

	void updateLocal(Department weixin);
	List<Department> search(String search);
	
}
