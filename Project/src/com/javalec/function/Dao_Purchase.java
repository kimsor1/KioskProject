package com.javalec.function;



public class Dao_Purchase {

	
	int seqno;
	String name;
	int price;
	int size;
				
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pass_mysql = ShareVar.dbPass;
	
	
	
	public Dao_Purchase() {
		
	}



	public Dao_Purchase(int seqno, String name, int price, int size) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.price = price;
		this.size = size;
	}
	
	
	
	
							
	
	
	
	
	
	
}
