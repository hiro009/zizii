package jp.co.zizii.model;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;

import jp.co.zizii.dto.Player;
import jp.co.zizii.factory.PlayerFactory;

public class PlayerModel {
	
	private final PersistenceManager pm; 
	private final User user;
	
	public PlayerModel(User user, PersistenceManager pm){
		this.user = user;
		this.pm = pm;
	}
	
	/**
	 * 新規プレイヤーならばtureを、そうでなければfalseを返す。
	 * @return boolean
	 */
	public boolean isNewPlayer(){
		Query query = pm.newQuery(Player.class);
		query.setFilter("userId == userIdParam");
		query.declareParameters("String userIdParam");
		List<Player> player = (List<Player>) query.execute(user.getUserId());
		return player.isEmpty();
	}
	
	/**
	 * googleアカウントでログインしているPlayerを返す。
	 * 未登録アカウントの場合はnullを返す
	 * @return
	 */
	public Player getLoginPlayer(){
		Query query = pm.newQuery(Player.class);
		query.setFilter("userId == userIdParam");
		query.declareParameters("String userIdParam");
		List<Player> player = (List<Player>) query.execute(user.getUserId());
		if(player.isEmpty())
			return null;
		else
			return player.get(0);
	}
	
	public Player registNewPlayer(String name)throws IOException {
		Player player = PlayerFactory.newInstance(user.getUserId(), user.getNickname(), name);
		try{
			pm.makePersistent(player);
			return player;
		}catch(Exception e){
			throw new IOException(e);
		}
	}

}
