package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.graph.Node;

import lexic.Token;
/*
 * Object ::= GraphNode
 */
public class Object extends Production{
	public Node node = null;
	@Override
	public boolean process() throws IOException {
		GraphNode gn = (GraphNode)$.get("GraphNode");
		boolean result = gn.analize();
		node = gn.node;
		return result;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.COMMA},
				get("ObjectList").FOLLOWS()
				});
	}

}