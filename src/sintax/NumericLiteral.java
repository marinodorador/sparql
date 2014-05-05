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
		NumericLiteralUnsigned nlu = (NumericLiteralUnsigned)$.get("NumericLiteralUnsigned");
		NumericLiteralPositive nlp = (NumericLiteralPositive)$.get("NumericLiteralPositive");
		NumericLiteralNegative nln = (NumericLiteralNegative)$.get("NumericLiteralNegative");
		if(nlu.analize()){
			val = nlu.val;
			type = nlu.type;
			expr = nlu.expr;
			return true;
		}else if(nlp.analize()){
			val = nlp.val;
			type = nlp.type;
			expr = nlp.expr;
			return true;
		}else if(nln.analize()){
			val = nln.val;
			type = nln.type;
			expr = nln.expr;
			return true;
		}
		
		return false;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return construct(new Token[][]{
				get("NumericLiteralUnsigned").FIRSTS(),
				get("NumericLiteralPositive").FIRSTS(),
				get("NumericLiteralNegative").FIRSTS(),
				});
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("PrimaryExpression").FOLLOWS(),
				get("GraphTerm").FOLLOWS(),
				});
	}
}