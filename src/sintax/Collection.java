package sintax; 

import java.io.IOException;

import lexic.Token;

public class Collection extends Production{

	@Override
	public boolean analize() throws IOException {
		if($.current.token == Token.LEFT_PARENTH){
			$.next();
			if($.analize("GraphNode")){
				while($.analize("GraphNode"));
				if($.current.token == Token.RIGTH_PARENTH){
					$.next();
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

}