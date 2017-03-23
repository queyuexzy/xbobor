package com.bean;

public class UserBean{
	private Integer id;
	private String name;
	private String tel;
	private String image;
	private Integer addressId;
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getImage(){
		return image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public Integer getAddressId(){
		return addressId;
	}
	public void setAddressId(Integer addressId){
		this.addressId = addressId;
	}
}
