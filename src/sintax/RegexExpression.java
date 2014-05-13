package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
		if($.current.token != Token.REGEX){
			MistakeLog.spected.add(Token.REGEX);
			return false;
		}
	    $.next();
	    if($.current.token != Token.LEFT_PARENTH){
	    	MistakeLog.spected.add(Token.LEFT_PARENTH);
	    	return false;
	    }
	    $.next();
		if(!e1.analize()) return false;
		if($.current.token != Token.COMMA){
			MistakeLog.spected.add(Token.COMMA);
			return false;
		}
		$.next();
		if(!e2.analize1()) return false;
		
		if($.current.token == Token.COMMA){
			$.next();
			if(!e3.analize1())return false;
			expr = new E_Regex(e1.expr, e2.expr, e3.expr);
			
		
		}else{
			expr = new E_Regex(e1.expr, e2.expr,null);
		}
		if($.current.token != Token.RIGTH_PARENTH)return false;
		$.next();
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.REGEX);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("BuiltInCall").FOLLOWS();
	}

}