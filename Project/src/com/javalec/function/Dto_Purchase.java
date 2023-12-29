package com.javalec.function;

public class Dto_Purchase {

	
	
	
     private int pro_id;
	int sales_price;
	String color;
	int size;
	int quantity;
	String name;
	
	
				
		
	

	//pro_id, quantity, size, sales_price, color
	public Dto_Purchase(int pro_id, int sales_price, String color,  int size, int quantity) {
		super();
		
		this.pro_id = pro_id;
		this.sales_price = sales_price;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
	}
	
	public Dto_Purchase(int pro_id, String name, int size, String color, int price, int quantity) {
		this.pro_id = pro_id;
		this.name = name;
		this.size = size;
		this.color = color;
		this.sales_price = price;
		this.quantity = quantity;
		
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public int getSales_price() {
		return sales_price;
	}

	public void setSales_price(int sales_price) {
		this.sales_price = sales_price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}