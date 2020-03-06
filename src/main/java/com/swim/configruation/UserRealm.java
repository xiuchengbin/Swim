package com.swim.configruation;

import com.swim.dao.ILoginMapper;
import com.swim.entity.User;
import com.swim.entity.UserInfo;
import com.swim.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ILoginMapper iLoginMapper;
    @Autowired
    private LoginService loginService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        if(user.getUserInfo().getAuthority()==2){//管理员
            info.addStringPermission("index:*");
            info.addStringPermission("goods-management:*");
            info.addStringPermission("points-management:*");
            info.addStringPermission("swim-management:*");
            info.addStringPermission("user-management:*");
            info.addStringPermission("history-search:*");
        }else {

        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user=loginService.getShiroUser(loginService.getIdByUsername(token.getUsername()));
        if(user==null){//用户不存在
            throw new UnknownAccountException();
        }else if(!user.getUserInfo().getStatus()){
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
