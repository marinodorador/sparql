package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;
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
		System.out.println($.current.token.toString());
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
	
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.ORDER_BY);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("LimitOffsetClauses").FIRSTS() )
			ans.add(t);
		for ( Token t : get("SolutionModifier").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}