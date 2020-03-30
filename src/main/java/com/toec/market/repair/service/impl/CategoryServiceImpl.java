package com.toec.market.repair.service.impl;

import java.util.List;

import com.toec.market.repair.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.toec.market.repair.entity.CategoryExample;
import com.toec.market.repair.mapper.CategoryMapper;
import com.toec.market.repair.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryMapper mapper;

	@Transactional(readOnly=true)
	public List<Category> list() throws Exception {
		return mapper.selectByExample(null);
	}
	@Transactional(readOnly=true)
	public Category findById(Integer id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public List<Category> selectByExample(CategoryExample example) {
		return mapper.selectByExample(example);
	}
	@Transactional
	public void deleteByExample(CategoryExample example) {
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
	public Integer insert(Category e) {
		 return mapper.insert(e);
	}
	@Transactional
	public Integer insertSelective(Category record) {
		return mapper.insert(record);
	}
	@Transactional(readOnly=true)
	public Category selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	@Transactional(readOnly=true)
	public Long selectCountByExample(CategoryExample example) {
		  List<Category> selectByExample = mapper.selectByExample(example);
		  if(selectByExample==null){
			  return 0L;
		  }
		  return (long)selectByExample.size();
	}


	@Transactional(readOnly=true)
	public Category selectOneByExample(CategoryExample example) {
		   List<Category> list = mapper.selectByExample(example);
		   if(list!=null && list.size()>0){
			   return list.get(0);
		   }
		  return null;
	}
	@Transactional
	public void updateByExample(Category t, CategoryExample example) {
		mapper.updateByExample(t, example)	;
	}
	@Transactional
	public void updateByExampleSelective(Category t, CategoryExample example) {
		mapper.updateByExampleSelective(t, example)	;
	}
	@Transactional
	public int updateByPrimaryKey(Category record) {
		return mapper.updateByPrimaryKey(record);
	}
	@Transactional
	public int updateByPrimaryKeySelective(Category record) {
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
	@Override
	public void deleteBatch(Integer[] ids) {
		if(ids != null && ids.length > 0){
			for(Integer id :ids){
				mapper.deleteByPrimaryKey(id);
			}
		}
	}
	@Override
	public List<Category> selectByExampleWithBLOBs(CategoryExample example){

		return mapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int insertWithMore(List<Category> list) {
		return mapper.insertWithMore(list);
	}
}
