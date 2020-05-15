package com.toec.market.repair.controller.inner;

import com.toec.market.repair.controller.BaseController;
import com.toec.market.repair.vo.MessageResultVo;
import com.toec.market.repair.beans.PasswardInfoBean;
import com.toec.market.repair.Example.PasswardExample;
import com.toec.market.repair.service.PasswardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.toec.market.repair.pojo.Passward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/passward")
public class PasswardController extends BaseController {

    @Autowired
    private PasswardService passwardService;

    @RequestMapping(value = "updatePasswardById",method = RequestMethod.POST)
    @CrossOrigin
    public String updatePasswardById(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody PasswardInfoBean passward) throws Exception {
        MessageResultVo result = new MessageResultVo();
        PasswardExample example = new PasswardExample();
        Passward truthPassward = passwardService.selectByPrimaryKey(passward.getPassward().getId());
        if(truthPassward != null
                && truthPassward.getPassward() != null && truthPassward.getPassward() != ""
                && passward.getOldPassward() != null && passward.getOldPassward() != ""){ 
            //此时输入旧密码不等于原先密码，则将return
            if(!truthPassward.getPassward().equals(passward.getOldPassward())){
                result.setStatus(0);
            }else{
                //此时密码老密码输入正确，将开始修改密码 update
                PasswardExample.Criteria criteria = example.createCriteria();
                criteria.andIdEqualTo(passward.getPassward().getId());
                passwardService.updateByExample(passward.getPassward(),example);
                result.setStatus(1);
            }
        }
            return super.ajax(result,response);
    }
}