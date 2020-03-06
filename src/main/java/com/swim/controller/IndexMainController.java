package com.swim.controller;

import com.swim.dao.IDataMapper;
import com.swim.entity.User;
import com.swim.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexMainController {
    @Autowired(required = false)
    private IDataMapper IDataMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("index-main.html")
    public String toIndexMain(Model model, HttpServletRequest request, HttpSession session){
        model.addAttribute("os_name",System.getProperty("os.name"));
        model.addAttribute("java_runtime_version",System.getProperty("java.runtime.version"));
        model.addAttribute("mysql_version", IDataMapper.getMySQL_version());
        model.addAttribute("ip", MyUtil.getClientIpAddress(request));
        Map<String,Object> data=(Map) redisTemplate.opsForHash().entries("Mydata");
        model.addAttribute("data",data);
        User user= (User) session.getAttribute("user");
        model.addAttribute("name",user.getUsername());
        return "index/index-main";
    }

}
