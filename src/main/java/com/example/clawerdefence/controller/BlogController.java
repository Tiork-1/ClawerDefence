package com.example.clawerdefence.controller;

import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.service.PassageServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    PassageServece passageServece;

    @GetMapping("/getbloglist")
    @ResponseBody
    public String getBlogList(){
        System.out.println("1111111111111111");
        List<Passage> passageList = passageServece.getAllPassages();
        return JSON.toJSONString(passageList);
    }
}
