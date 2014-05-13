package sintax; 

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDecimal;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDouble;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueInteger;

import lexic.Token;

/**
 * 
 * @author esteban
 *
 *NumericLiteralUnsigned	  		::=  	INTEGER 
										|	DECIMAL 
										|	DOUBLE
 */
public class NumericLiteralUnsigned extends Production{
	public String val = null;
	public String type = null;
	public Expr expr = null;
	public boolean process() throws IOException{
		switch($.current.token){
			case INTEGER:{
				
				val = $.current.lexeme;
				type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-integer";
				this.expr = new NodeValueInteger(Long.parseLong(val));
				$.next();
				
				break;
			}
			case DECIMAL:{
				val = $.current.lexeme;
				type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-decimal";
				this.expr = new NodeValueDecimal(new BigDecimal(val));
				$.next();
				break;
			}
			case DOUBLE:{
				val = $.current.lexeme;
				type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-boolean";
				this.expr = new NodeValueDouble(Double.parseDouble(val));
				$.next();
				break;
			}
			default:{
				MistakeLog.spected.add(Token.DOUBLE);
				MistakeLog.spected.add(Token.INTEGER);
				MistakeLog.spected.add(Token.DECIMAL);
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.INTEGER );
		ans.add( Token.DECIMAL );
		ans.add( Token.DOUBLE );
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return $.get("NumericLiteral").FOLLOWS();
	}
}