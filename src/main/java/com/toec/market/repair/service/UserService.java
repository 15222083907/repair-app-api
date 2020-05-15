package com.toec.market.repair.service;

import com.toec.market.repair.pojo.User;
import com.toec.market.repair.Example.UserExample;
import com.toec.market.repair.vo.UserVo;

public interface UserService extends BaseService<User,String, UserExample> {

    UserVo selectByExampleOther(UserExample userExample, Integer limit, Integer size);
}
