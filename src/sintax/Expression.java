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
	public Token[] initFIRSTS() throws IOException {
		return get("ConditionalOrExpression").FIRSTS();
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return  new Token[]{ Token.RIGTH_PARENTH, Token.COMMA};
	}
}