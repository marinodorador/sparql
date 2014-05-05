package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.datatypes.BaseDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;

import lexic.Token;

public class GraphTerm extends Production{
	public Node node = null;
	/**
	 * @author Romina
	 *
	 * GraphTerm = IRIref |	RDFLiteral | NumericLiteral  | BooleanLiteral |	NIL
	 * 
	 * FIRSTS: NIL | (IRIref |	RDFLiteral | NumericLiteral  | BooleanLiteral ).FIRSTS
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		

		
		IRIref iriRef = (IRIref) $.get("IRIref");
		RDFLiteral rdfLiteral = (RDFLiteral) $.get("RDFLiteral");
		NumericLiteral numericLiteral = (NumericLiteral)$.get("NumericLiteral");
		BooleanLiteral booleanLiteral = (BooleanLiteral) $.get("BooleanLiteral");
		
		if($.current.token == Token.NIL){
			throw new SemanticException("NIL is unsopported: "+$.current.lexeme);
		}else if(iriRef.analize()){  
			this.node = NodeFactory.createURI(iriRef.val);
			return true;
		}else if(rdfLiteral.analize()){
			this.node = rdfLiteral.node;
			return true;
		}else if(numericLiteral.analize()){
			this.node = NodeFactory.createLiteral(numericLiteral.val,new BaseDatatype(numericLiteral.type));
			return true;
		}else if(booleanLiteral.analize()){
			this.node = NodeFactory.createLiteral(booleanLiteral.val,new BaseDatatype(booleanLiteral.type));
			return true;
		}
		
		return false;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.NIL );
		
		for ( Token t : get("IRIref").FIRSTS() )
			ans.add(t);
		for ( Token t : get("RDFLiteral").FIRSTS() )
			ans.add(t);
		for ( Token t : get("NumericLiteral").FIRSTS() )
			ans.add(t);
		for ( Token t : get("BooleanLiteral").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("VarOrTerm").FOLLOWS();
	}
}