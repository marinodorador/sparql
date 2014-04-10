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
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.COMMA},
				get("ObjectList").FOLLOWS()
				});
	}

}