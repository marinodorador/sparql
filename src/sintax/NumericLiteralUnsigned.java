package sintax; 

import java.io.IOException;
import java.math.BigDecimal;

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
			default: return false;
		}
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("NumericLiteralUnsigned").FOLLOWS();
	}
}