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
		TriplesNode triplesNode = (TriplesNode) $.get("TriplesNode" );
		
		if (varOrterm.analize()){
			PropertyListNotEmpty plne = (PropertyListNotEmpty)$.get("PropertyListNotEmpty");
			plne.subject  = varOrterm.node;
			boolean result =  plne.analize();
			
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
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("TriplesBlock").FOLLOWS(),
				new Token[]{Token.PERIOD}
				});
	}
}