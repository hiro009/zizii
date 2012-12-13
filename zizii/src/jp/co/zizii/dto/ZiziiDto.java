package jp.co.zizii.dto;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class ZiziiDto implements Serializable{
	
	//必須パラメータ
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String userId;
	
	@Persistent 
	private String name;
	
	@Persistent
	private String picture;	

	//オプショナルパラメータ
	@Persistent
	private int hp;
	
	@Persistent
	private int attack;
	
	@Persistent 
	private int defence;
	
	@Persistent
	private int specialAttack;
	
	@Persistent
	private int specialDefence;
	
	@Persistent 
	private int quick;
	
	@Persistent
	private int luck;
	
	@Persistent 
	private int money;
	
	@Persistent
	private int location; //-1 : 死亡、　０；戻ってきている、　１；ダンジョン
	
	public ZiziiDto(String userId, String name, String picture, int hp, int attack, int defence,
			int specialAttack, int specialDefence, int quick, int luck,
			int money, int location) {
		super();
		this.userId = userId;
		this.name = name;
		this.picture = picture;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.specialAttack = specialAttack;
		this.specialDefence = specialDefence;
		this.quick = quick;
		this.luck = luck;
		this.money = money;
		this.location = location;
	}
	
	public String userId(){
		return userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture){
		this.picture = picture;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	public int getSpecialDefence() {
		return specialDefence;
	}

	public void setSpecialDefence(int specialDefence) {
		this.specialDefence = specialDefence;
	}

	public int getQuick() {
		return quick;
	}

	public void setQuick(int quick) {
		this.quick = quick;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getWhere() {
		return location;
	}

	public void setWhere(int where) {
		this.location = where;
	}

	public Key getKey() {
		return key;
	}
	
	public int getLocation(){
		return this.location;
	}
	
	public void setLocation(int location){
		this.location = location;
	}

}
