package com.swim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableTransactionManagement//开启事务
@ServletComponentScan
@MapperScan("com.swim.dao")
//@AutoConfigureWebMvc
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
//@EnableJpaRepositories("com.**.dao")
public class SwimApplication {

	public static void main(String[] args) {

		SpringApplication.run(SwimApplication.class, args);
	}

}
