package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("GraphNode").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.COMMA );
		
		for ( Token t : get("ObjectList").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}

}