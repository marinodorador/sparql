package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;

import lexic.Token;
/*
 *  VarOrIRIref ::=  Var 
 *				|   IRIref
 * FIRST = 'IRI_REF', 'VAR1', 'VAR2'
 **/
public class VarOrIRIref extends Production{
	public Node node = null;
	@Override
	public boolean process() throws IOException {
		
		if($.current.token == Token.VAR1 || $.current.token == Token.VAR2){ //si es falso aqui, se esperaba VAR1 o VAR2
			Var v = (Var)$.get("Var");
			if(v.analize()){
				node = v.node;
				return true;
			}else{
				return false;
			}
		}
		if($.current.token == Token.IRI_REF){ //si es falso aqui, se esperaba un IRI_REF
			IRIref iriRef = (IRIref)$.get("IRIref");
			if(iriRef.analize()){
				node = NodeFactory.createURI(iriRef.val);
				return true;
			}
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("Verb").FOLLOWS()
				});
	}

}