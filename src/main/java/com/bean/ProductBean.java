package com.bean;

public class ProductBean{
	private Integer id;
	private String name;
	private Integer categoryId;
	private String price;
	private String image;
	private String desc;
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
	public Integer getCategoryId(){
		return categoryId;
	}
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}
	public String getPrice(){
		return price;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getImage(){
		return image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
}
