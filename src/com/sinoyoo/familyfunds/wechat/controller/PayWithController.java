package com.sinoyoo.familyfunds.wechat.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinoyoo.familyfunds.pojo.DepositeWithdraw;
import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.result.OperationResult;
import com.sinoyoo.familyfunds.service.DepositeWithdrawService;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;
import com.sinoyoo.familyfunds.utils.PageBean;

@Controller
@RequestMapping("/payWith")
public class PayWithController {

	@Autowired
	private DepositeWithdrawService dService;
	
	@RequestMapping("/wadd")
	@ResponseBody
	public <T> void addDepositeWithdraw(@RequestParam("exchangeAmount") Float exchangeAmount,
									@RequestParam("exchangeType") Integer exchangeType,
									@RequestParam("exchangeTime") Date exchangeTime,
									@RequestParam("exchangePlace") String exchangePlace,
									@RequestParam("exchangeDeatil") String exchangeDetail,
									@RequestParam("planType") String fundPlanId,
									HttpServletResponse response,HttpServletRequest request) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		DepositeWithdraw depositeWithdraw = new DepositeWithdraw();
		
		User user = (User) request.getSession().getAttribute("user");
		
		depositeWithdraw.setExchangeAmount(exchangeAmount);
		depositeWithdraw.setExchangeType(exchangeType);
		depositeWithdraw.setExchangeTime(exchangeTime);
		depositeWithdraw.setExchangePlace(exchangePlace);
		depositeWithdraw.setExchangeDeatil(new String(exchangeDetail.getBytes("iso-8859-1"),"UTF-8"));
		depositeWithdraw.setUserId(user.getId());
		depositeWithdraw.setFundPlanId(fundPlanId);
		
		try {
			DepositeWithdraw depositeWithdraw2 = dService.add(depositeWithdraw);
			if (depositeWithdraw2!=null) {
				OperationResult<DepositeWithdraw> result = new OperationResult<DepositeWithdraw>(200, true, depositeWithdraw2);
				out.print(FastJsonUtils.BeanToJson(result));
			}else {
				OperationResult<DepositeWithdraw> result = new OperationResult<DepositeWithdraw>(500, false, depositeWithdraw2);
				out.print(FastJsonUtils.BeanToJson(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
		
	}
	
	@RequestMapping("/wlist/{currPage}")
	@ResponseBody
	public void showList(@PathVariable("currPage") String currPage, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<DepositeWithdraw> pagebean = dService.selectByLimit(Integer.parseInt(currPage), 5);
		
		if (pagebean!=null) {
			List<DepositeWithdraw> list = pagebean.getList();
			if (list!=null && list.size()>0) {
				out.println(FastJsonUtils.ArrayToJson((ArrayList<DepositeWithdraw>) list));
			}else {
				out.print("");
			}
		}
		out.flush();
		out.close();
	}
	
//	@RequestMapping("/list_search")
//	@ResponseBody
//	public void searchPaymentWithdrawList(
//			@RequestParam("currentPage") String currentPage,
//			@RequestParam("exchangtype") String exchangtype,
//			@RequestParam("fundPlanType") String fundPlanType, 
//			@RequestParam("username") String username,
//			HttpServletResponse response, Model model) throws NumberFormatException, Exception{
//	
//		PageBean<DepositeWithdraw> pagebean = dService
//										.searchListByPage(Integer.parseInt(currentPage), 5, exchangtype,fundPlanType,username);
//		
//		model.addAttribute("pagebean", pagebean);
//		
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		JSONObject jsonObject = new JSONObject();
//		jsonObject = (JSONObject) JSONObject.toJSON(pagebean);
//		out.println(jsonObject.toJSONString());
//		
//		out.flush();
//		out.close();
//		
//	}
	
}
