package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 정훈
 * @discription 다음 디비커넥션
 * @date 2020. 3. 31.
 */
public class DConnectionMaker implements ConnectionMaker {

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
