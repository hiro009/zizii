package jp.co.zizii.dto;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Player implements Serializable{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String userId;
	@Persistent
	private String userNickName;
	@Persistent
	private String name;
	@Persistent
	private int money;
	
	public Player(String userId, String userNickName, String name, int money){
		this.userId = userId;
		this.userNickName = userNickName;
		this.name = name;
		this.money = money;
	}
	
	public Key getKey(){
		return key;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNickName(String userNickName){
		return userNickName;
	}
	public void setUserNickName(String userNickName){
		this.userNickName = userNickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	

}
