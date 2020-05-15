package com.toec.market.repair.service.impl;

import com.toec.market.repair.pojo.User;
import com.toec.market.repair.Example.UserExample;
import com.toec.market.repair.mapper.UserMapper;
import com.toec.market.repair.service.UserService;
import com.toec.market.repair.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Transactional(readOnly=true)
    public List<User> list() throws Exception {
        return mapper.selectByExample(null);
    }
    @Transactional(readOnly=true)
    public User findById(String id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly=true)
    public List<User> selectByExample(UserExample example) {
        return mapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public UserVo selectByExampleOther(UserExample example, Integer limit, Integer size) {
        //得到全部的数据
        UserVo userVo = new UserVo();
        List<User> lists = mapper.selectByExample(example);
        User[] users = lists.toArray(new User[]{});
        List<User> Mylist = new ArrayList<>();
        Integer mySize = 0;
        if(limit == null){
            limit = 0;
            //稍后会取出来,点击上一页或下一页进行使用
            userVo.setLimit(0);
        }else{
            userVo.setLimit(limit);
        }
        if(size == null){size = 10;}
        if(limit+size > lists.size()){
            mySize = lists.size();
        }else{
            mySize = limit+size;
        }
        //对数据进行分页查询
        for(int i = limit; i < mySize ;i++){
            Mylist.add(users[i]);
        }
        userVo.setUser(Mylist);
        userVo.setCount(lists.size());
        return userVo;
    }
    @Transactional
    public void deleteByExample(UserExample example) {
        mapper.deleteByExample(example);
    }
    @Transactional
    public void deleteByPrimaryKey(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly=true)
    public boolean existsWithPrimaryKey(String id) {
        return this.selectByPrimaryKey(id)!=null ? true:false;
    }
    @Transactional
    public Integer insert(User e) {
        return mapper.insert(e);
    }
    @Transactional
    public Integer insertSelective(User record) {
        return mapper.insert(record);
    }
    @Transactional(readOnly=true)
    public User selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }
    @Transactional(readOnly=true)
    public Long selectCountByExample(UserExample example) {
        List<User> selectByExample = mapper.selectByExample(example);
        if(selectByExample==null){
            return 0L;
        }
        return (long)selectByExample.size();
    }


    @Transactional(readOnly=true)
    public User selectOneByExample(UserExample example) {
        List<User> list = mapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    @Transactional
    public void updateByExample(User t, UserExample example) {
        mapper.updateByExample(t, example)	;
    }
    @Transactional
    public void updateByExampleSelective(User t, UserExample example) {
        mapper.updateByExampleSelective(t, example)	;
    }
    @Transactional
    public int updateByPrimaryKey(User record) {
        return mapper.updateByPrimaryKey(record);
    }
    @Transactional
    public int updateByPrimaryKeySelective(User record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
    @Transactional
    @Override
    public void deleteBatch(List<String> ids) {
        if(ids!=null && ids.size()>0){
            for(String id :ids){
                mapper.deleteByPrimaryKey(id);
            }
        }
    }
    @Override
    public void deleteBatch(String[] ids) {
        if(ids != null && ids.length > 0){
            for(String id :ids){
                mapper.deleteByPrimaryKey(id);
            }
        }
    }
}
