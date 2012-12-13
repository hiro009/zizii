package jp.co.zizii.controller;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import jp.co.zizii.dto.Player;
import jp.co.zizii.dto.ZiziiDto;
import jp.co.zizii.factory.PMF;
import jp.co.zizii.model.PlayerModel;
import jp.co.zizii.model.ZiziiModel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ZiziiServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
			
		// googleアカウントでログインしているかを確認
		if (user != null) {// ログインしている
			PersistenceManager pm = PMF.get().getPersistenceManager();
			PlayerModel playerModel = new PlayerModel(user, pm);
			try{
				// 新規Playerかチェック
				Player player = playerModel.getLoginPlayer();
				if (player == null) {// 新規プレイヤー
					// プレイヤー登録画面へ
					getServletContext().getRequestDispatcher(
							"/WEB-INF/jsp/regist_player.jsp")
							.forward(req, resp);
				} else {// 既存プレイヤー
					HttpSession session = req.getSession();
					session.setAttribute("player", player);
					ZiziiModel ziziiModel = new ZiziiModel(pm);
					ZiziiDto zizii = ziziiModel.getZizii(user.getUserId());
					req.setAttribute("zizii", zizii);
					// トップ画面へ
					resp.sendRedirect("/top");
				}
				}catch (Exception e) {
					throw new IOException(e);
				}finally{
					pm.close();
				}
		} else {// ログインしていない
			// Googleアカウントのログイン画面にリダイレクト
			// createLoginURL()はログイン画面のURLを返す
			resp.sendRedirect(userService.createLoginURL(req
					.getRequestURI()));
		}
	}
}
