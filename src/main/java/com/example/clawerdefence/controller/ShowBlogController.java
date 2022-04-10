package com.example.clawerdefence.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.pojo.Pair;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.pojo.Result;
import com.example.clawerdefence.pojo.VisitData;
import com.example.clawerdefence.service.PassageServece;
import com.example.clawerdefence.utils.IpUtil;
import com.example.clawerdefence.utils.StatisticUtil;
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

    @RequestMapping("/{id}")
    public String getSpecificPassage(HttpSession session, @PathVariable("id") String id, Model model, HttpServletRequest request){
        //获取IP地址
        String ipAddress = IpUtil.getIpAddr(request);
//        是爬虫，拦截
        if(StatisticUtil.isBlack(ipAddress)){
            StpUtil.logout();
            return "index";
        }
//        不是爬虫，将这个访问数据统计起来
        StatisticUtil.addVisit(ipAddress,0L);

        session.setAttribute("blogId",id);
        Passage passage = passageServece.getPassageById(id);
        model.addAttribute("passage",passage);
        return "showblog";
    }

    @GetMapping("/showData")
    public String showData(Model model){
//        获取统计数据
        List<VisitData> arrayList = StatisticUtil.getVisitData();
        model.addAttribute("datas",arrayList);
        return "showdata";
    }

    @GetMapping("/getHourVisit")
    @ResponseBody
    public String getHoursVisit(){
        return JSON.toJSONString(StatisticUtil.getHoursVisit());
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
