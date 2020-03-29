package com.toec.market.repair.service;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T,PK extends Serializable,E> {
	
	
	public T selectByPrimaryKey(PK id);
	
	public boolean existsWithPrimaryKey(PK id);
	
	public Integer insert(T e);
	
	public Integer insertSelective(T record);
	
	public void deleteByPrimaryKey(PK id);
	
	public List<T> selectByExample(E example);
	
	public T selectOneByExample (E example);
	
	public Long selectCountByExample(E example);
	
	public void deleteByExample(E example);
	
	public void updateByExample(T t,E example);
	
	public void updateByExampleSelective(T t,E example);
	
    public int updateByPrimaryKeySelective(T record);

    public int updateByPrimaryKey(T record);
    
    public void deleteBatch(List<PK> ids);
    
    public void deleteBatch(PK[] ids);
    
}
