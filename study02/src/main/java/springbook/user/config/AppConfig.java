package springbook.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.CountingConnectionMaker;
import springbook.user.dao.DConnectionMaker;
import springbook.user.dao.UserDao;

@Configuration
public class AppConfig {
	@Bean
	public ConnectionMaker connectionMaker() throws ClassNotFoundException {

	    return new CountingConnectionMaker(realConnetionMaker());
	}


	@Bean
	public UserDao userDao() throws ClassNotFoundException {
		UserDao userDao = new UserDao(connectionMaker());
		return userDao;
	}


	@Bean
	public ConnectionMaker realConnetionMaker() {
		return new DConnectionMaker();
	}






}
