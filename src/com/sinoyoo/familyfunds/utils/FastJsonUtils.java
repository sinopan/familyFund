package com.sinoyoo.familyfunds.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJsonUtils {
	/*** * ����Ϊ�ַ��� * * @param jsonString * @param key * @return */
	public static String fromString(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getString(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*** * ����Ϊ���� * * @param jsonString * @param key * @return */
	public static Boolean formBoolean(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getBoolean(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*** * ����ΪString * * @param jsonString * @param key * @return */
	public static String formString(String jsonString, String key, String skey) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				JSONObject jsonObject1 = jsonObject.getJSONObject(key);
				String jsonObject2 = jsonObject1.getString(skey);
				return jsonObject2;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*** * ����ΪInt * * @param jsonString * @param key * @return */
	public static int formInt(String jsonString, String key, String skey) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				JSONObject jsonObject1 = jsonObject.getJSONObject(key);
				int jsonObject2 = jsonObject1.getInteger(skey);
				return jsonObject2;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/*** * ����Ϊ���� * * @param jsonString * @param key * @return */
	public static Integer formInteger(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getInteger(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*** * ����Ϊ��λʮ������ * * @param jsonString * @param key * @return */
	public static BigDecimal formBigDecimal(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getBigDecimal(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*** * ����Ϊ˫���� * * @param jsonString * @param key * @return */
	public static Double formDouble(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getDouble(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*** * ����Ϊ������ * * @param jsonString * @param key * @return */
	public static Float formFloat(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getFloat(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * * ����Ϊ���� * * @param jsonString * @param key * @param t * @param
	 * <T> * @return
	 */
	public static <T> T fromBean(String jsonString, String key, Class<T> t) {
		if (jsonString != null) {
			try {
				JSONObject jsonObj = JSONObject.parseObject(jsonString);
				return JSONObject.toJavaObject(jsonObj.getJSONObject(key), t);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/***
	 * * ����Ϊ�б� * * @param jsonString * @param key * @param t * @param
	 * <T> * @return
	 */
	public static <T> ArrayList<T> fromList(String jsonString, String key, Class<T> t) {
		ArrayList<T> list = new ArrayList<T>();
		if (jsonString != null && jsonString.length() > 0) {
			try {
				JSONObject jsonObj = JSONObject.parseObject(jsonString);
				JSONArray inforArray = jsonObj.getJSONArray(key);
				for (int index = 0; index < inforArray.size(); index++) {
					list.add(JSONObject.toJavaObject(inforArray.getJSONObject(index), t));
				}
			} catch (Exception e) {
			}
		}
		return list;
	}

	/*** * ֱ�ӽ���Ϊbean * * @param jsonString * @param t * @param <T> * @return */
	public static <T> T fromBean(String jsonString, Class<T> t) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				return JSON.parseObject(jsonString, t);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*** * ֱ�ӽ���Ϊlist * * @param jsonString * @param t * @param <T> * @return */
	public static <T> ArrayList<T> fromList(String jsonString, Class<T> t) {
		ArrayList<T> list = null;
		try {
			list = new ArrayList<T>();
			if (jsonString != null && jsonString.length() > 0) {
				list.addAll(JSON.parseArray(jsonString, t));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*** * ���б�ת��Ϊjson * * @param listBean * @return */
	public static <T> String ArrayToJson(ArrayList<T> listBean) {
		String jsonString = JSON.toJSONString(listBean);
		return jsonString;
	}

	/*** * ����תΪjson * * @param <T> * @return */
	public static <T> String BeanToJson(Object obj) {
		String jsonsString = JSON.toJSONString(obj);
		return jsonsString;
	}

}
