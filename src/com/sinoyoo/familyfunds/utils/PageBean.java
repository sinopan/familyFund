package com.sinoyoo.familyfunds.utils;

import java.util.List;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2018年1月18日 下午10:34:00 
 * @version 1.0 
 * @param <T>
 * @description  分页实体类
 * @return  
 */
public class PageBean<T> {
	private List<T> list;//当前页内容	 	查询
	private int currPage;//当前页码 	 	传递
	private int pageSize;//每页显示的条数	固定
	private int totalCount;//总条数		查询
	private int totalPage;//总页数		计算
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
