package com.sinoyoo.familyfunds.utils;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 短信工具类
 * @author ASUS-LXP
 *
 */
public class SMSUtils {

	/**
	 * 发送短信
	 * @param mobileNums 接受手机号码，多个以英文逗号隔开
	 * @param content 短信内容
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String sendMessage(String mobileNums,String content) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		// 在头文件中设置转码
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
		NameValuePair[] data = { 
				new NameValuePair("Uid", "tdcq101625"),
				new NameValuePair("Key", "d41d8cd98f00b204e980"),
				new NameValuePair("smsMob", mobileNums),
				new NameValuePair("smsText", content)
			};
		post.setRequestBody(data);
		client.executeMethod(post);
		Header[] headers = post.getRequestHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result);//打印返回的状态
		
		post.releaseConnection();
		if (Integer.parseInt(result)>0) {
			return "已成功发送";
		}
		
		return getErrorMsg(Integer.parseInt(result));
	}
	
	/**
	 * 获取错误提示
	 * @param errorCode
	 * @return
	 */
	public static String getErrorMsg(int errorCode){
		if(errorCode==-1){
			return "没有该用户账户";
		}else if(errorCode==-2){
			return "接口密钥不正确";
		}else if(errorCode==-3){
			return "短信数量不足";
		}else if(errorCode==-4){
			return "手机号格式不正确";
		}else if(errorCode==-21){
			return "MD5接口密钥加密不正确";
		}else if(errorCode==-11){
			return "该用户被禁用";
		}else if(errorCode==-14){
			return "短信内容出现非法字符";
		}else if(errorCode==-41){
			return "手机号码为空";
		}else if(errorCode==-42){
			return "短信内容为空";
		}else if(errorCode==-51){
			return "短信签名格式不正确";
		}else if(errorCode==-6){
			return "IP限制";
		}else{
			return "未知错误码:"+errorCode;
		}
	}
}
