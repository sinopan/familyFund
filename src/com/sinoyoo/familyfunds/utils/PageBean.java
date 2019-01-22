package com.sinoyoo.familyfunds.utils;

import java.util.List;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2018��1��18�� ����10:34:00 
 * @version 1.0 
 * @param <T>
 * @description  ��ҳʵ����
 * @return  
 */
public class PageBean<T> {
	private List<T> list;//��ǰҳ����	 	��ѯ
	private int currPage;//��ǰҳ�� 	 	����
	private int pageSize;//ÿҳ��ʾ������	�̶�
	private int totalCount;//������		��ѯ
	private int totalPage;//��ҳ��		����
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "PageBean [list=" + list + ", currPage=" + currPage + ", pageSize=" + pageSize + ", totalCount="
				+ totalCount + ", totalPage=" + totalPage + "]";
	}
	
}
