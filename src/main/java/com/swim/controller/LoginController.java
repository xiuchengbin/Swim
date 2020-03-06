package com.swim.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.swim.entity.User;
import com.swim.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	private static Log log= LogFactory.getLog(LoginController.class);

	@PostMapping("login/codeIncorrect")
	@ResponseBody
	public Object codeIncorrect(HttpSession session,String verifyCode){
		if(session==null){
			return -1;
		}
		if(session.getAttribute("verCode").equals(verifyCode.toLowerCase())){//验证码正确
			return 1;
		}else {
			return 0;
		}
	}

	@PostMapping("login/user")
	public String login(User user,String verifyCode, HttpSession session, Model model) {

		Subject subject= SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			subject.login(token);//登录(认证)
			User sessionUser=loginService.updateloginSession(loginService.getIdByUsername(user.getUsername()));//根据用户id查询到用户信息，并将存入session
			session.setAttribute("user",sessionUser);//用户信息存入session
			User tempUser=(User)SecurityUtils.getSubject().getPrincipal();
			if(tempUser.getUserInfo().getAuthority()==2){//管理员
				return "redirect:/index/index";//登陆成功
			}else{//用户
				return "redirect:/index/user";
			}
		}catch (UnknownAccountException e){//有户名不存在
			log.warn("用户名不存在");
			model.addAttribute("msg",-1);
			return "redirect:/login";
		}catch (IncorrectCredentialsException e){//密码错误
			log.warn("密码错误");
			model.addAttribute("msg",-1);
			return "redirect:/login";
		}catch (LockedAccountException e){//账户被冻结
			log.warn("账户被冻结");
			model.addAttribute("msg",-2);
			return "redirect:/login";
		}
//		User tempUser=loginService.login(user);//主要作用是或得到id，也是判断用户名和密码是否可以登录的依据
//		if (tempUser != null) {//用户名和密码正确
//			User sessionUser=loginService.updateloginSession(tempUser);//查询到用户信息，并将存入session
//			log.info(sessionUser);
//			if(sessionUser==null){//账号被冻结
//				model.addAttribute("msg",-2);
//				return "redirect:/login";
//			}
//			session.setAttribute("user",sessionUser);//用户信息存入session
//			return "redirect:/index/index";//登陆成功
//		} else {//用户名或密码错误
//			model.addAttribute("msg",-1);
//			return "redirect:/login";
//		}
	}

	@RequestMapping("login/exit")
	public String exitLogin(HttpSession session) {
		Subject subject= SecurityUtils.getSubject();
		subject.logout();
//		session.invalidate();//销毁session
		return "redirect:/login";
	}






	
}
