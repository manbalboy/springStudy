package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;


/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @discription
 * @date 2020. 4. 2.
 */
public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException{
		ApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");
		//AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao dao1 = applicationContext.getBean("userDao", UserDao.class);
		dao1.deleteAll();
		assertThat("삭제후 카운트 0",dao1.getCount(),is(0));

		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		dao1.add(user);


		assertThat("저장후 카운트 1",dao1.getCount(),is(1));
		User user2 = dao1.get(user.getId());
		dao1.get(user2.getId());


		assertThat("패스워드 검증 :", user2.getPassword().toString(), is(user.getPassword() ) );
		assertThat("이름 검증 :", user2.getName().toString(), is(user.getName() ) );




	}



}
