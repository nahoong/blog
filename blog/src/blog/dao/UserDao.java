package blog.dao;

import blog.model.User;

public interface UserDao {

	/**
	 * 注册?��?��
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean register(String username, String password);

	/**
	 * ?��录验�?
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

}