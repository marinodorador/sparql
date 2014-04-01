package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * OrderClause	::=   'ORDER' 'BY' OrderCondition+
 * 
 * FIRST(OrderClause) = {'ORDER BY', }
 * FIRST(OrderCondition+) = {'ASC', 'DESC', '(' , VAR1, VAR2}
 * FOLLOW(OrderClause) = {LIMIT, OFFSET, $}
 * */
public class OrderClause extends Production{
	public boolean analize() throws IOException{
		if($.current.token != Token.ORDER_BY) return false;
		$.next();
		do{
			if( $.current.token == Token.ASC ||  
					$.current.token == Token.DESC ||
					$.current.token == Token.LEFT_PARENTH || 
					$.current.token == Token.VAR1 ||
					$.current.token == Token.VAR2){
				$.next();
				if(!$.analize("OrderCondition")) return false;
			}else{
				return false;
			}
		}while($.current.token == Token.ASC ||  
				$.current.token == Token.DESC ||
				$.current.token == Token.LEFT_PARENTH || 
				$.current.token == Token.VAR1 ||
				$.current.token == Token.VAR2);
		
		if($.current.token != Token.LIMIT && $.current.token != Token.OFFSET && $.current.token != Token.END)
			return false;
	
		return true;
	}
}