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
 * �����ƣ�TimerTask ����������ʱ������
 */
@Component
public class SMSTimerTask {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FamilyActivityService activityService;
	

	/**
	 * �����ݿ��ѯ�û����շ�������ף��
	 */
	@Scheduled(cron = "0 0 10 * * ?")
	public void sendBirthdayWishesTask() {
		//���Ͷ��ţ����ڵ��õ������ӿں�ʱ�Ͼã�����ʹ���̡߳�
		Runnable runnable = new Runnable() {
			public void run() {
				List<User> userList = null;
				try {
					userList = userService.selectByExample(new UserExample());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//������Ҫ���Ͷ��ŵ��û�������
				Map<String, String> map = new HashMap<String, String>();
				Map<User, Integer> map2 = new HashMap<User, Integer>();
				
				if (userList!=null && userList.size()>0) {
					Date now = new Date();
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(now);
					for (User user : userList) {//�����û����գ�����͵�ǰ�����������
						Date bithday = user.getBithday();
						Calendar cal2 = Calendar.getInstance();
						cal2.setTime(bithday);
						cal1.set(Calendar.YEAR, cal2.get(Calendar.YEAR));
						
						now = cal1.getTime();
						
						int diiferDays = DateUtils.differentDays(bithday, now);
						if (diiferDays>=0 && diiferDays<=3) {
							if (diiferDays==3) {
								map.put(user.getMobile(), "�ף��������ٹ�3������������Ӵ����л����֧�֣���������ǰף�����տ��֣�");
								map2.put(user, 3);
							} else if(diiferDays==2) {
								map.put(user.getMobile(), "�ף������˺�������������Ӵ����л����֧�֣���������ǰף�����տ��֣�");
								map2.put(user, 2);
							} else if(diiferDays==1) {
								map.put(user.getMobile(), "�ף��ۿ���������������Ӵ������ʲô�ƻ��;�ϲ�𣡸�л����֧�֣���������ǰף�����տ��֣�");
								map2.put(user, 1);
							} else if(diiferDays==0) {
								map.put(user.getMobile(), "�ף��ڴ��Ѿõ�������Ҫ�������Ͽ�������������ȡ��������Ӵ����л����֧�֣�ף�����տ��֣�");
								map2.put(user, 0);
							}
						}
					}
					//�������û�����Ϣ��ʾ��������ȥף��
					if (map2!=null && map2.size()>0) {
						if (userList!=null && userList.size()>0) {
							for (User user : userList) {
								Iterator<User> iterator = map2.keySet().iterator();
								while (iterator.hasNext()) {
									User user2 = iterator.next();
									Integer diiferDays2 = map2.get(user2);
									
									if (diiferDays2==3) {
										map.put(user.getMobile(), "�ף��ٹ�3�����"+ user2.getName() +"������Ӵ��");
									} else if(diiferDays2==2) {
										map.put(user.getMobile(), "�ף��������"+ user2.getName() +"������Ӵ���Ͽ�ȥ˵���տ��ְѣ�");
									} else if(diiferDays2==1) {
										map.put(user.getMobile(), "�ף��������"+ user2.getName() +"������Ӵ��������ô�����������أ�");
									} else if(diiferDays2==0) {
										map.put(user.getMobile(), "�ף�������"+ user2.getName() +"�����գ��Ͽ���ȥף���ɣ�");
									}
								}
							}
						}
					}
				}
				//���Ͷ�Ϣ
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
	 * �����ݿ��ѯ����ͻ����
	 */
	@Scheduled(cron = "0 0 9,14 * * ?")
	public void sendActivityRemindTask() {
		//���Ͷ��ţ����ڵ��õ������ӿں�ʱ�Ͼã�����ʹ���̡߳�
		Runnable runnable = new Runnable() {
			public void run() {
				List<FamilyActivity> familyActivities = null;
				try {
					familyActivities = activityService.getAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//������Ҫ���Ͷ��ŵ��û�������
				Map<String, String> map = new HashMap<String, String>();
				if (familyActivities!=null && familyActivities.size()>0) {
					Date now = new Date();
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(now);
					for (FamilyActivity activity : familyActivities) {//�����û����գ�����͵�ǰ�����������
						Date bithday = activity.getBeginTime();
						Calendar cal2 = Calendar.getInstance();
						cal2.setTime(bithday);
						cal1.set(Calendar.YEAR, cal2.get(Calendar.YEAR));
						
						now = cal1.getTime();
						
						int diiferDays = DateUtils.differentDays(bithday, now);
						//��ȡ������Ա����ϵ��ʽ
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
								
								i++;//����
							}
						}
						
						if (diiferDays>=0) {
							if (diiferDays==1) {
								if (mobiles!=null && mobiles.length>0) {
									for (String mobile : mobiles) {
										map.put(mobile, "��ܰ��ʾ�������������»��"+activity.getContent());
									}
								}
							} else if(diiferDays==0) {
								for (String mobile : mobiles) {
									map.put(mobile, "��ܰ��ʾ���ǵý���ĻӴ��"+activity.getContent());
								}
							}
						}
					}
				}
				//���Ͷ�Ϣ
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