package jp.co.zizii.factory;

import javax.jdo.PersistenceManager;

import jp.co.zizii.model.PlayerModel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ModelFactory {
	
	public static PlayerModel getPlayerModel(){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		return new PlayerModel(user, pm);
	}

}
