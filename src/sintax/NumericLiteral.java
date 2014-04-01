package sintax; 

import java.io.IOException;
/*
 *  NumericLiteralUnsigned	::=  INTEGER 
 *							|	DECIMAL 
 *							|	DOUBLE
 **/
public class NumericLiteral extends Production{
	public boolean analize() throws IOException{
		return $.analize("NumericLiteralUnsigned");
	}
}