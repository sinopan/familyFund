package com.sinoyoo.familyfunds.service;

import java.text.ParseException;
import java.util.List;

import com.sinoyoo.familyfunds.pojo.DepositeWithdraw;
import com.sinoyoo.familyfunds.pojo.DepositeWithdrawExample;
import com.sinoyoo.familyfunds.utils.PageBean;
import com.sinoyoo.familyfunds.vo.DepositeWithdrawChartVO;

public interface DepositeWithdrawService {

	DepositeWithdraw getByPrimaryKey(String id) throws Exception;
	
	DepositeWithdraw add(DepositeWithdraw depositeWithdraw) throws Exception;
	
	//��ҳ��ѯ
	PageBean<DepositeWithdraw> selectByLimit(Integer currPage, Integer pageSize) throws Exception;
	
	//��������
	PageBean<DepositeWithdraw> searchListByPage(Integer currentePage, Integer pageSize, String exchangetype, String fundPlanType, String username) throws Exception;
	
	List<DepositeWithdraw> getByExample(DepositeWithdrawExample example) throws Exception;
	
	//���ط����ѯ���
	List<DepositeWithdrawChartVO> getChartsVO(Integer exchangeType,String conditiontype,String conditionValue) throws ParseException;
	
}
