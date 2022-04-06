package com.example.clawerdefence.service;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs")
public class ShowBlogController {

    @RequestMapping("/{id}")
    public String getSpecificPassage(Model model,String id){
        model.addAttribute("id",id);
        return "/html/showblog.html";
    }
}
