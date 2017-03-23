package com.bean;

public class OrderDetailsBean{
	private Integer id;
	private Integer productId;
	private Integer activityDetailsId;
	private String tel;
	private Integer count;
	
	public Integer getActivityDetailsId(){
		return activityDetailsId;
	}
	public void setActivityDetailsId(Integer activityDetailsId){
		this.activityDetailsId = activityDetailsId;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getProductId(){
		return productId;
	}
	public void setProductId(Integer productId){
		this.productId = productId;
	}
	public Integer getCount(){
		return count;
	}
	public void setCount(Integer count){
		this.count = count;
	}
}
