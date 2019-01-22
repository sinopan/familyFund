package com.sinoyoo.familyfunds.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinoyoo.familyfunds.mapper.DepositeWithdrawMapper;
import com.sinoyoo.familyfunds.pojo.DepositeWithdraw;
import com.sinoyoo.familyfunds.pojo.DepositeWithdrawChartPO;
import com.sinoyoo.familyfunds.pojo.DepositeWithdrawExample;
import com.sinoyoo.familyfunds.pojo.FundPlan;
import com.sinoyoo.familyfunds.pojo.FundPlanExample;
import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.pojo.UserExample;
import com.sinoyoo.familyfunds.pojo.DepositeWithdrawExample.Criteria;
import com.sinoyoo.familyfunds.utils.DateUtils;
import com.sinoyoo.familyfunds.utils.PageBean;
import com.sinoyoo.familyfunds.vo.DepositeWithdrawChartVO;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;
@Service
public class DepositeWithdrawServiceImpl implements DepositeWithdrawService {

	@Autowired
	private DepositeWithdrawMapper dMapper;
	
	@Autowired
	private FundPlanTypeService fPlanTypeService;

	@Autowired
	private FundPlanService fPlanService;
	
	@Autowired
	private UserService userService;
	
	public DepositeWithdraw getByPrimaryKey(String id) throws Exception {
		return dMapper.selectByPrimaryKey(id);
	}

	public DepositeWithdraw add(DepositeWithdraw depositeWithdraw) throws Exception {
		//根据fundPlanId查询
		FundPlan fundPlan = fPlanService.getFundPlanById(depositeWithdraw.getFundPlanId());
		
		//修改计划金额
		if (depositeWithdraw.getExchangeType()==1) {//存款
			fundPlan.setRealizeGoal(fundPlan.getRealizeGoal()+depositeWithdraw.getExchangeAmount());
			if (fundPlan.getAmountGoal()<=fundPlan.getRealizeGoal()) {
				fundPlan.setPlanState(1);
			}
		}else {//支出
			fundPlan.setRealizeGoal(fundPlan.getRealizeGoal()-depositeWithdraw.getExchangeAmount());
			if (fundPlan.getAmountGoal()>fundPlan.getRealizeGoal()) {
				fundPlan.setPlanState(0);
			}
		}
		//更新基金计划
		fPlanService.update(fundPlan);
		
		//设置主键
		depositeWithdraw.setId( UUID.randomUUID().toString().replace("-", "").toLowerCase());
		int i = dMapper.insert(depositeWithdraw);//返回插入数据库的结果
		if (i>0) {//成功
			return depositeWithdraw;
		}
		
		return null;
	}

	/**
	 * 分页查询（需要编写sql和接口）
	 */
	public PageBean<DepositeWithdraw> selectByLimit(Integer currPage, Integer pageSize) throws Exception {
		List<DepositeWithdraw> list = null;
		DepositeWithdrawExample example = new DepositeWithdrawExample();
		//获取总记录数
		int totalCount = dMapper.countByExample(example);
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
		list = dMapper.selectListByLimit(limitQueryVO);
		
		//将所属基金计划id赋值为name在页面展示
		if (list!=null && list.size()>0) {
			for (DepositeWithdraw depositeWithdraw : list) {
				//将用户id转换为name
				User user = userService.getUserByPrimaryKey(depositeWithdraw.getUserId());
				depositeWithdraw.setUserId(user.getName());
				
				//获取所属计划的id
				FundPlan fundPlan = fPlanService.getFundPlanById(depositeWithdraw.getFundPlanId());
				//根据id查询fundPlanType
				FundPlanType fundPlanType = fPlanTypeService.getById(fundPlan.getPlanType());
				//赋值为基金名称
				depositeWithdraw.setFundPlanId(fundPlanType.getPlanTypeName());
			}
		}
		
		PageBean<DepositeWithdraw> pageBean = new PageBean<DepositeWithdraw>();
		pageBean.setTotalCount(totalCount);
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 搜索功能
	 */
	public PageBean<DepositeWithdraw> searchListByPage(Integer currentePage, Integer pageSize, String exchangetype,
			String fundPlanType, String username) throws Exception {
		
		List<DepositeWithdraw> list = null;
		DepositeWithdrawExample example = new DepositeWithdrawExample();
		Criteria criteria = example.createCriteria();	
		
		if (!"".equals(exchangetype) && exchangetype != null) {
			criteria.andExchangeTypeEqualTo(Integer.parseInt(exchangetype));
		} 
		if(!"".equals(fundPlanType) && fundPlanType != null){
			FundPlanExample fundPlanExample = new FundPlanExample();
			com.sinoyoo.familyfunds.pojo.FundPlanExample.Criteria criteria2 = fundPlanExample.createCriteria();
			criteria2.andPlanTypeEqualTo(fundPlanType);
			
			List<FundPlan> list2 = fPlanService.getListByExample(fundPlanExample);
			if (list2==null || list2.size()==0) {
				PageBean<DepositeWithdraw> pageBean = new PageBean<DepositeWithdraw>();
				pageBean.setTotalCount(0);
				pageBean.setCurrPage(1);
				pageBean.setPageSize(pageSize);
				pageBean.setTotalPage(1);
				pageBean.setList(new ArrayList<DepositeWithdraw>());
				
				return pageBean;
			}
			List<String> fundIds = new ArrayList<String>();
			
			if (list2!=null && list2.size()>0) {
				for (FundPlan fundPlan : list2) {
					fundIds.add(fundPlan.getId());
				}
				criteria.andFundPlanIdIn(fundIds);
			}
		}
		if(!"".equals(username) && username != null){
			List<User> list2 = userService.selectByExample(new UserExample());
			String userId = "";
			if (list2!=null && list2.size()>0) {
				for (User user : list2) {
					if (username.equalsIgnoreCase(user.getName())) {
						userId = user.getId();
					}
				}
			}
			
			criteria.andUserIdEqualTo(userId);
		}
		
		//获取总记录数
		example.setOrderByClause("exchange_time desc");
		int totalCount = dMapper.countByExample(example);
		//总页数
		int totalPage = 0;
		if ((totalCount % pageSize) == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = (totalCount / pageSize) + 1;
		}
		//用户可能长时间未刷新页面，这样导致页面显示的currentPage不存在
		if (currentePage>totalPage) {
			currentePage = 1;
		}
		
		//分页查询
		list = dMapper.selectByExample(example);
		
		//将用户id转换为用户名；  将计划id转换为 计划类型名称
		if (list!=null && list.size()>0) {
			for (DepositeWithdraw depositeWithdraw : list) {
				User user = userService.getUserByPrimaryKey(depositeWithdraw.getUserId());
				FundPlan fundPlan = fPlanService.getFundPlanById(depositeWithdraw.getFundPlanId());
				FundPlanType fundPlanType2 = fPlanTypeService.getById(fundPlan.getPlanType());
				
				depositeWithdraw.setUserId(user.getName());
				depositeWithdraw.setFundPlanId(fundPlanType2.getPlanTypeName());
			}
		}
		
		PageBean<DepositeWithdraw> pageBean = new PageBean<DepositeWithdraw>();
		pageBean.setTotalCount(totalCount);
		pageBean.setCurrPage(currentePage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		return pageBean;
	}

	public List<DepositeWithdraw> getByExample(DepositeWithdrawExample example) throws Exception {
		
		return dMapper.selectByExample(example);
	}


	/**
	 * 获取图表显示
	 */
	public List<DepositeWithdrawChartVO> getChartsVO(Integer exchangeType,String conditiontype,String conditionValue) throws ParseException {
		DepositeWithdrawChartPO queryVO = new DepositeWithdrawChartPO();
		queryVO.setExchangeType(exchangeType);

		Date startDate = null;
		Date endDate = null;
		if (null == conditiontype || "".equals(conditiontype) ||
				null == conditionValue || "".equals(conditionValue)) {//如果查询条件为空，则查询本周支出
			String dateRangeStr  = DateUtils.getDateRange(new Date());
			String[] split = dateRangeStr.split(",");
			SimpleDateFormat sdf = null;
			try {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				startDate = sdf.parse(split[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			endDate = sdf.parse(split[1]);
			
			queryVO.setMinDate(startDate);
			queryVO.setMaxDate(endDate);
	     }else{
	    	 //如果是按月份查询
	    	if (Integer.valueOf(Integer.valueOf(conditiontype))==1) {
	    		Date date = new Date();
	    		date.setMonth(Integer.valueOf(conditionValue)-1);
	    		
	    		Calendar calendar = Calendar.getInstance();
	    		calendar.setTime(date);
	    		calendar.set(Calendar.DAY_OF_MONTH, 1);
	    		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    		calendar.set(Calendar.SECOND, 0);
	    		calendar.set(Calendar.MINUTE, 0);
	    		Date first = calendar.getTime();
	    		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
	    		Date end = calendar.getTime();
	    		
	    		queryVO.setMinDate(first);
	    		queryVO.setMaxDate(end);
				
			}else if (Integer.valueOf(conditiontype)==2) {//如果是按年份查询
				//.....
			}
	     }
			
		List<DepositeWithdrawChartVO> groupBy = dMapper.selectDepositeWithdrawGroupByVOList(queryVO);
		
		return groupBy;
	}

}
