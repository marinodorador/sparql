package sintax; 
import java.io.IOException;
import lexic.Token;

/**
 * @author esteban
 *
 * Fisrts: {'BASE', 'PREFIX', 'SELECT'}
 */

public class Query extends Production{
	public boolean process() throws IOException{
		if($.current.token == Token.BASE || $.current.token == Token.PREFIX || $.current.token == Token.SELECT){
			if(!$.analize("Prologue")) return false;
			if(!$.analize("SelectQuery")) return false;
			
			$.next();
			if($.current.token != Token.END) return false;
			
		}else{
			return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}