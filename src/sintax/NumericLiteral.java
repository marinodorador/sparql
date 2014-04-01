package sintax; 

import java.io.IOException;

public class NumericLiteral extends Production{
	public boolean analize() throws IOException{
		return $.analize("NumericLiteralUnsigned");
	}
}