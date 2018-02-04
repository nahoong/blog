package blog.dao;

import blog.model.User;

public interface UserDao {

	/**
	 * æ³¨å†Œ?”¨?ˆ·
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean register(String username, String password);

	/**
	 * ?™»å½•éªŒè¯?
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

}