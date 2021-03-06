package com.example.clawerdefence.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.example.clawerdefence.config.LoginHandlerInterceptor;
import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.pojo.Result;
import com.example.clawerdefence.service.PassageServece;
import com.example.clawerdefence.utils.IVerifyCodeGen;
import com.example.clawerdefence.utils.SimpleCharVerifyCodeGenImpl;
import com.example.clawerdefence.utils.VerifyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.clawerdefence.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class IndexController {
    private static Map<String,String> codeMap = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCharVerifyCodeGenImpl.class);

    @Autowired
    PassageServece passageServece;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Map<String,Object> args,HttpServletRequest request,HttpSession session){
        String username = (String) args.get("username");
        String password = (String) args.get("password");
        String verifycode = (String) args.get("verifycode");
        String selfCode = (String) args.get("selfCode");
        String code = codeMap.get(selfCode);
//        用户输入的验证码和实际验证码转化一下
        verifycode = verifycode.toUpperCase();
        code = code.toUpperCase();
        System.out.println(username+" "+password+" "+verifycode+" "+code+" "+selfCode);
        Result result = new Result(true,"success");
        if (!verifycode.equals(code)){
            result.setDescribe("验证码错误");
            result.setOk(false);
        }else if(!username.equals("qiuxinhan")){
            result.setDescribe("用户不存在");
            result.setOk(false);
        }else if(!password.equals("123456")){
            result.setDescribe("密码错误");
            result.setOk(false);
        }else {
            StpUtil.login(username);
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("/blogs")
    public String toblogs(){
        return "blogs";
    }


    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response,HttpSession session,String selfCode) {
        System.out.println("get code");
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            LOGGER.info(code);
            codeMap.put(selfCode,code);

            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            LOGGER.info("", e);
        }
    }

    @GetMapping("/testcode")
    public String testCode(){
        return "testcode";
    }

    @GetMapping("/closeVerify")
    @ResponseBody
    public String closeVerify(){

        if (LoginHandlerInterceptor.needLogin == true){
            LoginHandlerInterceptor.needLogin = false;
            return "开启登录认证";
        }else {
            LoginHandlerInterceptor.needLogin = true;
            return "关闭登录认证";
        }
    }
}
