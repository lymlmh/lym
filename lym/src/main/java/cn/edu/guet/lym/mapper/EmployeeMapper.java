package cn.edu.guet.lym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.guet.lym.domain.Employee;

public interface EmployeeMapper {

	List<Employee> selectAllEmployees();

	void save(Employee employee);

	void saveDept(@Param("userid")String userid, @Param("deptId")Integer integer);

	Employee getItem(String userid);

	List<Employee> search(String search);

	void update(Employee weixin);
	void updaterole(Employee employee);
	void updatepart(Employee employee);
	
	void delete(String userid);
	List<Employee> selectall();
	Employee selectbyuserid(String userid);
	
}
