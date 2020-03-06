package com.swim.controller;

import com.swim.dao.ISwimMapper;
import com.swim.entity.User;
import com.swim.entity.Vistor;
import com.swim.service.SwimService;
import com.swim.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class PageController {
	@Autowired(required = false)
	private ISwimMapper iSwimMapper;

	@Autowired(required = false)
	private SwimService swimService;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		//生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		//存入会话session
		HttpSession session = request.getSession(true);
		//删除以前的
		session.removeAttribute("verCode");
		session.setAttribute("verCode", verifyCode.toLowerCase());
		//生成图片
		int w = 100, h = 30;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}

	@GetMapping({"/login/verifyCode"})//登录页
	public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		service(request,response);
		return null;
	}

	@GetMapping({"/login"})//登录页
	public String toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "login/login";
	}

	@GetMapping("index/index")//登陆成功后的首页(管理员)
	public String toIndex(){
		return "index/index";
	}

	@GetMapping("index/user")//登陆成功后的首页(用户)
	public String toIndexUser(){
		return "index/index-user";
	}

	@GetMapping({"user/modify_password","user/modify_password.html"})
	public String toModifyPassword(){
		return "user/modify-password";
	}

	@GetMapping({"user/modify_userInfo","user/modify_userInfo.html"})
	public String toModifyUserInfo(){
		return "user/modify-userInfo";
	}

	@GetMapping({"user/select_account","user/select_account.html"})
	public String toSelectAccount(){
		return "user/select-account";
	}

	@GetMapping({"user-management/user-add","user-management/user-add.html"})
	public String toAddUser(){
		return "user-management/user-add";
	}

//	@GetMapping({"user-management/user-list","user-management/user-list.html"})
//	public String toUserList1(){
//		return "user-list1";
//	}



	@GetMapping({"user-management/user-list","user-management/user-list.html"})
	public String toUserList(){
		return "user-management/user-list";
	}

	@GetMapping("user-management/user-recharge.html")
	public String toUserRecharge(){
		return "user-management/user-recharge";
	}

	@GetMapping({"user-management/alter-user.html"})
	public String toAlterUser(){
		return "user-management/alter-user";
	}


	@GetMapping({"swim-management/vistor.html"})
	public String toVistor(){
		return "swim-management/vistor";
	}

	@GetMapping({"swim-management/vistor-add.html"})
	public String toVistorAdd(){
		return "swim-management/vistor-add";
	}

	@GetMapping({"swim-management/vistor-end.html"})
	public String toVistorEnd(Integer vistorId, Model model){
		Vistor vistor=iSwimMapper.selectVistorById(vistorId);
		long swimTime=swimService.getSwimTimeByTimeStamp(vistor.getSwimStart());
		Float cost=swimService.getResultBySwimBySwimTimer(swimTime);
		model.addAttribute("vistor",vistor);//散客信息
		model.addAttribute("swimTime",swimService.getSwimTime(vistor.getSwimStart()));//游泳的时间
		model.addAttribute("swimEnd",
				new Timestamp(vistor.getSwimStart().getTime()+swimTime));
		float returnPay=vistor.getBalance()-cost;
		if(returnPay<0){
			returnPay=0f;
			model.addAttribute("cost",vistor.getBalance());
		}else {
			model.addAttribute("cost",cost);
		}
		model.addAttribute("returnMoney",returnPay);
		return "swim-management/vistor-end";
	}

	@GetMapping({"swim-management/swim-start.html"})
	public String toSwimStart(){
		return "swim-management/swim-start";
	}

	@GetMapping({"swim-management/swim-end.html"})
	public String toSwimEnd(){
		return "swim-management/swim-end";
	}

	@GetMapping({"goods-management/goods-list.html"})
	public String toGoodsList(){
		return "goods-management/goods-list";
	}

	@GetMapping({"goods-management/goods-add.html"})
	public String toGoodsAdd(){
		return "goods-management/goods-add";
	}

	@GetMapping({"points-management/points-goods-list.html"})
	public String toPointsGoods(){
		return "points-management/points-goods-list";
	}

	@GetMapping({"points-management/points-goods-add.html"})
	public String toPointsGoodsAdd(){
		return "points-management/points-goods-add";
	}


	@GetMapping("401")
	@ResponseBody
	public String to401(){
		return "权限不足";
	}


//	@GetMapping({"swim-management/swim-time.html"})
//	public String toSwimByTime(Integer id,Model model){
//		User user=iSwimMapper.selectUserById(id);
//		model.addAttribute("id",user.getId());
//		model.addAttribute("name",user.getUserMessage().getName());
//		model.addAttribute("balance",user.getAccount().getBalance());
//		model.addAttribute("remainTime",user.getAccount().getRemainTime());
//		return "swim-management/swim-time";
//	}






}
