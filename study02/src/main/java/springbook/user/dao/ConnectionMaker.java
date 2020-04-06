package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription DB Connection 인터페이스
 * @date 2020. 4. 7.
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
