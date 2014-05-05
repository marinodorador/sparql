package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;
/*
 * OrderClause	::=   'ORDER' 'BY' OrderCondition+
 * 
 * FIRST(OrderClause) = {'ORDER BY', }
 * FIRST(OrderCondition+) = {'ASC', 'DESC', '(' , VAR1, VAR2}
 * FOLLOW(OrderClause) = {LIMIT, OFFSET, $}
 * */
public class OrderClause extends Production{
	public boolean process() throws IOException{
		if($.current.token != Token.ORDER_BY) return false;
		$.next();
		do{
			if( $.current.token == Token.ASC ||  
					$.current.token == Token.DESC ||
					$.current.token == Token.LEFT_PARENTH || 
					$.current.token == Token.VAR1 ||
					$.current.token == Token.VAR2){
				OrderCondition orderCond = (OrderCondition)$.get("OrderCondition");
				if(!orderCond.analize()) return false;
				Query.query.addOrderBy(orderCond.sortCondition);
				
			}else return false;
			
		}while($.current.token == Token.ASC ||  
				$.current.token == Token.DESC ||
				$.current.token == Token.LEFT_PARENTH || 
				$.current.token == Token.VAR1 ||
				$.current.token == Token.VAR2);
		
		if($.current.token != Token.LIMIT && $.current.token != Token.OFFSET && $.current.token != Token.END)
			return false;
	
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return new Token[]{LIMIT,OFFSET,END};
	}
}