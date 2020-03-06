package com.swim.interceptor;

import com.swim.entity.User;
import com.swim.util.ContextUtil;
import com.swim.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Scope("prototype")
public class IsLoginInterceptor implements HandlerInterceptor {

    public  synchronized void setClickData(RedisTemplate redisTemplate){//记录数据方法，线程安全
        Map<String,Object> data=(Map) redisTemplate.opsForHash().entries("Mydata");
        Integer day_click=Integer.parseInt(data.get("day_click").toString())+1;
        Integer sum_click=Integer.parseInt(data.get("sum_click").toString())+1;
        data.put("day_click",day_click);
        data.put("sum_click",sum_click);
        redisTemplate.opsForHash().putAll("Mydata",data);
    }


    public void addClickData(){//每次请求都会启动一个线程来记录数据
        RedisTemplate redisTemplate= (RedisTemplate)ContextUtil.getBean("redisTemplate");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    setClickData(redisTemplate);
                }
            }).start();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();
        System.out.println("请求uri: "+uri);
        if(uri.startsWith("/login")){
            String token=MyUtil.getToken();
            request.getSession().setAttribute("token",token);
            Cookie cookie=new Cookie("token", token);
            cookie.setPath("/");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
            return true;
        }
        User user= (User) request.getSession().getAttribute("user");
        if(user!=null){//判断session中是否有登录状态
            if(request.getMethod().equals("POST")){//只有POST请求进行token验证
                Cookie[] cookies=request.getCookies();//获取cookie
                String cookieToken=null;
                for(Cookie theCookie:cookies){
                    if(theCookie.getName().equals("token")){
                        cookieToken=theCookie.getValue();
                    }
                }
                System.out.println(String.valueOf(request.getSession().getAttribute("token")));
                System.out.println(cookieToken);
                if(String.valueOf(request.getSession().getAttribute("token")).equals(cookieToken)||cookieToken==null){//验证成功
                    String token=MyUtil.getToken();
                    request.getSession().setAttribute("token",token);
                    Cookie cookie=new Cookie("token", token);
                    cookie.setPath("/");
                    cookie.setMaxAge(60*60);
                    response.addCookie(cookie);
                    return true;
                }else {
                    if(response.getStatus()!=200) return true;
                    return false;
                }
            }
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(response.getStatus()==200){
            addClickData();
        }
    }
}
