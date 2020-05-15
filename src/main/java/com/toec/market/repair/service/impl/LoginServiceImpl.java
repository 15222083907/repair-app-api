package com.toec.market.repair.service.impl;

import com.toec.market.repair.pojo.Passward;
import com.toec.market.repair.pojo.Role;
import com.toec.market.repair.pojo.User;
import com.toec.market.repair.Example.UserExample;
import com.toec.market.repair.mapper.PasswardMapper;
import com.toec.market.repair.mapper.RoleMapper;
import com.toec.market.repair.mapper.UserMapper;
import com.toec.market.repair.service.LoginService;
import com.toec.market.repair.vo.UserAuthenticationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PasswardMapper passwardMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserAuthenticationVo getUserByName(String userName) {
        return getMapByName(userName);
    }

    private UserAuthenticationVo getMapByName(String userName) {
        UserAuthenticationVo userAuthenticationVo = new UserAuthenticationVo();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        //得到user
        List<User> users = userMapper.selectByExample(userExample);
        userAuthenticationVo.setUser(users.get(0));
        String id = users.get(0).getId();
        //得到passward
        Passward passward = passwardMapper.selectByPrimaryKey(id);
        userAuthenticationVo.setPassward(passward);
        //得到role
        Role role = roleMapper.selectByPrimaryKey(users.get(0).getRoleId());
        userAuthenticationVo.setRole(role);
        return userAuthenticationVo;
    }
}