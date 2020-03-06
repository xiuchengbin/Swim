package com.swim;

import com.swim.controller.UserManagementController;
import com.swim.dao.ILoginMapper;
import com.swim.dao.IUserInfoMapper;
import com.swim.dao.IUserListMapper;
import com.swim.dao.IUserManagementMapper;
import com.swim.entity.User;
import com.swim.entity.UserMessage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwimApplicationTests {
	@Autowired
	private ILoginMapper loginMapper;
	@Autowired
	private IUserInfoMapper iUserInfoMapper;
	@Autowired
	private IUserManagementMapper iUserManagementMapper;
	@Autowired
	private IUserListMapper iUserListMapper;
	@Autowired
	private UserManagementController userManagementController;
	@Autowired
	private RedisTemplate redisTemplate;
	@Test
	void contextLoads() {
	}

	@Test
	public void Test(){
		User user=new User();
		user.setUsername("1");
		user.setPassword("1");
		System.out.println(loginMapper.selectUserByUsernameAndPassword(user));
	}

	@Test
	public void Test2(){
//		UserMessage message=new UserMessage();
//		message.setId(1);message.setName("名字");message.setPhoneNumber("13399999999");message.setSex("男");message.setBirthday(new Date(System.currentTimeMillis()));
//		iUserInfo.updateUserMessageById(message);
//		User user=new User();
//		user.setUsername("2");
//		user.setPassword("2");
//		iUserManagementMapper.insertUser(user);
//		System.out.println(user.getId());
//		List list=iUserListMapper.selectUser(0,2);
//		for (Object o : list) {
//			System.out.println(o);
//		}

//		for (int i=4;i<=100;i++){
//			User user=new User();
//			user.setUsername(i+"");
//			user.setPassword(i+"");
//			UserMessage userMessage=new UserMessage();
//			userMessage.setSex("男");
//			userMessage.setBirthday(new Date(System.currentTimeMillis()-1000000000));
//			userMessage.setPhoneNumber("13999999999");
//			userMessage.setName("测试"+i);
//			System.out.println(userManagementController.addUser(user,userMessage,100f));
//		}
//		System.out.println(iUserListMapper.selectUserByType(0,10,"id",1));
//		Map<String,Object> map=(Map)redisTemplate.opsForValue().get("data");
//		System.out.println(map.get("day_click"));
	}



}
