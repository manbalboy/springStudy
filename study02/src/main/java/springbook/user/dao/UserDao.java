package springbook.user.dao;

import java.util.List;

import springbook.user.domain.User;

/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription 기술의 독립적인 DAO만들기
 * @date 2020. 4. 8.
 */
public interface UserDao {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
}
