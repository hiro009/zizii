package jp.co.zizii.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class HistoryDto {
	
	//必須パラメータ
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String userId;
	
	@Persistent
	private List<String> history;
	
	@Persistent
	private Date date;

	public HistoryDto(String userId, Date date) {
		this.userId = userId;
		this.history = new ArrayList<String>();
		this.date = date;
	}

	public Key getKey() {
		return key;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getHistry() {
		return history;
	}

	public void setHistry(List<String> history) {
		this.history = history;
	}
	
	public void addHistory(String hist){
		this.history.add(hist);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
		

}
