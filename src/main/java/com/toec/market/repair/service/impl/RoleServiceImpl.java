package com.toec.market.repair.service.impl;

import com.toec.market.repair.pojo.Role;
import com.toec.market.repair.Example.RoleExample;
import com.toec.market.repair.mapper.RoleMapper;
import com.toec.market.repair.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public boolean existsWithPrimaryKey(String id) {
        return false;
    }

    @Override
    public Integer insert(Role e) {
        return roleMapper.insert(e);
    }

    @Override
    public Integer insertSelective(Role record) {
        return null;
    }

    @Override
    public void deleteByPrimaryKey(String id) {

    }

    @Override
    public List<Role> selectByExample(RoleExample example) {
        return null;
    }

    @Override
    public Role selectOneByExample(RoleExample example) {
        return null;
    }

    @Override
    public Long selectCountByExample(RoleExample example) {
        return null;
    }

    @Override
    public void deleteByExample(RoleExample example) {

    }

    @Override
    public void updateByExample(Role role, RoleExample example) {

    }

    @Override
    public void updateByExampleSelective(Role role, RoleExample example) {

    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;
    }

    @Override
    public void deleteBatch(List<String> ids) {

    }

    @Override
    public void deleteBatch(String[] ids) {

    }
}
