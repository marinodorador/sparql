package sintax; 

import java.io.IOException;

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
				}else if( $.current.token == Token.INTEGER_POSITIVE || $.current.token == Token.DECIMAL_POSITIVE || $.current.token == Token.DOUBLE_POSITIVE){
					if(nlp.analize()){
						this.expr = nlp.expr;
					}else return false;
				}else if($.current.token == Token.INTEGER_NEGATIVE || $.current.token == Token.DECIMAL_NEGATIVE || $.current.token == Token.DOUBLE_NEGATIVE){
					if(nln.analize()){
						this.expr = nln.expr; 
					}else return false;
				}else{
					break;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				$.get("NumericExpression").FOLLOWS()
				});
	}

}