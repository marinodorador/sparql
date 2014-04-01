package sintax; 

import java.io.IOException;
/*
 * Object ::= GraphNode
 */
public class Object extends Production{

	@Override
	public boolean analize() throws IOException {
		return $.analize("GraphNode");
	}

}