package com.sinoyoo.familyfunds.utils;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * ���Ź�����
 * @author ASUS-LXP
 *
 */
public class SMSUtils {

	/**
	 * ���Ͷ���
	 * @param mobileNums �����ֻ����룬�����Ӣ�Ķ��Ÿ���
	 * @param content ��������
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String sendMessage(String mobileNums,String content) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		// ��ͷ�ļ�������ת��
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
		System.out.println(result);//��ӡ���ص�״̬
		
		post.releaseConnection();
		if (Integer.parseInt(result)>0) {
			return "�ѳɹ�����";
		}
		
		return getErrorMsg(Integer.parseInt(result));
	}
	
	/**
	 * ��ȡ������ʾ
	 * @param errorCode
	 * @return
	 */
	public static String getErrorMsg(int errorCode){
		if(errorCode==-1){
			return "û�и��û��˻�";
		}else if(errorCode==-2){
			return "�ӿ���Կ����ȷ";
		}else if(errorCode==-3){
			return "������������";
		}else if(errorCode==-4){
			return "�ֻ��Ÿ�ʽ����ȷ";
		}else if(errorCode==-21){
			return "MD5�ӿ���Կ���ܲ���ȷ";
		}else if(errorCode==-11){
			return "���û�������";
		}else if(errorCode==-14){
			return "�������ݳ��ַǷ��ַ�";
		}else if(errorCode==-41){
			return "�ֻ�����Ϊ��";
		}else if(errorCode==-42){
			return "��������Ϊ��";
		}else if(errorCode==-51){
			return "����ǩ����ʽ����ȷ";
		}else if(errorCode==-6){
			return "IP����";
		}else{
			return "δ֪������:"+errorCode;
		}
	}
}
