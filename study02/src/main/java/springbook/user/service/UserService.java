package springbook.user.service;

import java.util.List;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @githum https://github.com/manbalboy
 * @discription User를 관리하는 service
 * @date 2020. 4. 9.
 */
public class UserService {
	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		for(User user : users) {
			Boolean changed = null;
			if(user.getLevel() == Level.BASIC && user.getLogin() >=50) {
				user.setLevel(Level.SILVER);
				changed = true;
			}
			else if (user.getLevel() == Level.SILVER && user.getRecommend() >= 30) {
				user.setLevel(Level.GOLD);
				changed = true;
			}
			else if (user.getLevel() == Level.GOLD) {
				changed = false;
			} else {
				changed = false;
			}

			if(changed) {
				userDao.update(user);
			}
		}
	}
}
