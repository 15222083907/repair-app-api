package com.toec.market.repair.beans;
import java.util.List;

/**
 * Bean类 - 分页
 */

public class Pager {

	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制
	
	// 排序方式（递增、递减）
	public enum Order {
		asc, desc
	}

	private Integer pageNum = 1;// 当前页码
	private Integer numPerPage = 20;// 每页记录数
	private String searchBy;// 查找字段
	private String keyword;// 查找关键字
	private String orderBy;// 排序字段
	private Order order;// 排序方式

	private int totalCount;// 总记录数
	private List<?> result;// 返回结果

	
	// 获取总页数
	public Integer getPageCount() {
		int pageCount = totalCount / numPerPage;
		if (totalCount % numPerPage > 0) {
			pageCount++;
		}
		return pageCount;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if (pageNum ==null || pageNum < 1) {
			pageNum = 1;
		}
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		if (numPerPage ==null || numPerPage < 1) {
			numPerPage = 1;
		} else if (numPerPage > MAX_PAGE_SIZE) {
			numPerPage = MAX_PAGE_SIZE;
		}
		this.numPerPage = numPerPage;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}
}