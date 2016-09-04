/**
 * 
 */
package com.tomo.entity;

/**
 * @author YHX
 * @date： 日期：2014-3-5 时间：下午3:34:22
 */
public class Collect {
	private int collectionid;
	private int shopId;
	private String userName;
	public int getId() {
		return collectionid;
	}
	public void setId(int id) {
		this.collectionid = id;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "collection [id=" + collectionid + ", shopId=" + shopId + ", userName="
				+ userName + "]";
	}
	
}
