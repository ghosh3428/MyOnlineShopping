package com.niit.MyOnlineBackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="MyOnline_Category")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@NotBlank(message="Category name cannot be left blank")
	String categoryName;
	
	@Size(min=10 , max=250, message="Description should be between 10 to 250 characters")
	String description;
	boolean active;
	
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getCategoryName()
	{
		return categoryName;
	}
	public void setCategoryName(String categoryName) 
	{
		this.categoryName = categoryName;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}

}
