package jp.co.zizii.dto;


public enum ZiziiPicture {
	ZIZII1("zizii1", 1, "/images/ossan1.jpg"),
	ZIZII2("zizii2",2, "/images/ossan2.jpg"),
	ZIZII3("zizii3",3, "/images/ossan3.jpg"),
	ZIZII4("zizii4",4, "/images/ossan4.jpg"),
	ZIZII5("zizii5",5, "/images/ossan5.jpg"),
	ZIZII6("zizii6",6, "/images/ossan6.jpg"),
	ZIZII7("zizii7",7, "/images/ossan7.jpg"),
	ZIZII8("zizii8",8, "/images/ossan8.jpg"),
	ZIZII9("zizii9",9, "/images/ossan9.jpg"),
	ZIZII10("zizii10",10, "/images/ossan10.jpg"),
	ZIZII11("zizii11",11, "/images/ossan11.jpg"),
	ZIZII12("zizii12",12, "/images/ossan12.jpg");
	
	//public static final int NUM_PICTURE = 12;
	
	private String ziziiName;
	private int ziziiId;
	private String ziziiPicture;
	
	private ZiziiPicture(String ziziiName, int ziziiId, String ziziiPicture){
		this.ziziiName = ziziiName;
		this.ziziiId = ziziiId;
		this.ziziiPicture = ziziiPicture;
	}
	public String toString(){
		return this.ziziiName;
	}
	public int toId(){
		return this.ziziiId;
	}
	
	public String toPicture(){
		return this.ziziiPicture;
	}
	

}
