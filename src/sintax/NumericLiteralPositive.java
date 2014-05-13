package sintax; 

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDecimal;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDouble;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueInteger;

import lexic.Token;

public class NumericLiteralPositive extends Production{
	public String val  = null;
	public String type = null;
	public Expr expr = null;
	/**
	 * @author Romina
	 *
	 * NumericLiteralPositive = INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		switch($.current.token){
		case INTEGER_POSITIVE:{
			$.next();
			this.val = $.current.lexeme;
			type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-integer";
			this.expr = new NodeValueInteger(Long.parseLong(val));
			
			return true;
		}
		case DECIMAL_POSITIVE:{
			$.next();
			this.val = $.current.lexeme;
			type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-decimal";
			this.expr = new NodeValueDecimal(new BigDecimal(val));
			
			return true;
		}
		case DOUBLE_POSITIVE:
			$.next();
			this.val = $.current.lexeme;
			type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-double";
			this.expr = new NodeValueDouble(Double.parseDouble(val));
			
			return true;
		}
		MistakeLog.spected.add(Token.DECIMAL_POSITIVE);
		MistakeLog.spected.add(Token.DOUBLE_POSITIVE);
		MistakeLog.spected.add(Token.INTEGER_POSITIVE);
		return false;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.INTEGER_POSITIVE );
		ans.add( Token.DECIMAL_POSITIVE );
		ans.add( Token.DOUBLE_POSITIVE );
		
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