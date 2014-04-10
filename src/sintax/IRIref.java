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

	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.IRI_REF){
			$.next();
			return true;
		}else if($.current.token == Token.PNAME_LN || $.current.token == Token.PNAME_NS){
			$.next();
			return true;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.NIL, Token.LEFT_PARENTH },
				get("NumericLiteral").FOLLOWS(),
				get("GraphTerm").FOLLOWS(),
				get("VarOrIRIref").FOLLOWS(),
				get("SourceSelector").FOLLOWS()
				});
	}

}