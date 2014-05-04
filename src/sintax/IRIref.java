package sintax; 

import java.io.IOException;

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
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.NIL, Token.LEFT_PARENTH },
				get("PrimaryExpression").FOLLOWS(),
				get("GraphTerm").FOLLOWS(),
				get("VarOrIRIref").FOLLOWS(),
				get("SourceSelector").FOLLOWS()
				});
	}

}