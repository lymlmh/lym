package cn.edu.guet.lym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;
import cn.edu.guet.lym.domain.FixedAssets;
import cn.edu.guet.lym.mapper.FixedAssetsMapper;
import cn.edu.guet.lym.service.FixedAssetsService;

@Service
public class FixedAssetsServiceImpl implements FixedAssetsService {
	@Autowired
	private FixedAssetsMapper fixedAssetsMapper;
	
	@Override
	public List<FixedAssets> search(String search) {
		return fixedAssetsMapper.search(search);
	}

	@Override
	public void transfer(Long id, Integer deptId, String userid) {
		FixedAssets fixedAssets = fixedAssetsMapper.get(id);
/*		fixedAssets.setOldUserid(fixedAssets.getUser().getUserid());
		fixedAssets.setOldDeptid(fixedAssets.getDept().getId());
		Employee user=new Employee();
		user.setUserid(userid);
		Department dept=new Department();
		dept.setId(deptId);
		fixedAssets.setUser(user);
		fixedAssets.setDept(dept);*/
		fixedAssets.setStatus(4);
		//fixedAssets.setStatus(3);
		fixedAssetsMapper.update(fixedAssets);
	}

	@Override
	public FixedAssets getAssetsInfo(String state) {
		return fixedAssetsMapper.getAssetsInfo(state);
	}

	@Override
	public Long save(FixedAssets fa) {
		fa.setStatus(0);
		//fa.setPandian(2);
		return fixedAssetsMapper.save(fa);
	}

	@Override
	public List<FixedAssets> getAssetsInfoByUserid(String userid) {
		return fixedAssetsMapper.getAssetsInfoByCurUser(userid);
	}

	@Override
	public FixedAssets get(Long id) {
		return fixedAssetsMapper.get(id);
	}

	@Override
	public void updateStatus(Integer status, Long id) {
		//fixedAssetsMapper.updateStatus(status,id);
	}

	@Override
	public void update(FixedAssets fa) {
		fixedAssetsMapper.update(fa);
	}

	@Override
	public List<FixedAssets> getAssetsInfoByDeptId(Integer deptId) {
		return fixedAssetsMapper.getAssetsInfoByDeptId(deptId);
	}
	
}
