package com.toec.market.repair.service;

import com.toec.market.repair.entity.User;
import com.toec.market.repair.entity.UserExample;
import com.toec.market.repair.vo.UserVo;

import java.util.List;

public interface UserService extends BaseService<User,String, UserExample> {

    UserVo selectByExampleOther(UserExample userExample, Integer limit, Integer size);
}
