package com.sinoyoo.familyfunds.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinoyoo.familyfunds.mapper.FamilyActivityMapper;
import com.sinoyoo.familyfunds.pojo.FamilyActivity;
import com.sinoyoo.familyfunds.pojo.FamilyActivityExample;
import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.utils.PageBean;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

@Service
public class FamilyActivityServiceImpl implements FamilyActivityService {

	@Autowired
	private FamilyActivityMapper activityMapper;
	
	@Autowired
	private UserService userService;

	public FamilyActivity addFamilyActivity(FamilyActivity familyActivity) throws Exception {
		FamilyActivity familyActivity2 = null;// 如果失败，返回值为null

		// 设置主键
		familyActivity.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		int i = activityMapper.insert(familyActivity);// 返回插入数据库的结果
		if (i > 0) {// 成功
			familyActivity2 = familyActivity;
			return familyActivity2;
		}
		return familyActivity2;
	}

	public PageBean<FamilyActivity> getPageBean(Integer currPage, Integer pageSize) throws Exception {
		List<FamilyActivity> list = null;
		FamilyActivityExample example = new FamilyActivityExample();
		// 获取总记录数
		int totalCount = activityMapper.countByExample(example);
		// 总页数
		int totalPage = 0;
		if ((totalCount % pageSize) == 0 && totalCount != 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = (totalCount / pageSize) + 1;
		}
		// 用户可能长时间未刷新页面，这样导致页面显示的currentPage不存在
		if (currPage > totalPage) {
			currPage = 1;
		}

		LimitQueryVO limitQueryVO = new LimitQueryVO();
		limitQueryVO.setStartRecord((currPage - 1) * pageSize);
		limitQueryVO.setEndRecord(pageSize);

		// 分页查询
		list = activityMapper.selectListByLimit(limitQueryVO);
		
		//将策划人planer和参与人aprtcipants由用户di改为用户name便于页面显示
		if (list!=null && list.size()>0) {
			for (FamilyActivity familyActivity : list) {
				//将
				User User = userService.getUserByPrimaryKey(familyActivity.getPlaner());
				familyActivity.setPlaner(User.getName());
				
				String idsStr = familyActivity.getParticipants();
				String[] ids = idsStr.split(",");
				
				StringBuffer sb = new StringBuffer();
				if (ids.length>0) {
					for (String memberId : ids) {
						User familyMemeber2 = userService.getUserByPrimaryKey(memberId);
						sb.append(familyMemeber2.getName()+",");
					}
				}
				familyActivity.setParticipants(sb.toString().substring(0, sb.length()-1));
			}
		}
		
		PageBean<FamilyActivity> pageBean = new PageBean<FamilyActivity>();
		pageBean.setTotalCount(totalCount);
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);

		return pageBean;
	}

	public List<FamilyActivity> getAll() throws Exception {
		
		return activityMapper.selectByExample(new FamilyActivityExample());
	}

}
