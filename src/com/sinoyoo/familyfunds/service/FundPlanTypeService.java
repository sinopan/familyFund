package com.sinoyoo.familyfunds.service;

import java.util.List;

import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

public interface FundPlanTypeService {

	FundPlanType add(FundPlanType fundPlanType) throws Exception;
	
	List<FundPlanType> getAll() throws Exception;
	
	List<FundPlanType> limitQuery(LimitQueryVO queryVO) throws Exception;
	
	FundPlanType getById(String id) throws Exception;
}
