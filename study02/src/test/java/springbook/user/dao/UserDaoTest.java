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

import springbook.user.domain.Level;
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
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
	}

	@Autowired
	private ApplicationContext applicationContext ;

	@Before
	public void setUp() {
		System.out.println(this.applicationContext);
		System.out.println(this);
		this.dao = this.applicationContext.getBean("userDao", UserDao.class);
		this.user = new User("manbalboy", "정훈", "test1" , Level.BASIC, 1, 0);
		this.user1 = new User("kimsj", "김선중", "test2" , Level.SILVER, 55 , 10);
		this.user2 = new User("Namsh", "남수현", "test3", Level.GOLD, 100, 40);
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
		checkSameUser(userTest, user);

		User userTest1 = dao.get(user1.getId());
		checkSameUser(userTest1, user1);

		User userTest2 = dao.get(user2.getId());
		checkSameUser(userTest2, user2);

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
//	@Test(expected = DataAccessException.class)
	//@Test
	public void duplcaiateKey() {
		dao.deleteAll();

		dao.add(user);
		dao.add(user);
	}


	@Test
	public void update() {
		dao.deleteAll();
		dao.add(user);
		dao.add(user1);
		user.setName("오민구");
		user.setPassword("spring566");
		user.setLevel(Level.GOLD);
		user.setLogin(1000);
		user.setRecommend(999);
		dao.update(user);
		User user1update = dao.get(user.getId());
		checkSameUser(user, user1update);
		User user2same = dao.get(user1.getId());
		checkSameUser(user1, user2same);
	}



}
