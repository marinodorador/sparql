package sintax; 
import java.io.IOException;
import static lexic.Token.*;
import lexic.Token;

/**
 * @author esteban
 *
 * Fisrts: {'BASE', 'PREFIX', 'SELECT'}
 */

public class Query extends Production{
	public boolean process() throws IOException{
		if($.current.token == BASE || $.current.token == PREFIX || $.current.token == SELECT){
			if(!$.analize("Prologue")) return false;
			if(!$.analize("SelectQuery")) return false;
			
			$.next();
			if($.current.token != END) return false;
			
		}else{
			return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return new Token[]{END};
	}
}