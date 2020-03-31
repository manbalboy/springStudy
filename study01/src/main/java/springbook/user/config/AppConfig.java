package springbook.user.config;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import springbook.user.dao.UserDao;

@Configuration
public class AppConfig {
	public DataSource dataSource() throws ClassNotFoundException {
	    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

	    dataSource.setDriverClass((Class<Driver>)Class.forName("oracle.jdbc.driver.OracleDriver"));
	    dataSource.setUrl("jdbc:oracle:thin:@java-coder.co.kr:18903:orcl");
	    dataSource.setUsername("hr");
	    dataSource.setPassword("hr");

	    return dataSource;
	}


	@Bean
	public UserDao userDao() throws ClassNotFoundException {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}






}
