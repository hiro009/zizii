package jp.co.zizii.dto;

public enum Dungeon {
	FIRST_FOREST("初心者の森",1, 4),
	COMBINI("コンビニ", 1, 5),
	PUBLIC_HOLL("公民館", 2, 6),
	BONESETTER("接骨院", 2, 7),
	IONE("イオン", 3, 7),
	INSURANCE("保険会社", 4, 7),
	AMWEY("ア〇ムウェイ", 5, 7);
	
	private String dundeonName;
	private int	level;
	private int floor;
	
	private Dungeon(String dundeonName, int level, int floor){
		this.dundeonName = dundeonName;
		this.level = level;
		this.floor = floor;
	}
	
	public String toString(){
		return this.dundeonName;
	}
	
	public int toLevel(){
		return this.level;
	}
	
	public int toFloor(){
		return this.floor;
	}
	
	
	

}
