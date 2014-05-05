package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.graph.Node;

import lexic.Token;

public class VarOrTerm extends Production{
	public Node node;
	/**
	 * @author Romina
	 *
	 * VarOrTerm = { Var | GraphTerm }
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		Var var = (Var)$.get("Var");
		GraphTerm graphTerm = (GraphTerm)$.get("GraphTerm");
		if(var.analize()){
			node =  com.hp.hpl.jena.sparql.core.Var.alloc(var.value);
			return true;
		}else if (graphTerm.analize()){
			node = graphTerm.node;
			return true;
		}else return false;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return construct(new Token[][]{
				get("Var").FIRSTS(),
				get("GraphTerm").FIRSTS(),
				});
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{Token.VAR1, Token.VAR2, Token.IRI_REF, Token.PNAME_LN, Token.PNAME_NS, Token.A}/*Verb.first*/,
				get("GraphNode").FOLLOWS(),
				});
	}
}