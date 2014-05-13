package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
		System.out.println("1"+expr.toString());
		while(true){
			if ( $.current.token == Token.AND ){
				$.next();
				ValueLogical vl2 = (ValueLogical)$.get("ValueLogical");
				if ( ! vl2.analize()) return false;
				System.out.println("2"+vl2.expr.toString());
				this.expr = new E_LogicalAnd(this.expr, vl2.expr);
				System.out.println("3"+expr.toString());
			}else break;
		}
		
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("ValueLogical").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.OR );
		
		for ( Token t : get("Expression").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}