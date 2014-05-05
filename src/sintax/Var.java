package sintax; 

import static lexic.Token.*;
import java.io.IOException;

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
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return construct(new Token[][]{
				$.get("VarOrIRIref").FOLLOWS(),
				$.get("VarOrTerm").FOLLOWS(),
				$.get("OrderCondition").FOLLOWS(),
				$.get("PrimaryExpression").FOLLOWS(),
				new Token[]{FROM}
		});
		 
		
		 
		
	}
}