package com.example.clawerdefence.service;


import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/blogs")
public class ShowBlogController {

    @Autowired
    PassageServece passageServece;

    @RequestMapping("/{id}")
    public String getSpecificPassage(HttpSession session, @PathVariable("id") String id){
        session.setAttribute("blogId",id);
        return "/html/showblog.html";
    }

    @RequestMapping("/getReadingBlog")
    @ResponseBody
    public String getReadingBlog(HttpSession session) {
        String id = (String) session.getAttribute("blogId");
        Result result = new Result(true,""+id);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/getBlogById")
    @ResponseBody
    public String getBlogById(@RequestBody Map<String,Object> args){
        String id = (String) args.get("id");
        System.out.println(id);
        Passage passage = passageServece.getPassageById(id);
        System.out.println(passage);
        return JSON.toJSONString(passage);
    }
}
