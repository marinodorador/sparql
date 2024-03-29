package sintax;

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.ExprList;

import lexic.Token;

/**
 * @author esteban
 *	
 * ArgList	 ::=  	( NIL | '(' Expression ( ',' Expression )* ')' )
 */
public class ArgList  extends Production {
	public ExprList expr = new ExprList();
	@Override
	public boolean process() throws IOException {
		switch($.current.token){
			case NIL: $.next();break;
			case LEFT_PARENTH:
				$.next();
				Expression e = (Expression)$.get("Expression");
				if(!e.analize1()) return false;
				expr.add(e.expr);
				while($.current.token == Token.COMMA){
					$.next();
					Expression e2 = (Expression)$.get("Expression");
					if(!e2.analize1()) return false;
					expr.add(e2.expr);
				}
				
				if($.current.token == Token.RIGTH_PARENTH){
					$.next();
				}
				else{
					MistakeLog.spected.add(Token.RIGTH_PARENTH);
					return  false;
				}
				break;
		}
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.NIL );
		ans.add( Token.LEFT_PARENTH );
		
		return ans;
	}
	
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return $.get("IRIrefOrFunction").FOLLOWS();
	}

}
