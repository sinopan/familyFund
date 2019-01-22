package com.sinoyoo.familyfunds.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinoyoo.familyfunds.pojo.FundPlan;
import com.sinoyoo.familyfunds.result.OperationResult;
import com.sinoyoo.familyfunds.service.FundPlanService;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;
import com.sinoyoo.familyfunds.utils.PageBean;

@Controller
@RequestMapping("/fund")
public class FundController {

	@Autowired
	private FundPlanService fundPlanService;
	
	@RequestMapping("/wadd")
	@ResponseBody
	public void addFundPlan(@RequestParam("amountGoal") String amountGoal,
							@RequestParam("realizeGoal") String realizeGoal,
							@RequestParam("beginTime") String beginTime,
							@RequestParam("endTime") String endTime,
							@RequestParam("planType") String planType,
							@RequestParam("planState") String planState,
							@RequestParam("note") String note,
							HttpServletResponse response) throws Exception{

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		FundPlan fundPlan = new FundPlan();
		fundPlan.setAmountGoal(Float.parseFloat(amountGoal));
		fundPlan.setRealizeGoal(Float.parseFloat(realizeGoal));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		fundPlan.setBeginTime(sdf.parse(beginTime));
		fundPlan.setEndTime(sdf.parse(endTime));
		fundPlan.setPlanType(planType);
		fundPlan.setPlanState(Integer.parseInt(planState));
		fundPlan.setNote(new String(note.getBytes("iso-8859-1"),"UTF-8"));
		
		FundPlan addFundPlan = fundPlanService.addFundPlan(fundPlan);
		if (addFundPlan != null) {
			OperationResult<FundPlan> result = new OperationResult<FundPlan>(200, true, addFundPlan);
			out.print(FastJsonUtils.BeanToJson(result));
		}else {
			OperationResult<FundPlan> result = new OperationResult<FundPlan>(500, false, null);
			out.write(FastJsonUtils.BeanToJson(result));
		}
			
		out.flush();
		out.close();
	}
	
	/**
	 * 小程序接口：基金计划列表展示
	 * @param currPage
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/wlist/{currPage}")
	@ResponseBody
	public void showAllRecords(@PathVariable("currPage") Integer currPage, 
			HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();

			PageBean<FundPlan> pagebean = null;
			pagebean = fundPlanService.getPageBean(currPage, 15);
			out.println(FastJsonUtils.ArrayToJson((ArrayList<FundPlan>) pagebean.getList()));
			System.out.println(pagebean.getList());
		} catch (Exception e) {
			//out.println("");
		}
		
		out.flush();
		out.close();
		
	}
	
}
