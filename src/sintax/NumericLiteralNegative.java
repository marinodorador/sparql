package sintax; 

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDecimal;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDouble;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueInteger;

import lexic.Token;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.INTEGER_NEGATIVE );
		ans.add( Token.DECIMAL_NEGATIVE );
		ans.add( Token.DOUBLE_NEGATIVE );
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.PLUS );
		ans.add( Token.LESS );
		
		for ( Token t : get("NumericLiteral").FIRSTS() )
			ans.add(t);
		for ( Token t : get("NumericExpression").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralPositive").FIRSTS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralNegative").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
}