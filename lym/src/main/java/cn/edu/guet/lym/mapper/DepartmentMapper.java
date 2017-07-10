package cn.edu.guet.lym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.guet.lym.domain.Department;


public interface DepartmentMapper {

	List<Department> selectByUserid(String userid);

	List<Department> selectAll(@Param("id")Integer id);

	void save(Department department);

	Department get(@Param("id")Integer id);

	void update(Department d);

	void delete(Integer id);
	
	List<Department> search(String search);

}
