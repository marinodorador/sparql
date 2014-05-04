package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.E_Regex;
import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * RegexExpression	::= 'REGEX' '(' Expression ',' Expression ( ',' Expression )? ')'
 **/
public class RegexExpression extends Production{
	public Expr expr = null;
	@Override
	public boolean process() throws IOException {
		Expression e1 = (Expression)$.get("Expression");
		Expression e2 = (Expression)$.get("Expression");
		Expression e3 = (Expression)$.get("Expression");
		if($.current.token != Token.REGEX) return false;
	    $.next();
	    if($.current.token != Token.LEFT_PARENTH) return false;
	    $.next();
		if(!e1.analize()) return false;
		if($.current.token != Token.COMMA) return false;
		$.next();
		if(!e2.analize()) return false;
		
		if($.current.token == Token.COMMA){
			$.next();
			if(!e3.analize())return false;
			expr = new E_Regex(e1.expr, e2.expr, e3.expr);
		
		}else{
			expr = new E_Regex(e1.expr, e2.expr,null);
		}
		if($.current.token != Token.RIGTH_PARENTH)return false;
		$.next();
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("BuiltInCall").FOLLOWS()
				});
	}

}