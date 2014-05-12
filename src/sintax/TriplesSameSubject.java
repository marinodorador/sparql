package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.Triple;

import lexic.Token;

public class TriplesSameSubject extends Production{
	public ArrayList<Triple> triples = new ArrayList<Triple>();
	/**
	 * @author Romina
	 *
	 * TriplesSameSubject ::= VarOrTerm PropertyListNotEmpty |	TriplesNode PropertyList
	 * FIRSTS = { VarOrTerm
	 *          | TriplesNode => '('}
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		VarOrTerm varOrterm = (VarOrTerm)$.get("VarOrTerm" ) ;

		if (varOrterm.analize()){
			PropertyListNotEmpty plne = (PropertyListNotEmpty)$.get("PropertyListNotEmpty");
			plne.subject  = varOrterm.node;
			boolean result =  plne.analize();

			if(!result){
				for(Token t: plne.FIRSTS()) MistakeLog.spected.add(t);
				}
				
				for(int i=0;i<plne.triples.size();i++)
					triples.add(plne.triples.get(i));

				return result;
			}
		/*else if(triplesNode.analize()){ //Acci�n no soportada
			PropertyList propList = (PropertyList) $.get("PropertyList");
			
			boolean result = propList.analize();
			return result;
		}*/
		
		return false;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("VarOrTerm").FIRSTS() )
			ans.add(t);
		for ( Token t : get("TriplesNode").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.PERIOD );
		
		for ( Token t : get("TriplesBlock").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}