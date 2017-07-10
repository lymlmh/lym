package cn.edu.guet.lym.service;

import java.util.List;

import cn.edu.guet.lym.domain.FixedAssets;

public interface FixedAssetsService {

	FixedAssets getAssetsInfo(String state);

	Long save(FixedAssets fa);

	List<FixedAssets> getAssetsInfoByUserid(String userid);

	FixedAssets get(Long id);

	void updateStatus(Integer status, Long id);

	void update(FixedAssets fa);

	List<FixedAssets> getAssetsInfoByDeptId(Integer deptId);
	/**
	 * 转移
	 * @param id
	 * @param deptId	新部门	
	 * @param userid	新成员
	 */
	void transfer(Long id, Integer deptId, String userid);

	List<FixedAssets> search(String search);

}
