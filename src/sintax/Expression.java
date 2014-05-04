package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * Expression	  ::=  	ConditionalOrExpression
 */
public class Expression extends Production{
	Expr expr = null;
	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		ConditionalOrExpression coe= (ConditionalOrExpression)$.get("ConditionalOrExpression");
		boolean result = coe.analize();
		expr = coe.expr;
		return result;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return  new Token[]{ Token.RIGTH_PARENTH, Token.COMMA};
	}
}