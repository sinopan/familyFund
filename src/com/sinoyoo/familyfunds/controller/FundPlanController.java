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
 * 基金计划controller
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
	 * 添加基金计划：注意ajax提交的表单参数都为String类型！！！
	 * 			 必须配置转换器或者手动将参数转换为正确的类型！！！
	 * 			 这里的时间配置了转换器所以可以使用，其他的参数必须是string类型
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
	 * 将分页查询结果放入PageBean，返回列表页面
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
	 * 获取已创建的、且开始和截至日期包含用户在页面选择交易时间的计划列表
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/existfundplan/{exchangeTime}")
	@ResponseBody
	public void getExistCorrectFundPlanTypes(@PathVariable("exchangeTime") Date exchangeTime,
							HttpServletResponse response) throws Exception{

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//key：基金计划id   value：计划类型+备注
		Map<String, String> fundPlanTypesMap = null;
		//获取包含交易时间的月份的起止日期
		String monthRange = DateUtils.getMonthRange(exchangeTime);
		String[] split = monthRange.split(",");
		
		FundPlanExample example = new FundPlanExample();
		Criteria criteria = example.createCriteria();
		criteria.andBeginTimeBetween(new Date(Long.parseLong(split[0])), new Date(Long.parseLong(split[1])));
		
		List<FundPlan> list = fundPlanService.getListByExample(example);
		if (list!=null && list.size()>0) {
			fundPlanTypesMap = new HashMap<String, String>();
			for (FundPlan fundPlan : list) {
				//获取类型名称显示
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
	 * 收支图表展示(跳转)
	 */
	@RequestMapping("/chart_display")
	public String showPage(){
		
		return "fund_chart_display";
	}

	/**
	 * 基金3D柱状图展示
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
		//计算开始基金计划的开始时间
		Date startTime = DateUtils.getQuarterStartTime(jidu);
		Date endTime = DateUtils.getQuarterEndTime(jidu);
		
		FundPlanExample example = new FundPlanExample();
		example.setOrderByClause("begin_time");
		Criteria criteria = example.createCriteria();
		criteria.andBeginTimeBetween(startTime, endTime);
		
		List<FundPlan> list = fundPlanService.getFundPlanListByExample(example);
		
		//将所属计划id转换为计划类型名称
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
