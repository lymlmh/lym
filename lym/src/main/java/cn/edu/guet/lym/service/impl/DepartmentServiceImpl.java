package cn.edu.guet.lym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.mapper.DepartmentMapper;
import cn.edu.guet.lym.service.DepartmentService;
import cn.edu.guet.lym.util.AddressBookApi;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentMapper departmentMapper;
	
	
	@Override
	public void updateLocal(Department weixin) {
		departmentMapper.update(weixin);
	}
	@Override
	public List<Department> selectAll(Integer id) {
		return departmentMapper.selectAll(id);
	}
	@Override
	public void save(Department department) {
		departmentMapper.save(department);
	}
	@Override
	public Department get(Integer id) {	
		return departmentMapper.get(id);
	}
	@Override
	public JSONObject update(Department d) {
		JSONObject updateDept = AddressBookApi.updateDept(d);
		if(updateDept.getInteger("errcode")==0){
			departmentMapper.update(d);
		}
		return updateDept;
	}
	@Override
	public JSONObject saveToWeixin(Department d) {
		JSONObject createDept = AddressBookApi.createDept(d);
		return createDept;
	}
	@Override
	public JSONObject delete(Integer id) {
		JSONObject deleteDept = AddressBookApi.deleteDept(String.valueOf(id));
		if(deleteDept.getInteger("errcode")==0){
			departmentMapper.delete(id);
		}
		return deleteDept;
	}
	
	@Override
	public List<Department> search(String search) {
		if(StringUtils.hasText(search)){
			return departmentMapper.search(search);
		}
		return null;
	}
	
}
