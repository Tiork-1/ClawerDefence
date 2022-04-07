package com.example.clawerdefence.controller;

import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.service.PassageServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    @Autowired
    PassageServece passageServece;

    @GetMapping("/getbloglist")
    @ResponseBody
    public String getBlogList(){
        List<Passage> passageList = passageServece.getAllPassages();
        return JSON.toJSONString(passageList);
    }

    @GetMapping("/blogEditor")
    public String blogEditor(){
        return "/html/addblog.html";
    }

    @GetMapping("/addblog")
    public String addBlog(@RequestBody Map<String,Object> args){
        String title = (String) args.get("title");
        String context = (String) args.get("context");
        passageServece.addPassage(new Passage(null,title,context));
        return "forward:/blogs";
    }
}
