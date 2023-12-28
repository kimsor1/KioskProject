package com.javalec.function;

public class Dto_Product {
	
	int seqno;
	String name;
	int price;
	int size;
	String color;
	int stock;
	
	public Dto_Product(){
		
	}
	
	public Dto_Product(int seqno, String name, int price) {
		this.seqno = seqno;
		this.name = name;
		this.price = price;
	}

	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	

}
