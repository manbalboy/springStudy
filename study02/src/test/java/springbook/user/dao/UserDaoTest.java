package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;


/**
 * 테스트 스터디
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @discription
 * @date 2020. 4. 2.
 */

@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 테스트 컨텍스트 프레임워크의 Junit 확장기능
@ContextConfiguration(locations = "/applicationContext.xml") //테스트 컨텍스트가 자동으로 만들어줄 애플리케이션 컨텍스트의 위치지정
public class UserDaoTest {
	private UserDao dao;
	private User user;
	private User user1;
	private User user2;
	//의존성주입
	@Autowired
	private ApplicationContext applicationContext ;

	@Before
	public void setUp() {
		System.out.println(this.applicationContext);
		System.out.println(this);
		this.dao = this.applicationContext.getBean("userDao", UserDao.class);
		this.user = new User("manbalboy", "정훈", "test1");
		this.user1 = new User("kimsj", "김선중", "test2");
		this.user2 = new User("Namsh", "남수현", "test3");
	}

	@Test
	public void getAll() throws SQLException, ClassNotFoundException{
		dao.deleteAll();
		List<User> users0 = dao.getAll();
		assertThat(users0.size(), is(0));

		dao.add(user);
		dao.add(user1);
		dao.add(user2);
		users0 = dao.getAll();
		assertThat(users0.size(), is(3));
	}

	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException{
		dao.deleteAll();
		assertThat("삭제후 카운트 0",dao.getCount(),is(0));

		dao.add(user);
		dao.add(user1);
		dao.add(user2);

		assertThat("저장후 카운트 3",dao.getCount(),is(3));


		User userTest = dao.get(user.getId());
		assertThat("패스워드 검증 :", userTest.getPassword() , is(user.getPassword() ) );
		assertThat("이름 검증 :", userTest.getName(), is(user.getName() ) );

		User userTest1 = dao.get(user1.getId());
		assertThat("패스워드 검증 :", userTest1.getPassword() , is(user1.getPassword() ) );
		assertThat("이름 검증 :", userTest1.getName(), is(user1.getName() ) );

		User userTest2 = dao.get(user2.getId());
		assertThat("패스워드 검증 :", userTest2.getPassword() , is(user2.getPassword() ) );
		assertThat("이름 검증 :", userTest2.getName(), is(user2.getName() ) );

	}

	@Test
	public void count() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		assertThat("삭제후 카운트 0",dao.getCount(),is(0));

		dao.add(user);
		assertThat("저장후 카운트 1",dao.getCount(),is(1));

		dao.add(user1);
		assertThat("저장후 카운트 2",dao.getCount(),is(2));

		dao.add(user2);
		assertThat("저장후 카운트 3",dao.getCount(),is(3));
	}

	@Test(expected = EmptyResultDataAccessException.class) // 테스트중에 발생할 것으로 기대하는 예외 클래스를 지정해준다.
	public void getUSerFailure() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		assertThat("삭제후 카운트 0",dao.getCount(),is(0));

		dao.get("nulllllllllll!");
	}



}
