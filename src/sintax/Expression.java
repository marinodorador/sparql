package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * Expression	  ::=  	ConditionalOrExpression
 * FIRST   = { '-', '!', '+',INTEGER_POSITIVE,DECIMAL_POSITIVE,DOUBLE_POSITIVE,
 *										INTEGER_NEGATIVE, DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, '(', 'STR',
 *										'LANG','LANGMATCHES', 'DATATYPE', 'BOUND', 'sameTerm', 'isIRI',
 *										'isURI', 'isBLANK', 'isLITERAL', 'REGEX', IRIref, STRING_LITERAL1, STRING_LITERAL2,
 *										STRING_LITERAL_LONG1,STRING_LITERAL_LONG2, INTEGER, DECIMAL, DOUBLE, TRUE, FALSE, 
 * 										VAR1, VAR2
 *									  }
 */
public class Expression extends Production{
	Expr expr = null;
	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		ConditionalOrExpression coe= (ConditionalOrExpression)$.get("ConditionalOrExpression");
		boolean result = coe.analize();
		expr = coe.expr;
		return result;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return  new Token[]{ Token.RIGTH_PARENTH, Token.COMMA};
	}
}