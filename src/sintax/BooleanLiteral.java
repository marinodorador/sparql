package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
				$.next();		
				val = "true";
				expr = new NodeValueBoolean(true);	
				break;
			case FALSE:
				$.next();
				val = "false";
				expr = new NodeValueBoolean(false);
				break;
			default:
				MistakeLog.spected.add(Token.TRUE);
				MistakeLog.spected.add(Token.FALSE);
				return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.TRUE );
		ans.add( Token.FALSE );
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
				
		for ( Token t : get("GraphTerm").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("PrimaryExpression").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}