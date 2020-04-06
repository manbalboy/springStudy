package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription 스프링템플릿 메소드 사용하기전 템플릿메소드 체험
 * @date 2020. 4. 7.
 */
public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = this.dataSource.getConnection();

			ps = stmt.makePreparedStatement(c);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {try {ps.close();} catch (SQLException e) {}}
			if (c != null) {try {c.close();} catch (SQLException e) {}}
		}
	}
}
