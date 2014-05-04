package sintax;

import java.io.IOException;
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
				if(!e.analize()) return false;
				expr.add(e.expr);
				while($.current.token == Token.COMMA){
					$.next();
					Expression e2 = (Expression)$.get("Expression");
					if(!e2.analize()) return false;
					expr.add(e2.expr);
				}
				
				if($.current.token != Token.RIGTH_PARENTH) return false;
				$.next();
				break;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("IRIrefOrFunction").FOLLOWS();
	}

}
