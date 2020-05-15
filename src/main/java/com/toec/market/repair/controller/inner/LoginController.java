package com.toec.market.repair.controller.inner;


import com.toec.market.repair.enums.Status;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.subject.Subject;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/toLogin",method = RequestMethod.POST)
    @CrossOrigin
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("rememberMe") Boolean rememberMe) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,password
        );
        usernamePasswordToken.setRememberMe(rememberMe);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
//            return Status.usernameOrpasswardError.getName(); //401
            return "/login";
        } catch (AuthorizationException e) {
            e.printStackTrace();
//            return Status.noPermission.getName(); //402
            return "/login"; //402
        }
        return Status.success.getName();  //index
    }
}
