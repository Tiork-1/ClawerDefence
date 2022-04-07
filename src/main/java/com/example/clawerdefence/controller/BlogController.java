package com.example.clawerdefence.controller;

import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.service.PassageServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

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
        return "addblog";
    }


    @RequestMapping("/publish")
//    @ResponseBody
    public String publishArticle(Passage passage) {
        Random random = new Random();
        Integer x = random.nextInt(100000000);
        passage.setId(x.toString());
        boolean res = passageServece.addPassage(passage);
        return "success";
    }
}
