package sintax; 

import java.io.IOException;

import lexic.Token;

public class AdditiveExpression extends Production{

	@Override
	public boolean analize() throws IOException {
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

}