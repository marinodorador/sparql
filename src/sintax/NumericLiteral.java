package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("NumericLiteralUnsigned").FIRSTS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralPositive").FIRSTS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralNegative").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("PrimaryExpression").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("GraphTerm").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}