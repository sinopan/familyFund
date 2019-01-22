package com.sinoyoo.familyfunds.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.service.FundPlanTypeService;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;

@Controller
@RequestMapping("/plantype")
public class PlanTypeController {

	@Autowired
	private FundPlanTypeService planTypeService;
	
	@RequestMapping("/wlist")
	@ResponseBody
	public void getAll(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			List<FundPlanType> list = planTypeService.getAll();
			if (list!=null && list.size()>0) {
				StringBuffer sb = new StringBuffer();
				for (int i=0; i<list.size(); i++) {
					FundPlanType fundPlanType = list.get(i);
					if (i==0 && i!=(list.size()-1)) {
						sb.append("{\""+fundPlanType.getId()+"\":"+"\""+fundPlanType.getPlanTypeName()+"\",");
					}else if (i==0 && i==(list.size()-1)) {
						sb.append("{\""+fundPlanType.getId()+"\":"+"\""+fundPlanType.getPlanTypeName()+"\"}");
					}else if (i!=0 && i!=(list.size()-1)) {
						sb.append("\""+fundPlanType.getId()+"\":"+"\""+fundPlanType.getPlanTypeName()+"\",");
					}else if (i!=0 && i==(list.size()-1)) {
						sb.append("\""+fundPlanType.getId()+"\":"+"\""+fundPlanType.getPlanTypeName()+"\"}");
					}
					
				}
				out.print(FastJsonUtils.BeanToJson(sb));
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
