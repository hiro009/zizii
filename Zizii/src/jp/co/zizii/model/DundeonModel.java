package jp.co.zizii.model;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jp.co.zizii.dto.Dungeon;
import jp.co.zizii.dto.HistoryDto;
import jp.co.zizii.dto.Player;
import jp.co.zizii.dto.ZiziiDto;

public class DundeonModel {
	
	private final PersistenceManager pm;
	private static final Calendar gmtCal;
	//回復係数
	private static final int CURE_RATE = 2;
	//レベル係数
	private static final int LEVEL_RATE = 2;
	//アクションゲージ→この値に達するとアクション（こうげき）する
	private static final int ACTION_GAGE = 100;
	//ダメージ係数
	private static final int DAMAGE_RATE = 4;
	//モンスターパラメータ
	private static final int MONSTOR_HP = 8;
	private static final int MONSTOR_ATTACK = 3;
	private static final int MONSTOR_DEFFENCE = 3;
	private static final int MONSTOR_QUICK = 3;
	private static final int MONSTOR_MONEY = 5;
	//モンスターの最低値
	private static final int MIN_MONSTOR_HP = 5;
	private static final int MIN_MONSTOR_ATTACK = 3;
	private static final int MIN_MONSTOR_DEFFENCE = 3;
	private static final int MIN_MONSTOR_QUICK = 3;
	private static final int MIN_MONSTOR_MONEY = 5;
	
	//モンスターのステータス
	private static final String[] monnamearry={"若者","年金泥棒","チンピラ","犬","ネット右翼","元学生運動家","チャリティーオークション主催者","大津市民","平成生まれ","尼崎市民","人権団体メンバー","ニート暦3ヶ月","義理の息子","孫の友達","孫の友達の母親","自分ならではのおしゃれ","りんごまりな","ネットワークビジネス関係者","かわいい子だと思ったら勧誘だった","むしろマルチと堂々という"};
	
	
	
	
	static {
		gmtCal = Calendar.getInstance(TimeZone.getDefault());
	}
	public DundeonModel(PersistenceManager pm){
		this.pm = pm;
	}
	
	public HistoryDto goDundeon(Dungeon dundeon, Player player){
		Query query = pm.newQuery(ZiziiDto.class);
		query.setFilter("userId == userIdParam");
		query.declareParameters("String userIdParam");
		List<ZiziiDto> ziziis = (List<ZiziiDto>)query.execute(player.getUserId());
		
		//じじいがいない場合->警告ページへ飛ぶ（例外を発生させて）
		if(ziziis.isEmpty()) {
			HistoryDto history = new HistoryDto(player.getUserId(), gmtCal.getTime());
			history.addHistory("じじいがいねぇ");
			return history;
		}
		
		ZiziiDto zizii = ziziis.get(0);
		int ziziiHp = zizii.getHp();
		
		HistoryDto history = new HistoryDto(player.getUserId(), gmtCal.getTime());
		String hist = "";
		//ダンジョン突入
		for(int i=0; i < dundeon.toFloor(); i++){
			//生死判定
			if(ziziiHp <= 0){
				break;
			}
			hist = "[位置] " + dundeon.toString()+"の"+ (i+1) + "階において";
			history.addHistory(hist);
			
			if(ziziiHp != zizii.getHp()){//HPが減っているなら回復
				ziziiHp += (zizii.getHp() - ziziiHp) / CURE_RATE;
			}
			//HP状況記録
			hist = "[状況] HP残り"+ziziiHp;
			//バトル
			ziziiHp = battle(dundeon.toLevel(), zizii, ziziiHp, player, history);
		}
		//ダンジョン突破
		if(ziziiHp > 0){
			hist = "[結果]　" + zizii.getName() + "は" + dundeon.toString() + "から生還した";
		}
		else {
			zizii.setLocation(-1);
		}
		//記録をデータベースに登録
		pm.makePersistent(history);
		pm.makePersistent(player);
		
		return history;
	}
	
	private int battle(int level, ZiziiDto zizii, int ziziiHp, Player player, HistoryDto history){
		level++;
		int ramd = (int)(Math.random()*(monnamearry.length-1));
		String monstorName = monnamearry[ramd];
		
		ramd = (int)(Math.random()*MONSTOR_HP);
		ramd += MIN_MONSTOR_HP;
		int monstorHp = ramd+(LEVEL_RATE*level);
		ramd = (int)(Math.random()*MONSTOR_ATTACK);
		ramd += MIN_MONSTOR_ATTACK;
		int monstorAttack = ramd+(LEVEL_RATE*level);
		ramd = (int)(Math.random()*MONSTOR_DEFFENCE);
		ramd += MIN_MONSTOR_DEFFENCE;
		int monstorDeffence = ramd+(LEVEL_RATE*level);
		ramd = (int)(Math.random()*MONSTOR_QUICK);
		ramd += MIN_MONSTOR_QUICK;
		int monstorQuick = ramd+(LEVEL_RATE*level);
		ramd = (int)(Math.random()*MONSTOR_MONEY);
		ramd += MIN_MONSTOR_MONEY;
		int monstorMoney = ramd+(LEVEL_RATE*level);
		
		int monstorGage = 0;
		int ziziiGage = 0;
		
		//バトル
		while(true){
			ziziiGage += zizii.getQuick();
			monstorGage += monstorQuick;
			//ziziiとモンスター共にアクションゲージが溜まったとき
			if(ziziiGage >= ACTION_GAGE && monstorGage >= ACTION_GAGE){
				if(ziziiGage >= monstorGage){
					monstorHp -= attack(zizii.getAttack(), monstorDeffence, zizii.getName(),monstorName, history);
					//モンスターを殺したかチェック
					if(isKill(monstorHp, zizii.getName(), monstorName, history)){
						player.setMoney(player.getMoney() + monstorMoney);
						return ziziiHp;
					}
					ziziiHp -= attack(monstorAttack, zizii.getDefence(), monstorName, zizii.getName(), history);
					//じじいが死んだかチェック
					if(isDead(ziziiHp, zizii.getName(), history))
						return 0;
				}
				else if(ziziiGage < monstorGage){
					ziziiHp -= attack(monstorAttack, zizii.getDefence(), monstorName, zizii.getName(), history);
					//じじいが死んだかチェック
					if(isDead(ziziiHp, zizii.getName(), history))
						return 0;
					
					monstorHp -= attack(zizii.getAttack(), monstorDeffence, zizii.getName(),monstorName, history);
					//モンスターを殺したかチェック
					if(isKill(monstorHp, zizii.getName(), monstorName, history)){
						player.setMoney(player.getMoney() + monstorMoney);
						return ziziiHp;
					}
				}
				ziziiGage -= ACTION_GAGE;
				monstorGage -= ACTION_GAGE;
			}
			else if(ziziiGage >= ACTION_GAGE){
				monstorHp -= attack(zizii.getAttack(), monstorDeffence, zizii.getName(),monstorName, history);
				//モンスターを殺したかチェック
				if(isKill(monstorHp, zizii.getName(), monstorName, history)){
					player.setMoney(player.getMoney() + monstorMoney);
					return ziziiHp;
				}
				ziziiGage -= ACTION_GAGE;
			}
			else if(monstorGage >= ACTION_GAGE){
				ziziiHp -= attack(monstorAttack, zizii.getDefence(), monstorName, zizii.getName(), history);
				//じじいが死んだかチェック
				if(isDead(ziziiHp, zizii.getName(), history))
					return 0;
				monstorGage -= ACTION_GAGE;
			}
		}
		//return ziziiHp;
	}
	
	/**
	 * 攻撃する側の攻撃力とされる側の防御力を渡して、ダメージを返す
	 * @param attack
	 * @param deffence
	 * @return damage
	 */
	private int attack(int attack, int deffence, String attacker, String deffencer, HistoryDto history){
		int damage = 0;
		damage =(attack + (int)(Math.random()*DAMAGE_RATE)/2) - deffence;
		if(damage <= 0){
			damage = 1;
		}
		String hist = attacker + "は" + deffencer + "に" + damage + "のダメージを与えた";
		history.addHistory(hist);
		return damage;
	}
	
	private boolean isDead(int hp, String ziziiName, HistoryDto history){
		if(hp <= 0){
			String hist = "[結果]　" + ziziiName + "は死んだ。　じじいが死んだら世話がねぇ。";
			history.addHistory(hist);
			return true;
		}
		return false;
	}
	
	private boolean isKill(int hp, String ziziiName, String monstorName, HistoryDto history){
		if(hp <= 0){
			String hist = ziziiName + "は" + monstorName + "を殺した";
			history.addHistory(hist);
			return true;
		}
		return false;
	}


}
