package com.toec.market.repair.controller.user;

import com.toec.market.repair.beans.MessageResult;
import com.toec.market.repair.beans.UserInfoBean;
import com.toec.market.repair.controller.BaseController;
import com.toec.market.repair.entity.Passward;
import com.toec.market.repair.entity.User;
import com.toec.market.repair.entity.UserExample;
import com.toec.market.repair.mapper.PasswardMapper;
import com.toec.market.repair.service.UserService;
import com.toec.market.repair.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private PasswardMapper passwardMapper;
    /*
    对用户的数量进行统计
     */
    @RequestMapping(value = "/getUserByExample",method = RequestMethod.POST)
    @CrossOrigin
    public String getAllUserCount(String time1,String time2,User user,HttpServletRequest request, HttpServletResponse response,Integer limit,Integer size) throws Exception {
        MessageResult result = new MessageResult();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(time1 != null && time1 != ""){
            Date parse1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1 + " 00:00:00");
            criteria.andDateinGreaterThanOrEqualTo(parse1);
        }
        if(time2 != null && time2 != ""){
            Date parse2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time2 + " 23:59:59");
            criteria.andDateinLessThanOrEqualTo(parse2);
        }
        if(user.getId() != null && user.getId() != ""){
            criteria.andIdEqualTo(user.getId());
        }
        if(user.getUsername() != null && user.getUsername() != ""){
            criteria.andUsernameLike("%"+user.getUsername()+"%");
        }
        if(user.getGender() != null && user.getGender() != ""){
            criteria.andGenderEqualTo(user.getGender());
        }
        if(user.getPhone() != null && user.getPhone() != ""){
            criteria.andPhoneEqualTo(user.getPhone());
        }
        if(user.getEmail() != null && user.getEmail() != ""){
            criteria.andEmailLike("%"+user.getEmail()+"%");
        }
        if(user.getAddress() != null && user.getAddress() != ""){
            criteria.andAddressLike("%"+user.getAddress()+"%");
        }
        if(user.getStatus() != null && user.getStatus() != ""){
            criteria.andStatusEqualTo(user.getStatus());
        }
       try {
           UserVo users = userService.selectByExampleOther(userExample,limit,size);
           result.setMessage(users);
           result.setStatus(1);
       }catch (Exception ex){
           System.out.println(ex.getMessage());
       }
        return super.ajax(result,response);
    }

    /*
   根据id单个删除用户
    */
    @RequestMapping(value = "/deleteAllUser",method = RequestMethod.POST)
    @CrossOrigin
    public String deleteAllUser(HttpServletRequest request, HttpServletResponse response,@RequestBody String[] ids) throws Exception{
        MessageResult result = new MessageResult();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(ids != null && ids.length != 0){
            userService.deleteBatch(ids);
            result.setMessage("删除成功");
        }
        result.setStatus(1);
        return super.ajax(result,response);
    }

    /*
    根据id批量删除用户
     */
    @RequestMapping(value = "/deleteOneUser",method = RequestMethod.POST)
    @CrossOrigin
    public String deleteOneUser(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("id") String id) throws Exception{
        MessageResult result = new MessageResult();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(id != null && id != ""){
            userService.deleteByPrimaryKey(id);
            result.setMessage("删除成功");
        }
        result.setStatus(1);
        return super.ajax(result,response);
    }

    /*
    根据插入框插入用户
     */
    @RequestMapping(value = "/addOneUser",method = RequestMethod.POST)
    @CrossOrigin
    public String addOneUser(HttpServletRequest request,HttpServletResponse response,
                             @RequestBody UserInfoBean userInfoBean)throws Exception{
        MessageResult result = new MessageResult();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        String id = UUID.randomUUID().toString().replace("-", "").substring(0,10);
        if(userInfoBean.getUser() != null){
            User user = new User();
            user.setId(id);
            user.setDatein(new Date());
            user.setStatus("已启用");
            user.setPasswardId(id);
            user.setPhone(userInfoBean.getUser().getPhone());
            user.setGender(userInfoBean.getUser().getGender());
            user.setUsername(userInfoBean.getUser().getUsername());
            user.setEmail(userInfoBean.getUser().getEmail());
            user.setAddress(userInfoBean.getUser().getAddress());
            Integer insert = userService.insert(user);
            if(insert != null && insert > 0){
                result.setMessage("添加用户成功");
                result.setStatus(1);
            }
        }
        if(userInfoBean.getPassward() != null){
            Passward passward = new Passward();
            passward.setId(id);
            passward.setPassward(userInfoBean.getPassward().getPassward());
            Integer insert = passwardMapper.insert(passward);
            if(insert != null && insert > 0){
                result.setMessage("添加密码成功");
                result.setStatus(1);
            }
        }
        return super.ajax(result,response);
    }

    /*
    更改用户的状态
     */
    @RequestMapping("/stopOrStartUser")
    @CrossOrigin
    public String updateUserStatus(HttpServletRequest request,HttpServletResponse response,
                                   @RequestParam("idContent") String id,
                                    @RequestParam("status") String status) throws Exception {
        MessageResult result = new MessageResult();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(id);
        User user = new User();
        if(status.equals("禁用")){
            user.setStatus("已启用");
        }else{
            user.setStatus("禁用");
        }
        userService.updateByExampleSelective(user,userExample);
        result.setMessage("更新用户成功");
        result.setStatus(1);
        return super.ajax(result,response);
    }

    /**
     自动插入数据
     */
    @RequestMapping(value = "/insertUserByAuto",method =RequestMethod.POST)
    @CrossOrigin
    public String insertUserByAuto(HttpServletRequest request,HttpServletResponse response)throws Exception{
        MessageResult result = new MessageResult();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        User user = new User();
        for(int i = 0;i < 100000; i++){
            String id = UUID.randomUUID().toString().substring(0,10);
            user.setId(id);
            user.setStatus("已启用");
            user.setDatein(new Date());
            user.setAddress("虚拟地址");
            user.setEmail(UUID.randomUUID().toString().substring(0,6)+"@163.com");
            user.setUsername(UUID.randomUUID().toString().substring(0,4));
            user.setGender("男");
            user.setPhone("13111111111");
            user.setPasswardId(id);
            Integer insert = userService.insert(user);
            Passward passward = new Passward();
            passward.setPassward(UUID.randomUUID().toString().substring(0,10));
            passward.setId(id);
            Integer insert1 = passwardMapper.insert(passward);
            if(insert != null && insert >0 && insert1 != null && insert1 > 0){
                System.out.println("第" + i + "条插入成功!");
            }
        }
        result.setMessage("插入成功");
        result.setStatus(1);
        return super.ajax(result,response);
    }
}