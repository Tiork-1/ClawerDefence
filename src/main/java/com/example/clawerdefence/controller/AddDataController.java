package com.example.clawerdefence.controller;


import com.example.clawerdefence.utils.IpUtil;
import com.example.clawerdefence.utils.StatisticUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AddDataController {
    @GetMapping("/addVisit")
    public String addVisit(String h,String m, HttpServletRequest request){
        System.out.println(h+" "+m);
        Long hour = 0L;
        if(h!=null){
            hour = Long.valueOf(h);
        }
        Long minute = 0L;
        if (m!=null){
            minute = Long.valueOf(m);
        }
        Long time = hour*3600 + minute*60;

        //获取IP地址
        String ipAddress = IpUtil.getIpAddr(request);
        StatisticUtil.addVisit(ipAddress,time);
        return "增加访问记录成功";
    }

    @GetMapping("/cancelBlack")
    public String cancelVisit(HttpServletRequest request){
        //获取IP地址
        String ipAddress = IpUtil.getIpAddr(request);
        if(StatisticUtil.cancelBlack(ipAddress)){
            return "已将你从黑名单移出";
        }
        return "你不在黑名单中";
    }
}
