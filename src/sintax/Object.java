package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * Object ::= GraphNode
 */
public class Object extends Production{

	@Override
	public boolean process() throws IOException {
		return $.analize("GraphNode");
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}

}