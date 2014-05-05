package sintax; 

import java.io.IOException;

import lexic.Token;
/**
 * 
 * Collection ::= '(' GraphNode+ ')'
 * FIRST : 'L_PARENTH'
 *
 */
public class Collection extends Production{

	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.LEFT_PARENTH){
			$.next();
			boolean first = $.current.token == Token.VAR1 || $.current.token ==Token.VAR2 //verifica los first de graphnode antes de entar
					||  $.current.token == Token.IRI_REF ||  $.current.token == Token.STRING_LITERAL1
					||  $.current.token == Token.PNAME_LN ||  $.current.token == Token.PNAME_NS
					||  $.current.token == Token.STRING_LITERAL2 ||  $.current.token == Token.STRING_LITERAL_LONG1
					||  $.current.token == Token.STRING_LITERAL_LONG2 ||  $.current.token == Token.INTEGER
					||  $.current.token == Token.DECIMAL ||  $.current.token == Token.INTEGER_POSITIVE 
					||  $.current.token == Token.INTEGER_NEGATIVE ||  $.current.token == Token.DECIMAL_POSITIVE
					||  $.current.token == Token.DECIMAL_NEGATIVE ||  $.current.token == Token.DOUBLE_POSITIVE
					||  $.current.token == Token.DOUBLE_NEGATIVE ||  $.current.token == Token.TRUE ||  $.current.token == Token.FALSE
					||  $.current.token == Token.NIL ||  $.current.token == Token.LEFT_PARENTH;
			if(first){
				if($.analize("GraphNode")){
					
					while($.current.token == Token.VAR1 || $.current.token ==Token.VAR2 //verifica los first de graphnode antes de entar
					||  $.current.token == Token.IRI_REF ||  $.current.token == Token.STRING_LITERAL1
					||  $.current.token == Token.PNAME_LN ||  $.current.token == Token.PNAME_NS
					||  $.current.token == Token.STRING_LITERAL2 ||  $.current.token == Token.STRING_LITERAL_LONG1
					||  $.current.token == Token.STRING_LITERAL_LONG2 ||  $.current.token == Token.INTEGER
					||  $.current.token == Token.DECIMAL ||  $.current.token == Token.INTEGER_POSITIVE 
					||  $.current.token == Token.INTEGER_NEGATIVE ||  $.current.token == Token.DECIMAL_POSITIVE
					||  $.current.token == Token.DECIMAL_NEGATIVE ||  $.current.token == Token.DOUBLE_POSITIVE
					||  $.current.token == Token.DOUBLE_NEGATIVE ||  $.current.token == Token.TRUE ||  $.current.token == Token.FALSE
					||  $.current.token == Token.NIL ||  $.current.token == Token.LEFT_PARENTH){
						if(!$.analize("GraphNode")){
							return false;
						}
					}
					if($.current.token == Token.RIGTH_PARENTH){
						$.next();
						return true;
					}else{
						return false;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("TriplesNode").FOLLOWS()
				});
	}

}