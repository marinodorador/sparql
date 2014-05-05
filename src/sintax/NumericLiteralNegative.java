package sintax; 

import java.io.IOException;
import java.math.BigDecimal;

import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDecimal;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDouble;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueInteger;

import lexic.Token;

/*
 *  NumericLiteralNegative	  		::=  	INTEGER_NEGATIVE 
										|	DECIMAL_NEGATIVE 
										|	DOUBLE_NEGATIVE
 */

public class NumericLiteralNegative extends Production{
	public String val = null;
	public String type = null;
	public Expr expr = null;
	public boolean process() throws IOException{
	if($.current.token == Token.INTEGER_NEGATIVE){
		val = $.current.lexeme;
		type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-integer";
		this.expr = new NodeValueInteger(Long.parseLong(val));
		$.next();
		return true;
	}else if($.current.token == Token.DECIMAL_NEGATIVE){
		val = $.current.lexeme;
		type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-decimal";
		this.expr = new NodeValueDecimal(new BigDecimal(val));
		$.next();
		return true;
	}else if($.current.token == Token.DOUBLE_NEGATIVE){
		val = $.current.lexeme;
		type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-double";
		this.expr = new NodeValueDouble(Double.parseDouble(val));
		$.next();
		return true;
	}
	return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("NumericLiteral").FOLLOWS(),
				get("AdditiveExpression").FOLLOWS(),
				new Token[]{ Token.PLUS, Token.LESS },
				});
	}
}