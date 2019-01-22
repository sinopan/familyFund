package com.sinoyoo.familyfunds.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sinoyoo.familyfunds.pojo.FundPlan;
import com.sinoyoo.familyfunds.pojo.FundPlanExample;
import com.sinoyoo.familyfunds.pojo.FundPlanExample.Criteria;
import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.result.OperationResult;
import com.sinoyoo.familyfunds.service.FundPlanService;
import com.sinoyoo.familyfunds.service.FundPlanTypeService;
import com.sinoyoo.familyfunds.utils.DateUtils;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;
import com.sinoyoo.familyfunds.utils.PageBean;

/**
 * ����ƻ�controller
 * @author ASUS-LXP
 *
 */
@Controller
@RequestMapping("/fundplan")
public class FundPlanController {
	
	@Autowired
	private FundPlanService fundPlanService;
	
	@Autowired
	private FundPlanTypeService planTypeService;
	
	/**
	 * ��ӻ���ƻ���ע��ajax�ύ�ı�������ΪString���ͣ�����
	 * 			 ��������ת���������ֶ�������ת��Ϊ��ȷ�����ͣ�����
	 * 			 �����ʱ��������ת�������Կ���ʹ�ã������Ĳ���������string����
	 * @param amountGoal
	 * @param realizeGoal
	 * @param beginTime
	 * @param endTime
	 * @param planType
	 * @param planState
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void addFundPlan(@RequestParam("amountGoal") String amountGoal,
							@RequestParam("realizeGoal") String realizeGoal,
							@RequestParam("beginTime") Date beginTime,
							@RequestParam("endTime") Date endTime,
							@RequestParam("planType") String planType,
							@RequestParam("planState") String planState,
							@RequestParam("note") String note,
							HttpServletResponse response) throws IOException{

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			FundPlan fundPlan = new FundPlan();
			fundPlan.setAmountGoal(Float.parseFloat(amountGoal));
			fundPlan.setRealizeGoal(Float.parseFloat(realizeGoal));
			fundPlan.setBeginTime(beginTime);
			fundPlan.setEndTime(endTime);
			fundPlan.setPlanType(planType);
			fundPlan.setPlanState(Integer.parseInt(planState));
			fundPlan.setNote(note);
			
			FundPlan addFundPlan = fundPlanService.addFundPlan(fundPlan);
			if (addFundPlan != null) {
				OperationResult<FundPlan> result = new OperationResult<FundPlan>(200, true, addFundPlan);
				out.print(FastJsonUtils.BeanToJson(result));
			}else {
				OperationResult<FundPlan> result = new OperationResult<FundPlan>(500, false, null);
				out.write(FastJsonUtils.BeanToJson(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.flush();
			out.close();
		}
	}
	
	/**
	 * ����ҳ��ѯ�������PageBean�������б�ҳ��
	 * @param currPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/list/{currPage}")
	public String showAllRecords(@PathVariable("currPage") Integer currPage, Model model){
		
		PageBean<FundPlan> pagebean = null;
		try {
			pagebean = fundPlanService.getPageBean(currPage, 5);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagebean", pagebean);
		
		return "/fund_list";
	}

	@RequestMapping("/add_page")
	public String toAddPage(){
		
		return "/fund_add";
	}
	
	/**
	 * ��ȡ�Ѵ����ġ��ҿ�ʼ�ͽ������ڰ����û���ҳ��ѡ����ʱ��ļƻ��б�
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/existfundplan/{exchangeTime}")
	@ResponseBody
	public void getExistCorrectFundPlanTypes(@PathVariable("exchangeTime") Date exchangeTime,
							HttpServletResponse response) throws Exception{

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//key������ƻ�id   value���ƻ�����+��ע
		Map<String, String> fundPlanTypesMap = null;
		//��ȡ��������ʱ����·ݵ���ֹ����
		String monthRange = DateUtils.getMonthRange(exchangeTime);
		String[] split = monthRange.split(",");
		
		FundPlanExample example = new FundPlanExample();
		Criteria criteria = example.createCriteria();
		criteria.andBeginTimeBetween(new Date(Long.parseLong(split[0])), new Date(Long.parseLong(split[1])));
		
		List<FundPlan> list = fundPlanService.getListByExample(example);
		if (list!=null && list.size()>0) {
			fundPlanTypesMap = new HashMap<String, String>();
			for (FundPlan fundPlan : list) {
				//��ȡ����������ʾ
				FundPlanType fundPlanType = planTypeService.getById(fundPlan.getPlanType());
				fundPlanTypesMap.put(fundPlan.getId(), fundPlanType.getPlanTypeName()+": "+fundPlan.getNote());
			}
		}
		
		
		if (fundPlanTypesMap != null) {
			out.print(FastJsonUtils.BeanToJson(fundPlanTypesMap));
		}else {
			out.write("");
		}
		
	}
	
	/**
	 * ��֧ͼ��չʾ(��ת)
	 */
	@RequestMapping("/chart_display")
	public String showPage(){
		
		return "fund_chart_display";
	}

	/**
	 * ����3D��״ͼչʾ
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/fund_chart_show")
	@ResponseBody
	public void getFundChartsList(@RequestParam("quarter") String quarter, HttpServletResponse response) throws Exception{
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		int jidu = 1;
		if (quarter!=null && !"".equals(quarter)) {
			jidu = Integer.parseInt(quarter);
		}
		//���㿪ʼ����ƻ��Ŀ�ʼʱ��
		Date startTime = DateUtils.getQuarterStartTime(jidu);
		Date endTime = DateUtils.getQuarterEndTime(jidu);
		
		FundPlanExample example = new FundPlanExample();
		example.setOrderByClause("begin_time");
		Criteria criteria = example.createCriteria();
		criteria.andBeginTimeBetween(startTime, endTime);
		
		List<FundPlan> list = fundPlanService.getFundPlanListByExample(example);
		
		//�������ƻ�idת��Ϊ�ƻ���������
		if (list!=null && list.size()>0) {
			for (FundPlan fundPlan : list) {
				FundPlanType fundPlanType = planTypeService.getById(fundPlan.getPlanType());
				fundPlan.setPlanType(fundPlanType.getPlanTypeName());
			}
		}
		
		String json = JSONObject.toJSONString(list);
		
		out.print(json);
		out.flush();
		out.close();
	}
	

}
