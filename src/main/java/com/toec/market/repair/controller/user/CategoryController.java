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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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
    @RequestMapping("/insert")
    public Object insert(HttpServletRequest request, HttpServletResponse response)throws Exception{
        MessageResult messageResult = new MessageResult();
        Category category1 = new Category();
        category1.setCreateDate(new Date());
        category1.setId(6);
        category1.setName("张强");
        category1.setTitle("第一个数据");
        category1.setIconPath("111");
        Category category2 = new Category();
        category2.setCreateDate(new Date());
        category2.setName("张强");
        category2.setTitle("第一个数据");
        category2.setIconPath("222");
        category2.setId(7);
        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(category1);
        categoriesList.add(category2);
       try {
           int insert = categoryService.insertWithMore(categoriesList);
           if(insert != 0){
               messageResult.setStatus(1);
               messageResult.setMessage("多行插入成功！");
               return ajax(messageResult,response);
           }else{
               messageResult.setStatus(0);
               messageResult.setMessage("多行插入失败！");
               return ajax(messageResult,response);
           }
       }catch (Exception e){
            response.sendError(500);
            return null;
       }
    }
}