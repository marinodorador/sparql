package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.ExprVar;

import lexic.Token;
/*
 * Var ::= VAR1 
		|  VAR2
 **/
public class Var extends Production{
	public String value = null;
	public Node node = null;
	public Expr expr = null;
	public boolean process() throws IOException{
		switch($.current.token){
			case VAR1:
				value = $.current.lexeme.substring(1,$.current.lexeme.length());
				node =  com.hp.hpl.jena.sparql.core.Var.alloc(value);
				expr = new ExprVar(node);
				$.next();
				break;
			case VAR2:
				value = $.current.lexeme.substring(1,$.current.lexeme.length());
				node =  com.hp.hpl.jena.sparql.core.Var.alloc(value);
				expr = new ExprVar(node);
				$.next();
				break;
			default:
				return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.VAR1);
		ans.add(Token.VAR2);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.FROM );
		
		for ( Token t : get("VarOrIRIref").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("VarOrTerm").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("OrderCondition").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("PrimaryExpression").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}