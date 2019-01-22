package com.sinoyoo.familyfunds.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sinoyoo.familyfunds.pojo.DepositeWithdraw;
import com.sinoyoo.familyfunds.pojo.DepositeWithdrawExample;
import com.sinoyoo.familyfunds.pojo.FundPlan;
import com.sinoyoo.familyfunds.pojo.FundPlanExample;
import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.pojo.UserExample;
import com.sinoyoo.familyfunds.result.OperationResult;
import com.sinoyoo.familyfunds.service.DepositeWithdrawService;
import com.sinoyoo.familyfunds.service.FundPlanService;
import com.sinoyoo.familyfunds.service.FundPlanTypeService;
import com.sinoyoo.familyfunds.service.UserService;
import com.sinoyoo.familyfunds.utils.ExcelUtil;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;
import com.sinoyoo.familyfunds.utils.PageBean;
import com.sinoyoo.familyfunds.vo.DepositeWithdrawChartVO;

@Controller
@RequestMapping("/paymentWithdraw")
public class DepositeWithdrawController {

	@Autowired
	private DepositeWithdrawService dService;
	
	@Autowired
	private FundPlanService fundPlanService;
	
	@Autowired
	private FundPlanTypeService fundPlanTypeService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/add_page")
	public String jumpToAddPage(){
		return "/payment_withdraw_add";
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public <T> void addDepositeWithdraw(@RequestParam("exchangeAmount") Float exchangeAmount,
									@RequestParam("exchangeType") Integer exchangeType,
									@RequestParam("exchangeTime") Date exchangeTime,
									@RequestParam("exchangePlace") String exchangePlace,
									@RequestParam("exchangeDeatil") String exchangeDetail,
									@RequestParam("userId") String userId,
									@RequestParam("fundPlanId") String fundPlanId,
									HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		DepositeWithdraw depositeWithdraw = new DepositeWithdraw();
		
		depositeWithdraw.setExchangeAmount(exchangeAmount);
		depositeWithdraw.setExchangeType(exchangeType);
		depositeWithdraw.setExchangeTime(exchangeTime);
		depositeWithdraw.setExchangePlace(exchangePlace);
		depositeWithdraw.setExchangeDeatil(exchangeDetail);
		depositeWithdraw.setUserId(userId);
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
	
	@RequestMapping("/list/{currPage}")
	public String showList(@PathVariable("currPage") String currPage, Model model){
		
		PageBean<DepositeWithdraw> pagebean = null;
		try {
			pagebean = dService.selectByLimit(Integer.parseInt(currPage), 5);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagebean", pagebean);
		
		return "payment_withdraw_list";
		
	}
	
	@RequestMapping("/list_search")
	@ResponseBody
	public void searchPaymentWithdrawList(
			@RequestParam("currentPage") String currentPage,
			@RequestParam("exchangtype") String exchangtype,
			@RequestParam("fundPlanType") String fundPlanType, 
			@RequestParam("username") String username,
			HttpServletResponse response, Model model) throws NumberFormatException, Exception{
	
		PageBean<DepositeWithdraw> pagebean = dService
										.searchListByPage(Integer.parseInt(currentPage), 5, exchangtype,fundPlanType,username);
		
		model.addAttribute("pagebean", pagebean);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject = (JSONObject) JSONObject.toJSON(pagebean);
		out.println(jsonObject.toJSONString());
		
		out.flush();
		out.close();
		
	}

	/**
	 * 导出报表
	 * @return
	 */
	@RequestMapping(value = "/export/{exchangeType}/{fundPlanType}/{username}")
	@ResponseBody
	public void export(
				@PathVariable("exchangeType") String exchangetype,
				@PathVariable("fundPlanType") String fundPlanType, 
				@PathVariable("username") String username,
				HttpServletResponse response) throws Exception {
		//导出是get请求
		exchangetype = new String(exchangetype.getBytes("iso8859-1"),"utf-8");
		fundPlanType = new String(fundPlanType.getBytes("iso8859-1"),"utf-8");
		username = new String(username.getBytes("iso8859-1"),"utf-8");
		
		//获取数据
		DepositeWithdrawExample example = new DepositeWithdrawExample();
		com.sinoyoo.familyfunds.pojo.DepositeWithdrawExample.Criteria criteria1 = example.createCriteria();
		//确认查询条件
		if (exchangetype!=null && !"".equals(exchangetype) && !"all".equalsIgnoreCase(exchangetype)) {
			criteria1.andExchangeTypeEqualTo(Integer.parseInt(exchangetype));
		}
		if (fundPlanType!=null && !"".equals(fundPlanType) && !"all".equalsIgnoreCase(fundPlanType)) {
			List<String> fundPlanIds = null;
			
			FundPlanExample fundPlanExample = new FundPlanExample();
			com.sinoyoo.familyfunds.pojo.FundPlanExample.Criteria criteria = fundPlanExample.createCriteria();
			criteria.andPlanTypeEqualTo(fundPlanType);
			List<FundPlan> fundPlanList = fundPlanService.getListByExample(fundPlanExample);
			if (fundPlanList!=null && fundPlanList.size()>0) {
				fundPlanIds = new ArrayList<String>();
				for (FundPlan fundPlan : fundPlanList) {
					fundPlanIds.add(fundPlan.getId());
				}
				criteria1.andFundPlanIdIn(fundPlanIds);
			}
		}
		if(!"".equals(username) && username != null && !"all".equalsIgnoreCase(username)){
			List<User> userList = userService.selectByExample(new UserExample());
			String userId = "";
			if (userList!=null && userList.size()>0) {
				for (User user : userList) {
					if (username.equalsIgnoreCase(user.getName())) {
						userId = user.getId();
					}
				}
			}
			criteria1.andUserIdEqualTo(userId);
		}
		
		
		List<DepositeWithdraw> list = dService.getByExample(example);

		//excel标题
		String[] title = new String[]{"交易人","交易类型","交易金额","交易地点","交易时间","所属基金计划","备注"};
		System.out.println(title.length);
		//excel文件名
		String fileName = "交易记录列表"+System.currentTimeMillis()+".xls";

		//sheet名
		String sheetName = "交易记录列表";
		String[][] content = new String[list.size()][title.length];
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//"交易人","交易类型","交易金额","交易地点","交易时间","所属基金计划","备注"
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[title.length];
			DepositeWithdraw depositeWithdraw = list.get(i);
			User user = userService.getUserByPrimaryKey(depositeWithdraw.getUserId());
			content[i][0] = user.getName();
			String exchangeType = "";
			if (depositeWithdraw.getExchangeType() == 0) {
				exchangeType = "支出";
			} else if (depositeWithdraw.getExchangeType() == 1) {
				exchangeType = "存入";
			} 
			content[i][1] = exchangeType;
			content[i][2] = depositeWithdraw.getExchangeAmount().toString();
			content[i][3] = depositeWithdraw.getExchangePlace();
			content[i][4] = sdf.format(depositeWithdraw.getExchangeTime());
			String fundPlanName = "";//暂时硬编码
			FundPlan fundPlan = fundPlanService.getFundPlanById(depositeWithdraw.getFundPlanId());
			String planType = fundPlan.getPlanType();
			
			FundPlanType fundPlanType2 = fundPlanTypeService.getById(planType);
			content[i][5] = fundPlanType2.getPlanTypeName();
			content[i][6] = depositeWithdraw.getExchangeDeatil();

		}

		//创建HSSFWorkbook 
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

		//响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 收支图表展示(跳转)
	 */
	@RequestMapping("/chart_display")
	public String showPage(){
		
		return "shouzhi_chart_display";
	}

	/**
	 * 收支图表展示（ajax请求）
	 * @throws Exception 
	 */
	@RequestMapping("/exchange_chart_show")
	@ResponseBody
	public void getExchangeChartsList(@RequestParam("exchangeType") Integer exchangeType, 
			@RequestParam("conditionType") String conditiontype, @RequestParam("conditionValue") String conditionValue, 
				HttpServletResponse response) throws Exception{
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		List<DepositeWithdrawChartVO> chartVOs = dService.getChartsVO(exchangeType, conditiontype, conditionValue);
		//修改chartsVO中的实体的consumationType
		if (chartVOs != null) {
			for (DepositeWithdrawChartVO chartVO : chartVOs) {
				String funPlanId = chartVO.getConsumationType();
				FundPlan fundPlan = fundPlanService.getFundPlanById(funPlanId);
				
				FundPlanType fundPlanType = fundPlanTypeService.getById(fundPlan.getPlanType());
				
				chartVO.setConsumationType(fundPlanType.getPlanTypeName());
			}
		}
		ArrayList<DepositeWithdrawChartVO> listBean = new ArrayList<DepositeWithdrawChartVO>();
		if (chartVOs != null) {
			for (DepositeWithdrawChartVO vo : chartVOs) {
				listBean.add(vo);
			}
		}
		String json = FastJsonUtils.ArrayToJson(listBean );
		
		out.print(json);
		out.flush();
		out.close();
	}
	
}
