package vn.iotstar.service;

import vn.iotstar.entity.User;

public interface IUserService {
	User login(String username,String password);
	User findByUsername(String username);
	void insert(User user);
	boolean register(String username, String password, String email, String fullname, String phone, boolean admin);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean updatePassword(String username,String password);
	boolean updatePhone(int id,String phone);
	boolean updateFullname(int id,String fullname);
	boolean updateImages(int id,String images);
}
