package sintax; 

import java.io.IOException;
/*
 * Expression	  ::=  	ConditionalOrExpression
 */
public class Expression extends Production{

	@Override
	public boolean analize() throws IOException {
		// TODO Auto-generated method stub
		return $.analize("ConditionalOrExpression");
	}

}