package jp.co.zizii.model;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jp.co.zizii.dto.ZiziiDto;
import jp.co.zizii.factory.ZiziiFactory;

public class ZiziiModel {
	
	private final PersistenceManager pm;
	
	public ZiziiModel(PersistenceManager pm){
		this.pm = pm;
	}
	
	public boolean hasZizii(String userId){
		Query query = pm.newQuery(ZiziiDto.class);
		query.setFilter("userId == userIdParam");
		query.declareParameters("String userIdParam");
		List<ZiziiDto> zizii = (List<ZiziiDto>) query.execute(userId);
		return !zizii.isEmpty();
	}
	
	public ZiziiDto registNewZizii(String userId, String name) throws IOException{
		ZiziiDto zizii = ZiziiFactory.newInstance(userId, name);
		try{
			pm.makePersistent(zizii);
			return zizii;
		}catch(Exception e){
			throw new IOException(e);
		}
	}
	
	public ZiziiDto getZizii(String userId){
		Query query = pm.newQuery(ZiziiDto.class);
		query.setFilter("userId == userIdParam");
		query.declareParameters("String userIdParam");
		List<ZiziiDto> zizii = (List<ZiziiDto>)query.execute(userId);
		if(zizii.isEmpty()){
			return null;
		}
		return zizii.get(0);
	}
	
	public void killZizii(String userId){
		Query query = pm.newQuery(ZiziiDto.class);
		query.setFilter("userId == userIdParam" );
		query.declareParameters("String userIdParam");
		query.deletePersistentAll(userId);
	}

}
