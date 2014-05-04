package sintax; 

import java.io.IOException;
import java.math.BigDecimal;

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
			this.val = $.current.lexeme;
			type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-integer";
			this.expr = new NodeValueInteger(Long.parseLong(val));
			$.next();
			return true;
		}
		case DECIMAL_POSITIVE:{
			this.val = $.current.lexeme;
			type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-decimal";
			this.expr = new NodeValueDecimal(new BigDecimal(val));
			$.next();
			return true;
		}
		case DOUBLE_POSITIVE:
			this.val = $.current.lexeme;
			type = "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-double";
			this.expr = new NodeValueDouble(Double.parseDouble(val));
			$.next();
			return true;
		}
		
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("NumericLiteral").FOLLOWS();
	}
}