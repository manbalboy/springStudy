package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription Connection Count
 * @date 2020. 4. 7.
 */
public class CountingConnectionMaker implements ConnectionMaker {
	int count = 0;
	private ConnectionMaker realConnectionMaker;

	public CountingConnectionMaker( ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}



	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.count++;
		return  realConnectionMaker.makeConnection();
	}

	public int getCounter() {
		return this.count;
	}

}
