package springbook.user.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.User;


/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription JDBC 샘플로 스프링을 학습하는 DAO
 * @date 2020. 4. 7.
 */
public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> userMapper = (rs, rowNum) -> {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		return user;
	};


	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//변경후
	public void add(final User user) throws ClassNotFoundException, SQLException {
		this.jdbcTemplate.update("insert into users(id,name,password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id} , this.userMapper);
	}

	public void deleteAll() throws ClassNotFoundException, SQLException {
		this.jdbcTemplate.update("delete from users");
	}

	public int getCount() throws ClassNotFoundException, SQLException {
		return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}

    //변경전
//	public void add(final User user) throws ClassNotFoundException, SQLException {
//		this.jdbcContext.workWithStatementStrategy(
//			new StatementStrategy() {
//				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//					PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
//					ps.setString(1, user.getId() );
//					ps.setString(2, user.getName() );
//					ps.setString(3, user.getPassword() );
//					return ps;
//				}
//			}
//		);
//	}


//	public User get(String id) throws ClassNotFoundException, SQLException {
//		Connection c = dataSource.getConnection();
//
//		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
//		ps.setString(1, id);
//
//		ResultSet rs = ps.executeQuery();
//
//		User user = null;
//
//		if(rs.next()) {
//			user = new User();
//			user.setId(rs.getString("id"));
//			user.setName(rs.getString("name"));
//			user.setPassword(rs.getString("password"));
//		}
//		rs.close();
//		ps.close();
//		c.close();
//
//		if(user == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//
//		return user;
//	}

//	public void deleteAll() throws ClassNotFoundException, SQLException {
//		this.jdbcTemplate.update(
//			new PreparedStatementCreator() {
//				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//					return con.prepareStatement("delete from users");
//				}
//			}
//		);
//	}

//	public int getCount() throws ClassNotFoundException, SQLException {
//		return this.jdbcTemplate.query(new PreparedStatementCreator() {
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				return con.prepareStatement("select count(*) from users");
//			}
//		}, new ResultSetExtractor<Integer>() {
//			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
//				rs.next();
//				return rs.getInt(1);
//			}
//		});

//		기존소스
//		Connection c = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			c = dataSource.getConnection();
//			ps = c.prepareStatement("select count(*) from users");
//			rs = ps.executeQuery();
//			rs.next();
//
//			return rs.getInt(1);
//		} catch (SQLException e) {
//			throw e ;
//		} finally {
//			if (rs!=null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//
//				}
//			}
//
//			if (ps!=null) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//
//				}
//			}
//
//			if (c != null) {
//				try {
//					c.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//	}
}
