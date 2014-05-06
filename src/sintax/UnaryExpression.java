package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.E_LogicalNot;
import com.hp.hpl.jena.sparql.expr.E_UnaryMinus;
import com.hp.hpl.jena.sparql.expr.E_UnaryPlus;
import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
public class UnaryExpression extends Production{
	public Expr expr = null;
	/**
	 * @author Romina
	 *
	 * UnaryExpression = ('!' | '+' | '-')? PrimaryExpression
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		PrimaryExpression pe = (PrimaryExpression)$.get("PrimaryExpression");
		
		boolean result = false;
		
		switch($.current.token ){
			case NOT:
				$.next();
				result = pe.analize();
				this.expr = new E_LogicalNot(pe.expr);
			break;
			case PLUS:
				$.next();
				result = pe.analize();
				this.expr = new E_UnaryPlus(pe.expr);
			break;
			case SUB:
				$.next();
				result = pe.analize();
				this.expr = new E_UnaryMinus(pe.expr);
			break;
			default:
				result = pe.analize();
				this.expr = pe.expr;
		}
		
		
		
		
		return result;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.NOT );
		ans.add( Token.PLUS );
		ans.add( Token.SUB );
		
		for ( Token t : get("PrimaryExpression").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.MULT );
		ans.add( Token.DIV );
		
		for ( Token t : get("MultiplicativeExpression").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}