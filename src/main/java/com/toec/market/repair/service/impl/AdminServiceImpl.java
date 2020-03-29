package com.toec.market.repair.service.impl;

import java.util.List;

import com.toec.market.repair.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toec.market.repair.entity.AdminExample;
import com.toec.market.repair.mapper.AdminMapper;
import com.toec.market.repair.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{


	@Autowired
	private AdminMapper mapper;
	
	@Transactional(readOnly=true)
	public List<Admin> list() throws Exception {
		return mapper.selectByExample(null);
	}
	@Transactional(readOnly=true)
	public Admin findById(Integer id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}
 
	@Transactional(readOnly=true)
	public List<Admin> selectByExample(AdminExample example) {
		return mapper.selectByExample(example);
	}
	@Transactional
	public void deleteByExample(AdminExample example) {
		  mapper.deleteByExample(example);
	}
	@Transactional
	public void deleteByPrimaryKey(Integer id) {
		 mapper.deleteByPrimaryKey(id);
	}
	@Transactional(readOnly=true)
	public boolean existsWithPrimaryKey(Integer id) {
		 return this.selectByPrimaryKey(id)!=null ? true:false;
	}
	@Transactional
	public Integer insert(Admin e) {
		 return mapper.insert(e);
	}
	@Transactional
	public Integer insertSelective(Admin record) {
		return mapper.insert(record);
	}
	@Transactional(readOnly=true)
	public Admin selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	@Transactional(readOnly=true)
	public Long selectCountByExample(AdminExample example) {
		  List<Admin> selectByExample = mapper.selectByExample(example);
		  if(selectByExample==null){
			  return 0L;
		  }
		  return (long)selectByExample.size();
	}

 
	@Transactional(readOnly=true)
	public Admin selectOneByExample(AdminExample example) {
		   List<Admin> list = mapper.selectByExample(example);
		   if(list!=null && list.size()>0){
			   return list.get(0);
		   }
		  return null;
	}
	@Transactional
	public void updateByExample(Admin t, AdminExample example) {
		mapper.updateByExample(t, example)	;	
	}
	@Transactional
	public void updateByExampleSelective(Admin t, AdminExample example) {
		mapper.updateByExampleSelective(t, example)	;	
	}
	@Transactional
	public int updateByPrimaryKey(Admin record) {
		return mapper.updateByPrimaryKey(record);
	}
	@Transactional
	public int updateByPrimaryKeySelective(Admin record) {
		return mapper.updateByPrimaryKeySelective(record);
	}
	@Transactional
	@Override
	public void deleteBatch(List<Integer> ids) {
		if(ids!=null && ids.size()>0){
			for(Integer id :ids){
				mapper.deleteByPrimaryKey(id);
			}
		}
	}
	@Transactional
	@Override
	public void deleteBatch(Integer[] ids) {
		if(ids != null && ids.length > 0){
			for(Integer id :ids){
				mapper.deleteByPrimaryKey(id);
			}
		}
	}
	 
}
