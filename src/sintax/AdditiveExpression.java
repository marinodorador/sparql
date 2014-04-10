package sintax; 

import java.io.IOException;

import lexic.Token;
/**
 * AdditiveExpression ::=  MultiplicativeExpression ( '+' MultiplicativeExpression 
 *					  |    '-' MultiplicativeExpression 
 *					  |    NumericLiteralPositive 
 *					  |    NumericLiteralNegative )*
 * 
 */
public class AdditiveExpression extends Production{

	@Override
	public boolean process() throws IOException {
		if($.analize("MultiplicativeExpression")){
			while(true){
				if($.current.token == Token.PLUS){
					$.next();
					if(!$.analize("MultiplicativeExpression")){
						return false;
					}
				}else if($.current.token == Token.LESS){
					$.next();
					if(!$.analize("MultiplicativeExpression")){
						return false;
					}
				}else if(!$.analize("NumericLiteralPositive") || $.analize("NumericLiteralNegative")){
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
				get("NumericExpression").FOLLOWS()
				});
	}

}