package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription connection 정보 빼낸 Class
 * @date 2020. 4. 7.
 */
public class DConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		String user = "hr";
        String pw = "hr";
        String url = "jdbc:oracle:thin:@java-coder.co.kr:18903:orcl";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection  conn = DriverManager.getConnection(url, user, pw);

		return conn;
	}
    //
}
