package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * GraphNode ::=  VarOrTerm | TriplesNode
 * FIRST(GraphNode) = {
 * 						VAR1, VAR2, IRI_REF,PNAME_LN,PNAME_NS, STRING_LITERAL1, STRING_LITERAL2,STRING_LITERAL_LONG1,STRING_LITERAL_LONG2, 
 * 						INTEGER,DECIMAL, DOUBLE, INTEGER_POSITIVE, DECIMAL_POSITIVE, DOUBLE_POSITIVE, INTEGER_NEGATIVE , 
 * 						DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, true, false, NIL, '('
 * 					 }
 **/
public class GraphNode extends Production{
	public boolean process() throws IOException{
		switch($.current.token){
			case VAR1: case VAR2: case IRI_REF: case PNAME_NS: case PNAME_LN: case STRING_LITERAL1: case STRING_LITERAL2:
			case STRING_LITERAL_LONG1: case STRING_LITERAL_LONG2:  case INTEGER: case DECIMAL:
			case DOUBLE: case INTEGER_POSITIVE: case DECIMAL_POSITIVE: case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:  case DECIMAL_NEGATIVE: case DOUBLE_NEGATIVE: case TRUE:
			case FALSE: case NIL:
				if(!$.analize("VarOrTerm")) return false;
			break;
			case LEFT_PARENTH:
				if(!$.analize("TriplesNode")) return false;
			break;
			default:
				return false;
				
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return construct(
			new Token[][]{
				new Token[]{Token.RIGTH_PARENTH},
				$.get("Object").FOLLOWS()
			}
		);
	}
}