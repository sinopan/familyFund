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
	 * ����û����û��������ظ���
	 */
	public User addUser(User user) throws Exception {
		
		User user2 = null;//���ʧ�ܣ�����ֵΪnull
		
		//�ж��û����Ƿ��ѱ�ע��
		List<User> existMemberList = null;
		if (user != null) {
			UserExample example = new UserExample();
			example.createCriteria().andNameLike(user.getName());
			existMemberList = userMapper.selectByExample(example);
			if (existMemberList !=null && existMemberList.size()>0 ) {//����û��Ѵ���
				return user2;
			}
			//��������
			user.setId( UUID.randomUUID().toString().replace("-", "").toLowerCase());
			int i = userMapper.insert(user);//���ز������ݿ�Ľ��
			if (i>0) {//�ɹ�
				return user;
			}
		}
		return user2;
	}
	
	/**
	 * ��½
	 */
	public User login(String name, String password) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		
		List<User> list = userMapper.selectByExample(example);
		
		if (list!=null && list.size()>0) {
			return list.get(0);//�û���Ψһ
		}
		
		return null;
	}

	/**
	 * ��ҳչʾ��ҳչʾ
	 */
	public PageBean<User> getPageBean(Integer currPage, Integer pageSize) throws Exception {
		List<User> list = null;
		UserExample example = new UserExample();
		//��ȡ�ܼ�¼��
		int totalCount = userMapper.countByExample(example);
		//��ҳ��
		int totalPage = 0;
		if ((totalCount % pageSize) == 0 && totalCount != 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = (totalCount / pageSize) + 1;
		}
		//�û����ܳ�ʱ��δˢ��ҳ�棬��������ҳ����ʾ��currentPage������
		if (currPage>totalPage) {
			currPage = 1;
		}
		
		LimitQueryVO limitQueryVO = new LimitQueryVO();
		limitQueryVO.setStartRecord((currPage-1)*pageSize);
		limitQueryVO.setEndRecord(pageSize);
		
		//��ҳ��ѯ
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
	 * �޸��û�
	 */
	public Integer modifyUser(User modifyUser) throws Exception {
		
		return userMapper.updateByPrimaryKey(modifyUser);
	}

}
