//package com.swim.service;
//
//import com.swim.dao.ILoginMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SecurityServiceImp implements UserDetailsService {
//    @Autowired
//    private ILoginMapper loginMapper;
//
//    private String username;
//    private String password;
//
//    public SecurityServiceImp(){
//
//    }
//
//    public SecurityServiceImp(String username,String password){
//        this.username=username;
//        this.password=password;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user=loginMapper.login()
//        return null;
//    }
//
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//}
