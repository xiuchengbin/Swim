//package com.swim.configruation;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法权限控制
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()//开启登录配置
//                // 所有用户均可访问的资源
//                .antMatchers("/favicon.ico","/webjars/**","/css/**","/fonts/**","/framework/**","/images/**","/js/**","/layui/**","/login/**").permitAll()
//                // 任何尚未匹配的URL只需要验证用户即可访问
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")//表单登陆，登录页
//                .successForwardUrl("/login/user")//登陆url
//                .failureForwardUrl("/login?error=1")//权限拒绝的页面
//                .and()
//                .exceptionHandling().accessDeniedPage("/403");
//
//        http.logout().logoutSuccessUrl("/loginout");
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){//密码加密
//        return new BCryptPasswordEncoder();
//    }
//
//}
