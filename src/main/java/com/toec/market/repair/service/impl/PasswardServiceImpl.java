package com.toec.market.repair.service.impl;

import com.toec.market.repair.entity.Passward;
import com.toec.market.repair.entity.PasswardExample;
import com.toec.market.repair.mapper.PasswardMapper;
import com.toec.market.repair.service.PasswardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswardServiceImpl implements PasswardService {

    @Autowired
    private PasswardMapper mapper;


    @Override
    public Passward selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean existsWithPrimaryKey(String id) {
        return false;
    }

    @Override
    public Integer insert(Passward e) {
        return mapper.insert(e);
    }

    @Override
    public Integer insertSelective(Passward record) {
        return null;
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Passward> selectByExample(PasswardExample example) {
        return null;
    }

    @Override
    public Passward selectOneByExample(PasswardExample example) {
        return null;
    }

    @Override
    public Long selectCountByExample(PasswardExample example) {
        return null;
    }

    @Override
    public void deleteByExample(PasswardExample example) {

    }

    @Override
    public void updateByExample(Passward passward, PasswardExample example) {
        mapper.updateByExample(passward,example);
    }

    @Override
    public void updateByExampleSelective(Passward passward, PasswardExample example) {

    }

    @Override
    public int updateByPrimaryKeySelective(Passward record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Passward record) {
        return 0;
    }

    @Override
    public void deleteBatch(List<String> ids) {

    }

    @Override
    public void deleteBatch(String[] ids) {

    }
}
