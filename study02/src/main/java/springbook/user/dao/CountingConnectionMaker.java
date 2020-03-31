package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 정훈
 * @discription
 * @date 2020. 4. 1.
 */
public class CountingConnectionMaker implements ConnectionMaker {
	int count = 0;
	private ConnectionMaker realConnectionMaker;

	public CountingConnectionMaker( ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}



	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.count++;
		return  realConnectionMaker.makeConnection();
	}

	public int getCounter() {
		return this.count;
	}

}
