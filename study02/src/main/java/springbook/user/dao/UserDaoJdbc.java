package springbook.user.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.Level;
import springbook.user.domain.User;


/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription JDBC 샘플로 스프링을 학습하는 DAO
 * @date 2020. 4. 7.
 */
public class UserDaoJdbc implements UserDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> userMapper = (rs, rowNum) -> {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setLevel(Level.valueOf(rs.getInt(("leveltest"))));
		user.setLogin(rs.getInt("login"));
		user.setRecommend(rs.getInt("recommend"));
		return user;
	};


	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//변경후
	@Override
	public void add(final User user) {
		this.jdbcTemplate.update("insert into users(id,name,password,leveltest,login,recommend) values(?,?,?,?,?,?)", user.getId(), user.getName(), user.getPassword()
				,user.getLevel().intValue(), user.getLogin(), user.getRecommend());
	}

	@Override
	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}

	@Override
	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id} , this.userMapper);
	}

	@Override
	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}

	@Override
	public void update(User user) {
		this.jdbcTemplate.update("update users set name=?, password=?, leveltest=?, login=?, recommend=? where id=?",
				user.getName(),
				user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());

	}

}
