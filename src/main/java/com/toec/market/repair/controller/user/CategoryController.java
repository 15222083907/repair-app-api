package com.toec.market.repair.controller.user;

import com.toec.market.repair.beans.MessageResult;
import com.toec.market.repair.controller.BaseController;
import com.toec.market.repair.entity.AdminExample;
import com.toec.market.repair.entity.Category;
import com.toec.market.repair.entity.CategoryExample;
import com.toec.market.repair.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/api")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/get")
    @CrossOrigin
    public Object get(HttpServletRequest request, HttpServletResponse response)throws Exception{
        MessageResult result = new MessageResult();
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria createCriteria = example.createCriteria();
        createCriteria.andIdEqualTo(2);
        List<Category> categories = categoryService.selectByExample(example);
        Category category = categories.get(0);
        result.setMessage(category);
        result.setStatus(1);
        return super.ajax(result,response);
    }
}
