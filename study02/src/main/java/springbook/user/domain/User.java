package springbook.user.domain;


/**
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @discription
 * @date 2020. 4. 3.
 */
public class User {
	private String id;
	private String name;
	private String password;
	Level level;
	int login;
	int recommend;

	public User(String id, String name, String password, Level level, int logint, int recommend ) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.level  = level;
		this.login = logint;
		this.recommend = recommend;
	}




	public Level getLevel() {
		return level;
	}




	public void setLevel(Level level) {
		this.level = level;
	}




	public int getLogin() {
		return login;
	}




	public void setLogin(int login) {
		this.login = login;
	}




	public int getRecommend() {
		return recommend;
	}




	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}




	//자바빈 규약을 따르는 클래스에 생성자를 명시적으로 추가했을 때는 파라미터가 없는 디폴트 생성자도 함께 정의해주는것을 잊지말자.
	public User() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

