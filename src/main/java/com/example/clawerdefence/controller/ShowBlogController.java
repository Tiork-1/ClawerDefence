package com.example.clawerdefence.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Pair;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.pojo.Result;
import com.example.clawerdefence.service.PassageServece;
import com.example.clawerdefence.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/blogs")
public class ShowBlogController {

    @Autowired
    PassageServece passageServece;

    static Map<String,Integer> UserMap = new HashMap<>();
    static Map<String,Integer> UserDayMap = new HashMap<>();
    static Set<String> blackSet = new HashSet<>();
    static Queue<Pair> assessQueue = new LinkedList<>();
    public void processVisitor(String ip){
        Long nowTime = System.currentTimeMillis();
        Long secondTime = nowTime/1000;
        System.out.println(ip+" "+secondTime);
        Pair pair = new Pair(ip,secondTime);
        assessQueue.offer(pair);
//        修改访问的值
        if(UserMap.containsKey(ip)){
            UserMap.put(ip,UserMap.get(ip)+1);
        }else {
            UserMap.put(ip,1);
        }
//        更新map里面的用户信息
        while (!assessQueue.isEmpty()){
            Pair pair1 = assessQueue.peek();
            if(secondTime-pair1.getTimestamp()>=60) {
                Integer x = UserMap.get(pair1.getUserIp());
                if(x==1){
                    UserMap.remove(pair1.getUserIp());
                }else {
                    UserMap.put(pair1.getUserIp(),x-1);
                }
                assessQueue.poll();
            }else {
                break;
            }
        }
//        判断是否爬虫
        if (UserMap.get(ip)>20){
            blackSet.add(ip);
        }
    }

    @RequestMapping("/{id}")
    public String getSpecificPassage(HttpSession session, @PathVariable("id") String id, Model model, HttpServletRequest request){
        //获取IP地址
        String ipAddress = IpUtil.getIpAddr(request);
//        是爬虫，拦截
        if(blackSet.contains(ipAddress)){
            StpUtil.logout();
            return "index";
        }
        processVisitor(ipAddress);

        session.setAttribute("blogId",id);
        Passage passage = passageServece.getPassageById(id);
        model.addAttribute("passage",passage);
        return "showblog";
    }

//    @RequestMapping("/getReadingBlog")
//    @ResponseBody
//    public String getReadingBlog(HttpSession session) {
//        String id = (String) session.getAttribute("blogId");
//        Result result = new Result(true,""+id);
//        return JSON.toJSONString(result);
//    }

//    @RequestMapping("/getBlogById")
//    @ResponseBody
//    public String getBlogById(@RequestBody Map<String,Object> args){
//        String id = (String) args.get("id");
//        System.out.println(id);
//        Passage passage = passageServece.getPassageById(id);
//        return JSON.toJSONString(passage);
//    }
}
