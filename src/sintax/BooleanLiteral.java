package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueBoolean;

import lexic.Token;

/**
 * 
 * @author esteban
 *
 * BooleanLiteral	::= 'true' 				
 *					  | 'false'
 */
public class BooleanLiteral extends Production{
	
	public String val = null;
	public String type =  "http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-boolean";
	public Expr expr = null;
	public boolean process() throws IOException{
		switch($.current.token){
			case TRUE:
				val = "true";
				expr = new NodeValueBoolean(true);
				$.next();			
				break;
			case FALSE:
				val = "false";
				expr = new NodeValueBoolean(false);
				$.next();
				break;
			default: return false;
		}
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return new Token[]{
				Token.TRUE,
				Token.FALSE
		};
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("GraphTerm").FOLLOWS();
	}
}