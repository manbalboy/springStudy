package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.config.AppConfig;
import springbook.user.domain.User;

/**
 * @author wglee21g@gmail.com
 */
public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		UserDao dao1 = applicationContext.getBean("userDao", UserDao.class);

		System.out.println("dao >>>>>>>>>>>>>> " + dao);
		System.out.println("dao1 >>>>>>>>>>>>>> " + dao1);
		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		dao1.add(user);
		dao1.get(user.getId());
		dao1.get(user.getId());
		CountingConnectionMaker ccm = applicationContext.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println(ccm.getCounter() + " >>>>>>>>>>>>>>>>>>>>>>>>");

//		User user = new User();
//		user.setId("whiteship");
//		user.setName("백기선");
//		user.setPassword("married");
//
//		dao.add(user);
//
//		System.out.println(user.getId() + " 등록 성공");
//
//		User user2 = dao.get(user.getId());
//		System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//		System.out.println(user2.getId() + " 조회 성공");
	}
}
