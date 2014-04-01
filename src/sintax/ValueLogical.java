package sintax; 

import java.io.IOException;
/*
 * ValueLogical	::= RelationalExpression
 */
public class ValueLogical extends Production{

	@Override
	public boolean analize() throws IOException {
		// TODO Auto-generated method stub
		return $.analize("RelationalExpression");
	}

}