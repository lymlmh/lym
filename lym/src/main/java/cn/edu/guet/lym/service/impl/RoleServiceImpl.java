package cn.edu.guet.lym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.mapper.RoleMapper;
import cn.edu.guet.lym.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public void delete(Long id) {
		roleMapper.delete(id);
	}

	@Override
	public void save(Role role) {
		roleMapper.save(role);
	}

	@Override
	public List<Role> getRole(String userid) {
		return roleMapper.getRole(userid);
	}

	@Override
	public List<Role> selectAllRole() {
		return roleMapper.selectAllRole();
	}
	
	@Override
	public Role get(Integer id) {	
		return roleMapper.get(id);
	}
	
	@Override
	public List<Role> search(String search) {
		if(StringUtils.hasText(search)){
			return roleMapper.search(search);
		}
		return null;
	}

}
