package com.example.clawerdefence.controller;

import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.clawerdefence.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Map<String,Object> args){
        String username = (String) args.get("username");
        String password = (String) args.get("password");
        System.out.println(username+" "+password);
        String ans;
        if(username.equals("qiuxinhan") && password.equals("123456")){
            ans = JSON.toJSONString(new Result(true,"ok"));
        }else {
            ans = JSON.toJSONString(new Result(false, "no"));
        }
        return ans;
    }

    @GetMapping("/blogs")
    public String toblogs(){
        return "blogs";
    }
}
