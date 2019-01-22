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
		//����fundPlanId��ѯ
		FundPlan fundPlan = fPlanService.getFundPlanById(depositeWithdraw.getFundPlanId());
		
		//�޸ļƻ����
		if (depositeWithdraw.getExchangeType()==1) {//���
			fundPlan.setRealizeGoal(fundPlan.getRealizeGoal()+depositeWithdraw.getExchangeAmount());
			if (fundPlan.getAmountGoal()<=fundPlan.getRealizeGoal()) {
				fundPlan.setPlanState(1);
			}
		}else {//֧��
			fundPlan.setRealizeGoal(fundPlan.getRealizeGoal()-depositeWithdraw.getExchangeAmount());
			if (fundPlan.getAmountGoal()>fundPlan.getRealizeGoal()) {
				fundPlan.setPlanState(0);
			}
		}
		//���»���ƻ�
		fPlanService.update(fundPlan);
		
		//��������
		depositeWithdraw.setId( UUID.randomUUID().toString().replace("-", "").toLowerCase());
		int i = dMapper.insert(depositeWithdraw);//���ز������ݿ�Ľ��
		if (i>0) {//�ɹ�
			return depositeWithdraw;
		}
		
		return null;
	}

	/**
	 * ��ҳ��ѯ����Ҫ��дsql�ͽӿڣ�
	 */
	public PageBean<DepositeWithdraw> selectByLimit(Integer currPage, Integer pageSize) throws Exception {
		List<DepositeWithdraw> list = null;
		DepositeWithdrawExample example = new DepositeWithdrawExample();
		//��ȡ�ܼ�¼��
		int totalCount = dMapper.countByExample(example);
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
		list = dMapper.selectListByLimit(limitQueryVO);
		
		//����������ƻ�id��ֵΪname��ҳ��չʾ
		if (list!=null && list.size()>0) {
			for (DepositeWithdraw depositeWithdraw : list) {
				//���û�idת��Ϊname
				User user = userService.getUserByPrimaryKey(depositeWithdraw.getUserId());
				depositeWithdraw.setUserId(user.getName());
				
				//��ȡ�����ƻ���id
				FundPlan fundPlan = fPlanService.getFundPlanById(depositeWithdraw.getFundPlanId());
				//����id��ѯfundPlanType
				FundPlanType fundPlanType = fPlanTypeService.getById(fundPlan.getPlanType());
				//��ֵΪ��������
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
	 * ��������
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
		
		//��ȡ�ܼ�¼��
		example.setOrderByClause("exchange_time desc");
		int totalCount = dMapper.countByExample(example);
		//��ҳ��
		int totalPage = 0;
		if ((totalCount % pageSize) == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = (totalCount / pageSize) + 1;
		}
		//�û����ܳ�ʱ��δˢ��ҳ�棬��������ҳ����ʾ��currentPage������
		if (currentePage>totalPage) {
			currentePage = 1;
		}
		
		//��ҳ��ѯ
		list = dMapper.selectByExample(example);
		
		//���û�idת��Ϊ�û�����  ���ƻ�idת��Ϊ �ƻ���������
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
	 * ��ȡͼ����ʾ
	 */
	public List<DepositeWithdrawChartVO> getChartsVO(Integer exchangeType,String conditiontype,String conditionValue) throws ParseException {
		DepositeWithdrawChartPO queryVO = new DepositeWithdrawChartPO();
		queryVO.setExchangeType(exchangeType);

		Date startDate = null;
		Date endDate = null;
		if (null == conditiontype || "".equals(conditiontype) ||
				null == conditionValue || "".equals(conditionValue)) {//�����ѯ����Ϊ�գ����ѯ����֧��
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
	    	 //����ǰ��·ݲ�ѯ
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
				
			}else if (Integer.valueOf(conditiontype)==2) {//����ǰ���ݲ�ѯ
				//.....
			}
	     }
			
		List<DepositeWithdrawChartVO> groupBy = dMapper.selectDepositeWithdrawGroupByVOList(queryVO);
		
		return groupBy;
	}

}
