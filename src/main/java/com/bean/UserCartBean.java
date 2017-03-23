package com.bean;

public class UserCartBean{
	private Integer id;
	private String tel;
	private Integer productId;
	private Integer count;
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
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
