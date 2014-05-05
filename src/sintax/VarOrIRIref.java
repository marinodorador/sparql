package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("Var").FIRSTS() )
			ans.add(t);
		for ( Token t : get("IRIref").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("Verb").FOLLOWS();
	}

}