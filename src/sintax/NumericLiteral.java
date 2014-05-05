package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * ::=  	NumericLiteralUnsigned 
 *		| 	NumericLiteralPositive 
 *		| 	NumericLiteralNegative
 **/
public class NumericLiteral extends Production{
	public String val;
	public String type;
	public Expr expr = null;
	public boolean process() throws IOException{
		if($.current.token == Token.INTEGER || $.current.token == Token.DECIMAL|| $.current.token == Token.DOUBLE){
			NumericLiteralUnsigned nlu = (NumericLiteralUnsigned)$.get("NumericLiteralUnsigned");
			if(nlu.analize()){
				val = nlu.val;
				type = nlu.type;
				expr = nlu.expr;
				return true;
			}else return false;
		}else if($.current.token == Token.INTEGER_POSITIVE || $.current.token == Token.DECIMAL_POSITIVE || $.current.token == Token.DOUBLE_POSITIVE){
			NumericLiteralPositive nlp = (NumericLiteralPositive)$.get("NumericLiteralPositive");
			if(nlp.analize()){
				val = nlp.val;
				type = nlp.type;
				expr = nlp.expr;
				return true;
			}else return false;
		}else if( $.current.token == Token.INTEGER_NEGATIVE  || $.current.token == Token.DECIMAL_NEGATIVE || $.current.token == Token.DOUBLE_NEGATIVE){
			NumericLiteralNegative nln = (NumericLiteralNegative)$.get("NumericLiteralNegative");
			if(nln.analize()){
				val = nln.val;
				type = nln.type;
				expr = nln.expr;
				return true;
			}else return false;
		}

		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("PrimaryExpression").FOLLOWS(),
				get("GraphTerm").FOLLOWS(),
				});
	}
}