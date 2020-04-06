package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription Spring으로 가기전 전략패턴 인터페이스 구현
 * @date 2020. 4. 7.
 */
public interface StatementStrategy {
	PreparedStatement makePreparedStatement(Connection c) throws SQLException;
}
