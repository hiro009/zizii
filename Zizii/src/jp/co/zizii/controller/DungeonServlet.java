package jp.co.zizii.controller;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.zizii.dto.Dungeon;
import jp.co.zizii.dto.HistoryDto;
import jp.co.zizii.dto.Player;
import jp.co.zizii.factory.PMF;
import jp.co.zizii.model.DundeonModel;

public class DungeonServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		String dungeonName = (String)req.getParameter("dungeon");
		if(dungeonName == null){
			resp.sendRedirect("/select_dungeon");
		}
		//ダンジョンを選択
		Dungeon dungeon = null;
		for(Dungeon d : Dungeon.values()){
			if(d.toString().compareTo(dungeonName) == 0){
				dungeon = d;
				break;
			}
		}
		if(dungeon == null) resp.sendRedirect("/select_dungeon");
		System.out.println("test");
		HttpSession session = req.getSession();
		Player player = (Player)session.getAttribute("player");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		DundeonModel dundeonModel = new DundeonModel(pm);
		try{
			
			HistoryDto history = dundeonModel.goDundeon(dungeon, player);
			for(String hist : history.getHistry()){
				System.out.println(hist);
			}
			resp.sendRedirect("/top");
		}finally{
			pm.close();
		}
	}
}
