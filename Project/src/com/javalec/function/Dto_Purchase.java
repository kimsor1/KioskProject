package com.javalec.function;

public class Dto_Purchase {

	
	
	int seqno;
	String name;
	int price;
	int size;
				
		
	public Dto_Purchase() {
					
	}


	public Dto_Purchase(int seqno, String name, int price, int size) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.price = price;
		this.size = size;
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
				
	
	
	
	
	
}
