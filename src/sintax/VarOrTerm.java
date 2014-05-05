package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("Var").FIRSTS() )
			ans.add(t);
		for ( Token t : get("GraphTerm").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("PropertyListNotEmpty").FIRSTS() )
			ans.add(t);
		for ( Token t : get("GraphNode").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}