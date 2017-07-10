package cn.edu.guet.lym.service;

import java.util.List;

import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.Role;

public interface RoleService {

	List<Role> getRole(String userid);

	List<Role> selectAllRole();

	void save(Role role);
	
	Role get(Integer id);

	void delete(Long id);
	List<Role> search(String search);

}
