package com.sinoyoo.familyfunds.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinoyoo.familyfunds.mapper.FundPlanTypeMapper;
import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.pojo.FundPlanTypeExample;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

@Service
public class FundPlanTypeServiceImpl implements FundPlanTypeService {

	@Autowired
	private FundPlanTypeMapper fTypeMapper;
	
	public FundPlanType add(FundPlanType fundPlanType) throws Exception {
		FundPlanType addFundPlanType = null;
		//Éú³ÉÖ÷¼ü
		fundPlanType.setId( UUID.randomUUID().toString().replace("-", "").toLowerCase());
		
		int i = fTypeMapper.insert(fundPlanType);
		if (i>0) {
			addFundPlanType = fundPlanType;
		}
		return addFundPlanType;
	}

	public List<FundPlanType> getAll() throws Exception {
		FundPlanTypeExample example = new FundPlanTypeExample();
		return fTypeMapper.selectByExample(example);
	}

	public List<FundPlanType> limitQuery(LimitQueryVO queryVO) throws Exception {
		
		return fTypeMapper.selectListByLimit(queryVO);
	}

	public FundPlanType getById(String id) throws Exception {
		FundPlanType fundPlanType = null;
		
		FundPlanTypeExample example = new FundPlanTypeExample();
		example.createCriteria().andIdEqualTo(id);
		List<FundPlanType> list = fTypeMapper.selectByExample(example);
		
		if (list!=null && list.size()>0) {
			fundPlanType = list.get(0);
		}
		return fundPlanType;
	}

}
