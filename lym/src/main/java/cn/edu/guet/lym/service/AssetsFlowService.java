package cn.edu.guet.lym.service;

import java.util.List;

import cn.edu.guet.lym.domain.AssetsFlow;

public interface AssetsFlowService {

	void save(Long id,int newStatus,int oldStatus,String reason,Integer audit);
	void save1(Long id,int newStatus,Integer deptId, String userid,int oldStatus,String reason,String place,Integer audit);
	void savecreate(Long id,int newStatus,Integer deptId, String userid,int oldStatus,String reason,Integer audit);
	List<AssetsFlow> selectByAssetsId(Long id);

	List<AssetsFlow> selectQueryFlow(Integer audit);

	AssetsFlow get(Long id);

	void audit(Long id, Integer aduitAdvice);
	void audit1(Long id, Integer aduitAdvice);
	void sendmsg();

}
