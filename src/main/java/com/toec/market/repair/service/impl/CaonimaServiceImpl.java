package com.toec.market.repair.service.impl;

import com.toec.market.repair.entity.Caonima;
import com.toec.market.repair.entity.CaonimaExample;
import com.toec.market.repair.mapper.CaonimaMapper;
import com.toec.market.repair.service.CaonimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CaonimaServiceImpl implements CaonimaService {

    @Autowired
    private CaonimaMapper mapper;

    @Transactional(readOnly=true)
    public List<Caonima> list() throws Exception {
        return mapper.selectByExample(null);
    }
    @Transactional(readOnly=true)
    public Caonima findById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly=true)
    public List<Caonima> selectByExample(CaonimaExample example) {
        return mapper.selectByExample(example);
    }
    @Transactional
    public void deleteByExample(CaonimaExample example) {
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
    public Integer insert(Caonima e) {
        return mapper.insert(e);
    }
    @Transactional
    public Integer insertSelective(Caonima record) {
        return mapper.insert(record);
    }
    @Transactional(readOnly=true)
    public Caonima selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
    @Transactional(readOnly=true)
    public Long selectCountByExample(CaonimaExample example) {
        List<Caonima> selectByExample = mapper.selectByExample(example);
        if(selectByExample==null){
            return 0L;
        }
        return (long)selectByExample.size();
    }


    @Transactional(readOnly=true)
    public Caonima selectOneByExample(CaonimaExample example) {
        List<Caonima> list = mapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    @Transactional
    public void updateByExample(Caonima t, CaonimaExample example) {
        mapper.updateByExample(t, example)	;
    }
    @Transactional
    public void updateByExampleSelective(Caonima t, CaonimaExample example) {
        mapper.updateByExampleSelective(t, example)	;
    }
    @Transactional
    public int updateByPrimaryKey(Caonima record) {
        return mapper.updateByPrimaryKey(record);
    }
    @Transactional
    public int updateByPrimaryKeySelective(Caonima record) {
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