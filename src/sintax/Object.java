package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.graph.Node;

import lexic.Token;
/*
 * Object ::= GraphNode
 *  * FIRST = {
 * 						VAR1, VAR2, IRI_REF,PNAME_LN,PNAME_NS, STRING_LITERAL1, STRING_LITERAL2,STRING_LITERAL_LONG1,STRING_LITERAL_LONG2, 
 * 						INTEGER,DECIMAL, DOUBLE, INTEGER_POSITIVE, DECIMAL_POSITIVE, DOUBLE_POSITIVE, INTEGER_NEGATIVE , 
 * 						DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, true, false, NIL, '('
 * 					 }
 */
public class Object extends Production{
	public Node node = null;
	
	@Override
	public boolean process() throws IOException {
		boolean first = $.current.token == Token.VAR1 || $.current.token ==Token.VAR2 
				||  $.current.token == Token.IRI_REF ||  $.current.token == Token.STRING_LITERAL1
				||  $.current.token == Token.PNAME_LN ||  $.current.token == Token.PNAME_NS
				||  $.current.token == Token.STRING_LITERAL2 ||  $.current.token == Token.STRING_LITERAL_LONG1
				||  $.current.token == Token.STRING_LITERAL_LONG2 ||  $.current.token == Token.INTEGER
				||  $.current.token == Token.DECIMAL ||  $.current.token == Token.INTEGER_POSITIVE 
				||  $.current.token == Token.INTEGER_NEGATIVE ||  $.current.token == Token.DECIMAL_POSITIVE
				||  $.current.token == Token.DECIMAL_NEGATIVE ||  $.current.token == Token.DOUBLE_POSITIVE
				||  $.current.token == Token.DOUBLE_NEGATIVE ||  $.current.token == Token.TRUE ||  $.current.token == Token.FALSE
				||  $.current.token == Token.NIL ||  $.current.token == Token.LEFT_PARENTH;
				
		
		if(first){
			GraphNode gn = (GraphNode)$.get("GraphNode");
			boolean result = gn.analize();
			node = gn.node;
			return result;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.COMMA},
				get("ObjectList").FOLLOWS()
				});
	}

}