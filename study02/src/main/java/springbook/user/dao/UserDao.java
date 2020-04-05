package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

/**
 * @author 정훈
 * @discription UserDao Data access Object
 * @date 2020. 3. 31.
 */
public class UserDao {
	public DataSource dataSource;
	private JdbcContext jdbcContext;


	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
		this.dataSource = dataSource; // 적용되지 않은 메서드를 위해 남겨줌
	}

	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public void add(final User user) throws ClassNotFoundException, SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
					ps.setString(1, user.getId() );
					ps.setString(2, user.getName() );
					ps.setString(3, user.getPassword() );
					return ps;
				}
			}
		);
	}


	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		User user = null;

		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		rs.close();
		ps.close();
		c.close();

		if(user == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return user;
	}

	public void deleteAll() throws ClassNotFoundException, SQLException {
		StatementStrategy st = new DeleteAllStatement(); // 선정한 전략 클래스의 오브젝트 생성
		jdbcContextWithStatementStrategy(st); // 컨텍스트 호출 전략 오브젝트 전달
	}

	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection c = null ;
		PreparedStatement ps = null;

		try {
			c = dataSource.getConnection();

			ps = stmt.makePreparedStatement(c);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {

				}
			}

			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {

				}
			}
		}

	}


	public PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("delete from users");
		return ps;
	};

	public int getCount() throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
			rs.next();

			return rs.getInt(1);
		} catch (SQLException e) {
			throw e ;
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}

			if (ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {

				}
			}

			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}




}
