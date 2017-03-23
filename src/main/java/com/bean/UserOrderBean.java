package com.bean;

public class UserOrderBean{
	private Integer id;
	private String updateTime;
	private String tel;
	private Integer status;
	private String money;
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public String getMoney(){
		return money;
	}
	public void setMoney(String money){
		this.money = money;
	}
}
