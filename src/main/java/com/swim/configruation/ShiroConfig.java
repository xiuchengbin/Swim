package com.swim.configruation;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /*
        anon :无需认证
        authc:必须认证
        user：必须拥有记住我功能
        perms：必须对某个资源的权限才能访问
        role：必须拥有某个角色权限
    */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);//设置安全管理器
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/*","authc");
        filterMap.put("/index/index","perms[index:*]");
        filterMap.put("/goods-management/*","perms[goods-management:*]");
        filterMap.put("/points-management/*","perms[points-management:*]");
        filterMap.put("/swim-management/*","perms[swim-management:*]");
        filterMap.put("/user-management/*","perms[user-management:*]");
        filterMap.put("/history-search/*","perms[history-search:*]");
        bean.setLoginUrl("/login");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setUnauthorizedUrl("/404");
        return bean;
    }


    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);//关联userRealm
        return securityManager;
    }


    @Bean("userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
