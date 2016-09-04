package com.tomo.entity.common;

import java.io.Serializable;
import java.util.List;

/**
 *分页模型类
 * @author yhx
 * @param <T>
 */
public class PageModel<T> implements Serializable {
	private static final long serialVersionUID = -5259112447890337702L;
	
	/**
	 * 分页大小
	 */
	private int pageSize = 10;
	/**
	 * 分页序号
	 */
	private int pageNo = 1;
	/**
	 * 记录数量
	 */
	private long recordCount;
	/**
	 * 分页数量
	 */
	private int pageCount;
	/**
	 * 数据列表
	 */
	private List<T> data;
	
	public PageModel() {
	}

	public PageModel(int pageSize, int pageNo) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		pageCount = (int)((recordCount + pageSize - 1) / pageSize);
		
		return pageCount;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

//PageModel [pageSize=pageSize,pageNO=pageNo,recordCount=recordCount,pageCount=pageCount,data=dat]
	@Override
	public String toString() {
		return "PageModel [pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", recordCount=" + recordCount + ", pageCount=" + pageCount
				+ ", data=" + data + "]";
	}
}
