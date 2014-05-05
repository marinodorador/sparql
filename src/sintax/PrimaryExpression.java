package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * PrimaryExpression	::=  	BrackettedExpression 
 *							| 	BuiltInCall 
 *							| 	IRIrefOrFunction 
 *							| 	RDFLiteral 
 *							| 	NumericLiteral 
 *							| 	BooleanLiteral 
 *							| 	Var
 */
public class PrimaryExpression extends Production{
	public Expr expr = null;
	public boolean process() throws IOException{
		if($.current.token == Token.LEFT_PARENTH){
			BrackettedExpression bexpr = (BrackettedExpression) $.get("BrackettedExpression");
			if(bexpr.analize()){
				this.expr = bexpr.expr;
			}else return false;
		}else if($.current.token == Token.STR || $.current.token == Token.LANG
				|| $.current.token == Token.LANGMATCHES || $.current.token == Token.DATATYPE
				|| $.current.token == Token.BOUND || $.current.token == Token.SAMETERM || $.current.token == Token.ISIRI 
				|| $.current.token == Token.ISURI || $.current.token == Token.ISBLANK || $.current.token == Token.ISLITERAL
				||$.current.token == Token.REGEX){
			BuiltInCall bcall = (BuiltInCall)$.get("BuiltInCall");
			if(bcall.analize()){
				this.expr = bcall.expr;
			} else return false;
		}else if($.current.token == Token.NIL || $.current.token == Token.LEFT_PARENTH){
			IRIrefOrFunction iriRef = (IRIrefOrFunction)$.get("IRIrefOrFunction");
			if(iriRef.analize()){
				this.expr = iriRef.expr;
			}else return false;
		}else if($.current.token == Token.STR){
			RDFLiteral rdfLit = (RDFLiteral)$.get("RDFLiteral");
			if(rdfLit.analize()){
				this.expr = rdfLit.expr;
			}else return false;
		}else if($.current.token == Token.INTEGER || $.current.token == Token.DECIMAL|| $.current.token == Token.DOUBLE || $.current.token == Token.INTEGER_POSITIVE
				|| $.current.token == Token.INTEGER_NEGATIVE || $.current.token == Token.DECIMAL_POSITIVE || $.current.token == Token.DECIMAL_NEGATIVE || $.current.token == Token.DOUBLE_POSITIVE
				|| $.current.token == Token.DOUBLE_NEGATIVE){
			NumericLiteral numLit = (NumericLiteral)$.get("NumericLiteral");
			if(numLit.analize()){
				this.expr = numLit.expr;
			}else return false;
		}else if($.current.token == Token.TRUE || $.current.token == Token.FALSE){
			BooleanLiteral blit = (BooleanLiteral)$.get("BooleanLiteral");
			if(blit.analize()){
				this.expr = blit.expr;
			}else return false;
		}else if($.current.token == Token.VAR1 || $.current.token == Token.VAR2){
			Var var = (Var)$.get("Var");
			if(var.analize()){
				this.expr = var.expr;
			}else{
				return false;
			}
		}
		return false;
		  
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("UnaryExpression").FOLLOWS()
				});
	}
}