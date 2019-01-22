package com.sinoyoo.familyfunds.service;

import java.util.List;
import com.sinoyoo.familyfunds.pojo.FundPlan;
import com.sinoyoo.familyfunds.pojo.FundPlanExample;
import com.sinoyoo.familyfunds.utils.PageBean;

public interface FundPlanService {

	FundPlan addFundPlan(FundPlan fundPlan) throws Exception;
	
	PageBean<FundPlan> getPageBean(Integer currPage, Integer pageSize) throws Exception;
	
	FundPlan getFundPlanById(String id) throws Exception;
	
	List<FundPlan> getListByExample(FundPlanExample example) throws Exception;
	
	Integer update(FundPlan fundPlan);
	
	List<FundPlan> getFundPlanListByExample(FundPlanExample fundPlanExample);
}
