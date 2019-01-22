package com.sinoyoo.familyfunds.quartz;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sinoyoo.familyfunds.pojo.FamilyActivity;
import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.pojo.UserExample;
import com.sinoyoo.familyfunds.service.FamilyActivityService;
import com.sinoyoo.familyfunds.service.UserService;
import com.sinoyoo.familyfunds.utils.DateUtils;
import com.sinoyoo.familyfunds.utils.SMSUtils;

/**
 * 类名称：TimerTask 类描述：定时器任务
 */
@Component
public class SMSTimerTask {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FamilyActivityService activityService;
	

	/**
	 * 从数据库查询用户生日发送生日祝福
	 */
	@Scheduled(cron = "0 0 10 * * ?")
	public void sendBirthdayWishesTask() {
		//发送短信，由于调用第三方接口耗时较久，建议使用线程。
		Runnable runnable = new Runnable() {
			public void run() {
				List<User> userList = null;
				try {
					userList = userService.selectByExample(new UserExample());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//定义需要发送短信的用户和内容
				Map<String, String> map = new HashMap<String, String>();
				Map<User, Integer> map2 = new HashMap<User, Integer>();
				
				if (userList!=null && userList.size()>0) {
					Date now = new Date();
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(now);
					for (User user : userList) {//遍历用户生日，计算和当前日期相差天数
						Date bithday = user.getBithday();
						Calendar cal2 = Calendar.getInstance();
						cal2.setTime(bithday);
						cal1.set(Calendar.YEAR, cal2.get(Calendar.YEAR));
						
						now = cal1.getTime();
						
						int diiferDays = DateUtils.differentDays(bithday, now);
						if (diiferDays>=0 && diiferDays<=3) {
							if (diiferDays==3) {
								map.put(user.getMobile(), "亲，别忘了再过3天就是你的生日哟！感谢您的支持，在这里提前祝您生日快乐！");
								map2.put(user, 3);
							} else if(diiferDays==2) {
								map.put(user.getMobile(), "亲，别忘了后天就是你的生日哟！感谢您的支持，在这里提前祝您生日快乐！");
								map2.put(user, 2);
							} else if(diiferDays==1) {
								map.put(user.getMobile(), "亲，眼看明天就是你的生日哟，您由什么计划和惊喜吗！感谢您的支持，在这里提前祝您生日快乐！");
								map2.put(user, 1);
							} else if(diiferDays==0) {
								map.put(user.getMobile(), "亲，期待已久的生日重要到啦！赶快找您的朋友收取生日礼物哟！感谢您的支持，祝您生日快乐！");
								map2.put(user, 0);
							}
						}
					}
					//给所有用户发消息提示给寿星送去祝福
					if (map2!=null && map2.size()>0) {
						if (userList!=null && userList.size()>0) {
							for (User user : userList) {
								Iterator<User> iterator = map2.keySet().iterator();
								while (iterator.hasNext()) {
									User user2 = iterator.next();
									Integer diiferDays2 = map2.get(user2);
									
									if (diiferDays2==3) {
										map.put(user.getMobile(), "亲，再过3天就是"+ user2.getName() +"的生日哟！");
									} else if(diiferDays2==2) {
										map.put(user.getMobile(), "亲，后天就是"+ user2.getName() +"的生日哟！赶快去说生日快乐把！");
									} else if(diiferDays2==1) {
										map.put(user.getMobile(), "亲，明天就是"+ user2.getName() +"的生日哟，您有怎么给好友庆生呢！");
									} else if(diiferDays2==0) {
										map.put(user.getMobile(), "亲，今天是"+ user2.getName() +"的生日！赶快送去祝福吧！");
									}
								}
							}
						}
					}
				}
				//发送短息
				if (map!=null && map.size()>0) {
					Iterator<String> iterator = map.keySet().iterator();
					while (iterator.hasNext()) {
						String mobile = iterator.next();
						String content = map.get(mobile);
						try {
							String resultMessage = SMSUtils.sendMessage(mobile, content);
						} catch (HttpException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		new Thread(runnable).start();
	}

	/**
	 * 从数据库查询活动发送活动提醒
	 */
	@Scheduled(cron = "0 0 9,14 * * ?")
	public void sendActivityRemindTask() {
		//发送短信，由于调用第三方接口耗时较久，建议使用线程。
		Runnable runnable = new Runnable() {
			public void run() {
				List<FamilyActivity> familyActivities = null;
				try {
					familyActivities = activityService.getAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//定义需要发送短信的用户和内容
				Map<String, String> map = new HashMap<String, String>();
				if (familyActivities!=null && familyActivities.size()>0) {
					Date now = new Date();
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(now);
					for (FamilyActivity activity : familyActivities) {//遍历用户生日，计算和当前日期相差天数
						Date bithday = activity.getBeginTime();
						Calendar cal2 = Calendar.getInstance();
						cal2.setTime(bithday);
						cal1.set(Calendar.YEAR, cal2.get(Calendar.YEAR));
						
						now = cal1.getTime();
						
						int diiferDays = DateUtils.differentDays(bithday, now);
						//获取参与人员的联系方式
						String participantsIdsStr = activity.getParticipants();
						String[] userIds = participantsIdsStr.split(",");
						String[] mobiles = new String[userIds.length];
						
						if (userIds!=null && userIds.length > 0) {
							int i = 0;
							for (String userId : userIds) {
								User user = null;
								try {
									user = userService.getUserByPrimaryKey(userId);
								} catch (Exception e) {
									e.printStackTrace();
								}
								mobiles[i] = user.getMobile();
								
								i++;//变量
							}
						}
						
						if (diiferDays>=0) {
							if (diiferDays==1) {
								if (mobiles!=null && mobiles.length>0) {
									for (String mobile : mobiles) {
										map.put(mobile, "温馨提示：明天您有如下活动！"+activity.getContent());
									}
								}
							} else if(diiferDays==0) {
								for (String mobile : mobiles) {
									map.put(mobile, "温馨提示：记得今天的活动哟！"+activity.getContent());
								}
							}
						}
					}
				}
				//发送短息
				if (map!=null && map.size()>0) {
					Iterator<String> iterator = map.keySet().iterator();
					while (iterator.hasNext()) {
						String mobile = iterator.next();
						String content = map.get(mobile);
						try {
							String resultMessage = SMSUtils.sendMessage(mobile, content);
						} catch (HttpException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		new Thread(runnable).start();
	}

}