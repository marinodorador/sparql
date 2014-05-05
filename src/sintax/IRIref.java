package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;
/**
 * 
 * IRIref ::= IRI_REF 
 *			 |PrefixedName
 *
 */
public class IRIref extends Production{
	public com.hp.hpl.jena.query.Query  query;
	public String val;
	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.IRI_REF){
			val = $.current.lexeme;
			val = val.substring(1, val.length()-1);
			$.next();
			return true;
		}else if($.current.token == Token.PNAME_LN ){
			String[] aux = $.current.lexeme.split(":");
			
			String auxIri = Query.query.getPrefixMapping().getNsPrefixURI(aux[0]);
			auxIri = auxIri.substring(1,auxIri.length()-1);
			String local = aux[1];

			val = auxIri+local;
					
			$.next();
			return true;
		}else if ($.current.token == Token.PNAME_NS){
			String[] aux =  $.current.lexeme.split(":");

			String auxIri = query.getPrefixMapping().getNsPrefixURI(aux[0]);

			val = auxIri;
		}
		return false;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.IRI_REF );
		
		for ( Token t : get("PrefixedName").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.NIL );
		ans.add( Token.LEFT_PARENTH );
		
		for ( Token t : get("PrimaryExpression").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("GraphTerm").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("VarOrIRIref").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("SourceSelector").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}

}