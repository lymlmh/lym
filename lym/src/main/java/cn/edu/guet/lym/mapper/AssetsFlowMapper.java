package cn.edu.guet.lym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.guet.lym.domain.AssetsFlow;

public interface AssetsFlowMapper {

	void save(AssetsFlow flow);
	void save1(AssetsFlow flow);//资产转移添加

	List<AssetsFlow> selectByAssetsId(Long assetsId);
	
	List<AssetsFlow> selectByapplyer(String applyer);

	List<AssetsFlow> selectQueryFlow(@Param("audit")Integer audit, @Param("auditStatus")Integer auditStatus);//查询企业未审核申请
	List<AssetsFlow> selectQueryFlow1(@Param("audit")Integer audit, @Param("auditStatus")Integer auditStatus);//查询企业已审核申请
	List<AssetsFlow> selectQueryFlow2(@Param("audit")Integer audit, @Param("deptId")Integer deptId);//查询部门未审核申请
	List<AssetsFlow> selectQueryFlow3(@Param("audit")Integer audit,@Param("deptId")Integer deptId);//查询部门已审核申请
	List<AssetsFlow> selectDeptId(Integer deptid);

	AssetsFlow get(Long id);

	void updateAudit(AssetsFlow assetsFlow);

}
