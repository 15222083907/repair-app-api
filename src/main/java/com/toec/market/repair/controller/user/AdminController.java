package com.toec.market.repair.controller.user;

import com.toec.market.repair.beans.MessageResult;
import com.toec.market.repair.controller.BaseController;
import com.toec.market.repair.entity.Admin;
import com.toec.market.repair.entity.AdminExample;
import com.toec.market.repair.entity.AdminExample.Criteria;
import com.toec.market.repair.service.AdminService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/api")
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminService;
	
	@CrossOrigin
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public Object login(String username, String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			MessageResult result = new MessageResult();
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				result.setStatus(0);// 失败
				result.setMessage("用户名或密码错误");
				return super.ajax(result, response);
			}
			AdminExample example = new AdminExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andUsernameEqualTo(username);  //条件
			List<Admin> adminList = adminService.selectByExample(example);
			if (CollectionUtils.isEmpty(adminList)) {
				result.setStatus(0);// 失败
				result.setMessage("用户名或密码错误");
				return super.ajax(result, response);
			}
			Admin admin = adminList.get(0);
			if (!StringUtils.equals(admin.getPassword(), password)) {
				result.setStatus(0);// 失败
				result.setMessage("用户名或密码错误");
				return super.ajax(result, response);
			}
			result.setStatus(1);// 成功
			result.setMessage("登录成功");
			return super.ajax(result, response);
		} catch (Exception e) {
			response.sendError(500, "ServerError");
			return null;
		}

	}
}