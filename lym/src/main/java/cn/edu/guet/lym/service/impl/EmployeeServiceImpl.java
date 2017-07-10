package cn.edu.guet.lym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.mapper.EmployeeMapper;
import cn.edu.guet.lym.service.EmployeeService;
import cn.edu.guet.lym.util.AddressBookApi;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	@Override
	public void update(Employee weixin) {
		employeeMapper.update(weixin);
	}

	@Override
	public List<Employee> selectAllEmployees() {
		return employeeMapper.selectAllEmployees();
	}

	@Override
	public void save(Employee employee) {
		employeeMapper.save(employee);
		List<Integer> department = employee.getDepartment();
		for (int i = 0; i < department.size(); i++) {
			employeeMapper.saveDept(employee.getUserid(),department.get(i));
		}
	}

	@Override
	public Employee getItem(String userid) {
		//Employee e=employeeMapper.getItem(userid);
		Employee user = AddressBookApi.getUser(userid);
		List<Department> listDept = AddressBookApi.listDept(String.valueOf(user.getDepartment().get(0)));
		//List<Department> depts=departmentMapper.selectByUserid(userid);
		String dept=listDept.get(0).getName();
		int deptid=listDept.get(0).getId();
		user.setDeptName(dept);
		user.setDeptId(deptid);
		return user;
	}

	@Override
	public List<Employee> search(String search) {
		if(StringUtils.hasText(search)){
			return employeeMapper.search(search);
		}
		return null;
	}
	
	@Override
	public JSONObject delete(String id) {
		JSONObject deleteEmp = AddressBookApi.daleteUser(String.valueOf(id));
		if(deleteEmp.getInteger("errcode")==0){
			employeeMapper.delete(id);
		}
		return deleteEmp;
	}
}
