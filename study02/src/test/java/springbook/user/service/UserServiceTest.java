package springbook.user.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 테스트 컨텍스트 프레임워크의 Junit 확장기능
@ContextConfiguration(locations = "/applicationContext.xml") //테스트 컨텍스트가 자동으로 만들어줄 애플리케이션 컨텍스트의 위치지정
public class UserServiceTest {
	@Autowired
	UserService userService;
	private UserDao dao;

	List<User> users;
	@Before
	public void setUp() {
        users = Arrays.asList(
        		new User("a" , "박범진", "p", Level.BASIC, 49, 0),
        		new User("b" , "박범진1", "p1", Level.GOLD, 60, 0),
        		new User("c" , "박범진2", "p2", Level.BASIC, 50, 20),
        		new User("d" , "박범진3", "p3", Level.SILVER, 100, 30),
        		new User("e" , "박범진5", "p5", Level.SILVER, 60, 100)
		);
	}

	@Test
	public void bean() {
		System.out.println(">>>>>>>>>>");
		assertThat(this.userService, is(notNullValue()));
	}


	@Test
	public void upgradeLevels() {
		userService.userDao.deleteAll();



	}
}
