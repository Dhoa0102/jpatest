package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.User;


public interface IUserDao {
	void insert(User  user);
	void update(User user);
	void delete(int id) throws Exception;
	User findByUsername(String username);
	List<User> findAll();
	List<User> findAll(int page,int pagesize);
	int count();
	boolean checkExistUsername(String username);
	boolean checkExistEmail(String email);
	boolean checkExistPhone(String phone);
}
