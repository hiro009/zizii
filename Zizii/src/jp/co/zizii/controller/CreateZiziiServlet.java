package jp.co.zizii.controller;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.zizii.dto.Player;
import jp.co.zizii.dto.ZiziiDto;
import jp.co.zizii.factory.PMF;
import jp.co.zizii.model.ZiziiModel;

public class CreateZiziiServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			ZiziiModel ziziiModel = new ZiziiModel(pm);
			
			HttpSession session = req.getSession();
			Player player = (Player)session.getAttribute("player");
			String ziziiName = req.getParameter("ziziiName");
			
			ZiziiDto zizii = ziziiModel.registNewZizii(player.getUserId(), ziziiName);
			session.setAttribute("zizii", zizii);
			resp.sendRedirect("/top");
		}catch(Exception e){
			throw new IOException(e);
		}finally{
			pm.close();
		}
	}
	

}
