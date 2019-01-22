package com.sinoyoo.familyfunds.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinoyoo.familyfunds.mapper.FundPlanMapper;
import com.sinoyoo.familyfunds.pojo.FundPlan;
import com.sinoyoo.familyfunds.pojo.FundPlanExample;
import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.utils.PageBean;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

@Service
public class FundPlanServiceImpl implements FundPlanService {

	@Autowired
	private FundPlanMapper planMapper;
	
	@Autowired
	private FundPlanTypeService planTypeService;
	
	/**
	 * 添加成功返回添加的对象，失败返回null
	 */
	public FundPlan addFundPlan(FundPlan fundPlan) throws Exception {
		FundPlan addFundPlan = null;
		
		//生成主键
		fundPlan.setId( UUID.randomUUID().toString().replace("-", "").toLowerCase());
		
		int i = planMapper.insert(fundPlan);
		if (i>0) {
			addFundPlan = fundPlan;
		}
		
		return addFundPlan;
	}

	/**
	 * 分页查询返回PageBean<FundPlan>
	 */
	public PageBean<FundPlan> getPageBean(Integer currPage, Integer pageSize) throws Exception {
		List<FundPlan> list = null;
		FundPlanExample example = new FundPlanExample();
		//获取总记录数
		int totalCount = planMapper.countByExample(example);
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
		list = planMapper.selectListByLimit(limitQueryVO);
		
		//为了页面展示方便，将类型planType赋值为类型名称
		if (list!=null && list.size()>0) {
			for (FundPlan fundPlan : list) {
				FundPlanType planType = planTypeService.getById(fundPlan.getPlanType());
				fundPlan.setPlanType(planType.getPlanTypeName());
			}
		}
		
		PageBean<FundPlan> pageBean = new PageBean<FundPlan>();
		pageBean.setTotalCount(totalCount);
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		return pageBean; 
	}

	public FundPlan getFundPlanById(String id) throws Exception {
		
		return planMapper.selectByPrimaryKey(id);
	}

	public List<FundPlan> getListByExample(FundPlanExample example) throws Exception {
		
		return planMapper.selectByExample(example);
	}

	public Integer update(FundPlan fundPlan) {
		
		return planMapper.updateByPrimaryKey(fundPlan);
	}
	
	public List<FundPlan> getFundPlanListByExample(FundPlanExample fundPlanExample) {
		fundPlanExample.setOrderByClause("begin_time desc");
		return planMapper.selectByExample(fundPlanExample);
	}

}
