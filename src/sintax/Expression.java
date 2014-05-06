package sintax;

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.E_LogicalOr;
import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * Expression	  ::=  	ConditionalAndExpression ( '||' ConditionalAndExpression )*
 */
public class Expression extends Production{
	
	Expr expr = null;
	
	public boolean process() throws IOException{
		
		ConditionalAndExpression cae = (ConditionalAndExpression)$.get("ConditionalAndExpression");
		if(!cae.analize()) return false;
		
		this.expr = cae.expr;
		
		while($.current.token == Token.OR)
		{
			$.next();
			ConditionalAndExpression cae2 = (ConditionalAndExpression)$.get("ConditionalAndExpression");
			if(!cae2.analize()) return false;
			this.expr = new E_LogicalOr(this.expr, cae2.expr);
		}
		
//		if($.current.token != Token.RIGTH_PARENTH ) return false;
		
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("ConditionalAndExpression").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.RIGTH_PARENTH );
		ans.add( Token.COMMA );
		
		return ans;
	}
}