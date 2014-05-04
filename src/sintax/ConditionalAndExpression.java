package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.E_LogicalAnd;
import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;

public class ConditionalAndExpression extends Production{
	Expr expr = null;
	/**
	 * @author Romina
	 *
	 * ConditionalAndExpression = ValueLogical ( '&&' ValueLogical )*
	 * FIRSTS: ValueLogical.FIRSTS
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		ValueLogical vl = (ValueLogical)$.get("ValueLogical");
		
		if ( !  vl.analize()) return false;
		expr = vl.expr;
		
		while(true){
			if ( $.current.token == Token.AND ){
				$.next();
				ValueLogical vl2 = (ValueLogical)$.get("ValueLogical");
				if ( ! vl2.analize()) return false;
				this.expr = new E_LogicalAnd(this.expr, vl2.expr);
			}else break;
		}
		
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{Token.RIGHT_BRACE},
				get("Expression").FOLLOWS(),
				});
	}
}