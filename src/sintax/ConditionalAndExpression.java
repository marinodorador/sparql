package sintax; 

import java.io.IOException;

import lexic.Token;

public class ConditionalAndExpression extends Production{
	/**
	 * @author Romina
	 *
	 * ConditionalAndExpression = ValueLogical ( '&&' ValueLogical )*
	 * FIRSTS: ValueLogical.FIRSTS
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		if ( ! $.analize("ValueLogical") )
			return false;
		
		while(true)
		{
			if ( $.current.token == Token.AND )
			{
				$.next();
				if ( ! $.analize("ValueLogical") )
					return false;
			}
			else
				break;
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