package cn.edu.guet.lym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.guet.lym.domain.Quanxian;
import cn.edu.guet.lym.domain.Role;

public interface QuanxianMapper {
	
	void update(Quanxian quanxian);
	void save(Quanxian quanxian);
	void delete(@Param("id")Integer id);
	List <Quanxian> search();
	Quanxian getQaunxian(Integer id);
	void nameupdate(Quanxian quanxian);
	
}
