package sintax; 

import java.io.IOException;

public class Expression extends Production{

	@Override
	public boolean analize() throws IOException {
		// TODO Auto-generated method stub
		return $.analize("ConditionalOrExpression");
	}

}