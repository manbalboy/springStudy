package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 정훈
 * @discription DB커넥션 인터페이스 
 * @date 2020. 3. 31.
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
