package com.sinoyoo.familyfunds.service;

import java.util.List;

import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.pojo.UserExample;
import com.sinoyoo.familyfunds.utils.PageBean;

public interface UserService {

	List<User> selectByExample(UserExample example) throws Exception;
	
	User addUser(User user) throws Exception;
	
	User login(String name, String password) throws Exception;
	
	PageBean<User> getPageBean(Integer currPage, Integer pageSize) throws Exception;
	
	User getUserByPrimaryKey(String id) throws Exception;
	
	Integer modifyUser(User modifyUser) throws Exception;
}
