package com.toec.market.repair.service;

import com.toec.market.repair.vo.UserAuthenticationVo;

public interface LoginService {
    UserAuthenticationVo getUserByName(String userName);
}
