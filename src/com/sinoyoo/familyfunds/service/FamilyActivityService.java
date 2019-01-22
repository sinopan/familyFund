package com.sinoyoo.familyfunds.service;


import java.util.List;

import com.sinoyoo.familyfunds.pojo.FamilyActivity;
import com.sinoyoo.familyfunds.utils.PageBean;

public interface FamilyActivityService {

	FamilyActivity addFamilyActivity(FamilyActivity familyActivity) throws Exception;
	
	PageBean<FamilyActivity> getPageBean(Integer currPage, Integer pageSize) throws Exception;
	
	List<FamilyActivity> getAll() throws Exception;
	
}
