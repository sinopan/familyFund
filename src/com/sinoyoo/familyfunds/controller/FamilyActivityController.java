package com.sinoyoo.familyfunds.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinoyoo.familyfunds.pojo.FamilyActivity;
import com.sinoyoo.familyfunds.result.OperationResult;
import com.sinoyoo.familyfunds.service.FamilyActivityService;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;
import com.sinoyoo.familyfunds.utils.PageBean;

@Controller
@RequestMapping("/activity")
public class FamilyActivityController {

	@Autowired
	private FamilyActivityService activityService;
	
	@RequestMapping("/add_page")
	public String addPage(){
		
		return "/activity_add";
	}
	
	/**
	 * 处理ajax提交表单（参数类型为string一定要注意！！）
	 * @param beginTime 已配置转换器
	 * @param endTime 已配置转换器
	 * @param content
	 * @param planer
	 * @param participants
	 * @param response
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void addActivity(@RequestParam("beginTime") Date beginTime,
							@RequestParam("endTime") Date endTime,
							@RequestParam("content") String content,
							@RequestParam("planer") String planer,
							@RequestParam("participants") String participants,
							HttpServletResponse response) throws IOException, ParseException{
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		FamilyActivity activity = new FamilyActivity();
		activity.setBeginTime(beginTime);
		activity.setEndTime(endTime);
		activity.setContent(content);
		activity.setPlaner(planer);
		activity.setParticipants(participants);
		
		FamilyActivity resultFamilyActivity = null;
		try {
			resultFamilyActivity = activityService.addFamilyActivity(activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resultFamilyActivity!=null) {
			out.println(FastJsonUtils.BeanToJson(new OperationResult<FamilyActivity>(200, true ,resultFamilyActivity)));
		} else {
			out.println(FastJsonUtils.BeanToJson(new OperationResult<FamilyActivity>(500, false ,resultFamilyActivity)));
		}
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/list/{currPage}")
	public String showActivityList(@PathVariable("currPage") String currPage, Model model) {
		
		PageBean<FamilyActivity> pagebean = null;
		try {
			pagebean = activityService.getPageBean(Integer.parseInt(currPage), 5);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagebean", pagebean);
		
		return "activity_list";
		
	}
	
}
