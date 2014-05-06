package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.E_Add;
import com.hp.hpl.jena.sparql.expr.E_Subtract;
import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;

public class NumericExpression extends Production{
	Expr expr = null;
	/**
	 * @author Romina
	 *
	 * NumericExpression = AdditiveExpression ::=  

			MultiplicativeExpression (
			
	 					   '+' MultiplicativeExpression 
 					  |    '-' MultiplicativeExpression 
 					  |    NumericLiteralPositive 
 					  |    NumericLiteralNegative )*
 					  
	 * 
	 * @throws IOException
	 */
	
	public boolean process() throws IOException {
		MultiplicativeExpression me = (MultiplicativeExpression)$.get("MultiplicativeExpression");
		if(me.analize()){
			this.expr = me.expr;
			NumericLiteralPositive nlp = (NumericLiteralPositive)$.get("NumericLiteralPositive");
			NumericLiteralNegative nln = (NumericLiteralNegative)$.get("NumericLiteralNegative");
			while(true){

				if($.current.token == Token.PLUS){
					$.next();
					MultiplicativeExpression me2 = (MultiplicativeExpression) $.get("MultiplicativeExpression");
					if(!me2.analize()) return false;
					this.expr = new E_Add(this.expr, me2.expr);
				}else if($.current.token == Token.LESS){
					$.next();
					MultiplicativeExpression me2 = (MultiplicativeExpression) $.get("MultiplicativeExpression");
					if(!me2.analize()) return false;
					this.expr = new E_Subtract(this.expr, me2.expr);
				}else if(nlp.analize()) {
					this.expr = nlp.expr;
					break;
				}else if(nln.analize()){
					this.expr = nln.expr; 
					break;
				}else{
					break;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("MultiplicativeExpression").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.EQUAL );
		ans.add( Token.NOT_EQUAL );
		ans.add( Token.GREATER );
		ans.add( Token.LESS );
		ans.add( Token.GET );
		ans.add( Token.LET );
		
		for ( Token t : get("ValueLogical").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}