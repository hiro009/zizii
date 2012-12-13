package jp.co.zizii.factory;

import com.google.appengine.api.users.User;

import jp.co.zizii.dto.Player;

public class PlayerFactory {
	
	
//	public static Player getInstance(){
//		return new Player();
//	}
	public static Player newInstance(String userId, String userNickName, String name){
		int money = 0;
		return new Player(userId, userNickName, name, money);
	}

}
