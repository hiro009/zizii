package jp.co.zizii.factory;

import jp.co.zizii.dto.ZiziiDto;
import jp.co.zizii.dto.ZiziiPicture;

public class ZiziiFactory {
	private static final int MAX_HP = 20;
	private static final int MAX_ATTACK = 13;
	private static final int MAX_DEFFENCE = 13;
	private static final int MAX_QUEICK = 13;
	private static final int MAX_SP_ATTACK = 13;
	private static final int MAX_SP_DEFFENCE = 13;
	private static final int MAX_LUCK = 13;
	
	private static final int NUM_PICTURE = 12;
	
	public static ZiziiDto newInstance(String userId, String name){

		int faceNum= (int)(Math.random()* (NUM_PICTURE-1));
		System.out.println(ZiziiPicture.ZIZII1);
		//System.out.println(ZiziiPicture.ZIZII1);
		String picture = ZiziiPicture.values()[faceNum].toPicture();
		int hp = (int)(Math.random()*MAX_HP);
		int attack = (int)(Math.random()*MAX_ATTACK);
		int deffence = (int)(Math.random()*MAX_DEFFENCE);
		int spAttack = (int)(Math.random()*MAX_SP_ATTACK);
		int spDeffence = (int)(Math.random()*MAX_SP_DEFFENCE);
		int quick = (int)(Math.random()*MAX_QUEICK);
		int luck = (int)(Math.random()*MAX_LUCK);
		int money = 0;
		int location = 0;
	
		return new ZiziiDto(userId, name, picture, hp, attack, deffence, spAttack,
				spDeffence, quick, luck, money,location);
	}

}
