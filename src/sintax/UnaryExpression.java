package sintax; 

import java.io.IOException;

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
										
	 * FIRSTS: { '!' | '+' | '-' | PrimaryExpression }
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
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.MULT , Token.DIV },
				get("MultiplicativeExpression").FOLLOWS(),
				});
	}
}