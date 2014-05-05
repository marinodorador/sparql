package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;

import lexic.Token;
/*
 *  VarOrIRIref ::=  Var 
 *				|   IRIref
 **/
public class VarOrIRIref extends Production{
	public Node node = null;
	@Override
	public boolean process() throws IOException {
		Var v = (Var)$.get("Var");
		IRIref iriRef = (IRIref)$.get("IRIref");
		
		if(v.analize()){
			node = v.node;
			return true;
		}else if(iriRef.analize()){
			node = NodeFactory.createURI(iriRef.val);
			return true;
		}else 
			return false;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return construct(new Token[][]{
				get("Var").FIRSTS(),
				get("IRIref").FIRSTS(),
				});
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("Verb").FOLLOWS()
				});
	}

}