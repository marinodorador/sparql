package sintax; 
import lexic.Alex;
import lexic.Token;

/**
 * @author esteban
 *
 * Fisrts: {'BASE', 'PREFIX', 'SELECT'}
 */

public class Query extends Analizer{
	
	public Query(Alex alex){
		this.alex = alex;
	}

	public boolean analize(){
		if(current.token == Token.BASE || current.token == Token.PREFIX || current.token == Token.SELECT){
			if(!$.analize("Prologue",this)) return false;
			if(!$.analize("SelectQuery",this)) return false;
		}else{
			return false;
		}
		return true;
	}
}