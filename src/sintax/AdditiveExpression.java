package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.E_Add;
import com.hp.hpl.jena.sparql.expr.E_Subtract;
import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/**
 * AdditiveExpression ::=  MultiplicativeExpression ( '+' MultiplicativeExpression 
 *					  |    '-' MultiplicativeExpression 
 *					  |    NumericLiteralPositive 
 *					  |    NumericLiteralNegative )*
 * 
 */
public class AdditiveExpression extends Production{
	Expr expr = null;
	@Override
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
		return get("NumericExpression").FOLLOWS();
	}

}