package com.toec.market.repair.service;

import java.util.List;

import com.toec.market.repair.entity.Category;
import com.toec.market.repair.entity.CategoryExample;

public interface CategoryService extends BaseService<Category,Integer,CategoryExample> {

	public List<Category> selectByExampleWithBLOBs(CategoryExample example);

	public int insertWithMore(List<Category> list);
}
