package com.example.clawerdefence.controller;


import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.pojo.Result;
import com.example.clawerdefence.service.PassageServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/blogs")
public class ShowBlogController {

    @Autowired
    PassageServece passageServece;

    @RequestMapping("/{id}")
    public String getSpecificPassage(HttpSession session, @PathVariable("id") String id, Model model){
        session.setAttribute("blogId",id);
        Passage passage = passageServece.getPassageById(id);
        model.addAttribute("passage",passage);
        return "showblog";
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
        return JSON.toJSONString(passage);
    }
}
