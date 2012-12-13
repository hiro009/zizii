package jp.co.zizii.controller;

import java.io.IOException;

import javax.jdo.PersistenceManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.zizii.dto.Player;
import jp.co.zizii.factory.PMF;
import jp.co.zizii.factory.PlayerFactory;
import jp.co.zizii.model.PlayerModel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class RegisterServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String name = req.getParameter("playerName");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PlayerModel playerModel = new PlayerModel(user, pm);
		
		// 新規プレイヤー登録
		try {
			Player player = playerModel.registNewPlayer(name);
			HttpSession session = req.getSession();
			session.setAttribute("player", player);
			resp.sendRedirect("/top");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}

	}

}
