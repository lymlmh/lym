package cn.edu.guet.lym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.Login;
import cn.edu.guet.lym.domain.Place;
import cn.edu.guet.lym.domain.Role;
import cn.edu.guet.lym.domain.Type1;

public interface RoleMapper {

	List<Role> getRole(String userid);

	List<Role> selectAllRole();

	void save(Role role);
	
	Role get(@Param("id")Integer id);
	
	Role getemp(@Param("deptid")Integer deptid);

	void delete(Long id);
	
	
	List<Role> search(String search);
	
	List<Type1> searchtype();
	
	List<Place> searchplace();
	
	void deleteplace(Long id);
	
	void updateplace(Place place);
	
	Login searchlogin(String name);

}
