package sintax;

import java.io.IOException;

import lexic.Token;
/*
 * GroupOrUnionGraphPattern	  ::=  	GroupGraphPattern ( 'UNION' GroupGraphPattern )*
 * */
public class GroupOrUnionGraphPattern extends Production{

	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		if($.analize("GroupGraphPattern")){
			
			while($.current.token == Token.UNION){
				$.next();
				if(!$.analize("GroupGraphPattern")){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}
