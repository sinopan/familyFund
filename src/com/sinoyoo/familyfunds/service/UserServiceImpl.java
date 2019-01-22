package com.sinoyoo.familyfunds.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinoyoo.familyfunds.mapper.UserMapper;
import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.pojo.UserExample;
import com.sinoyoo.familyfunds.pojo.UserExample.Criteria;
import com.sinoyoo.familyfunds.utils.PageBean;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> selectByExample(UserExample example) throws Exception {
		
		return userMapper.selectByExample(example);
	}
	/**
	 * 添加用户（用户名不能重复）
	 */
	public User addUser(User user) throws Exception {
		
		User user2 = null;//如果失败，返回值为null
		
		//判断用户名是否已被注册
		List<User> existMemberList = null;
		if (user != null) {
			UserExample example = new UserExample();
			example.createCriteria().andNameLike(user.getName());
			existMemberList = userMapper.selectByExample(example);
			if (existMemberList !=null && existMemberList.size()>0 ) {//如果用户已存在
				return user2;
			}
			//设置主键
			user.setId( UUID.randomUUID().toString().replace("-", "").toLowerCase());
			int i = userMapper.insert(user);//返回插入数据库的结果
			if (i>0) {//成功
				return user;
			}
		}
		return user2;
	}
	
	/**
	 * 登陆
	 */
	public User login(String name, String password) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		
		List<User> list = userMapper.selectByExample(example);
		
		if (list!=null && list.size()>0) {
			return list.get(0);//用户名唯一
		}
		
		return null;
	}

	/**
	 * 分页展示分页展示
	 */
	public PageBean<User> getPageBean(Integer currPage, Integer pageSize) throws Exception {
		List<User> list = null;
		UserExample example = new UserExample();
		//获取总记录数
		int totalCount = userMapper.countByExample(example);
		//总页数
		int totalPage = 0;
		if ((totalCount % pageSize) == 0 && totalCount != 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = (totalCount / pageSize) + 1;
		}
		//用户可能长时间未刷新页面，这样导致页面显示的currentPage不存在
		if (currPage>totalPage) {
			currPage = 1;
		}
		
		LimitQueryVO limitQueryVO = new LimitQueryVO();
		limitQueryVO.setStartRecord((currPage-1)*pageSize);
		limitQueryVO.setEndRecord(pageSize);
		
		//分页查询
		list = userMapper.selectListByLimit(limitQueryVO);
		
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setTotalCount(totalCount);
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	
	public User getUserByPrimaryKey(String id) throws Exception {
		
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 修改用户
	 */
	public Integer modifyUser(User modifyUser) throws Exception {
		
		return userMapper.updateByPrimaryKey(modifyUser);
	}

}
