package cn.edu.guet.lym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.guet.lym.domain.FixedAssets;

public interface FixedAssetsMapper {

	FixedAssets getAssetsInfo(String id);

	Long save(FixedAssets fa);

	void update(FixedAssets fixedAssets);
	void updatepandian(FixedAssets fixedAssets);

	List<FixedAssets> getAssetsInfoByCurUser(@Param("userid")String userid);

	FixedAssets get(Long id);

	List<FixedAssets> getAssetsInfoByDeptId(Integer deptId);

	List<FixedAssets> search(String search);
	List<FixedAssets> searchbyuser(@Param("search")String search,@Param("userid")String userid);
	void clean(Integer pandian);
	List<FixedAssets> seearchall();
}
