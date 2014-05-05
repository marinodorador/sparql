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
		BrackettedExpression bexpr = (BrackettedExpression) $.get("BrackettedExpression");
		BuiltInCall bcall = (BuiltInCall)$.get("BuiltInCall");
		IRIrefOrFunction iriRef = (IRIrefOrFunction)$.get("IRIrefOrFunction");
		RDFLiteral rdfLit = (RDFLiteral)$.get("RDFLiteral");
		NumericLiteral numLit = (NumericLiteral)$.get("NumericLiteral");
		BooleanLiteral blit = (BooleanLiteral)$.get("BooleanLiteral");
		Var var = (Var)$.get("Var");
		
		if(bexpr.analize()){
			this.expr = bexpr.expr;
		}else if(bcall.analize()){
			this.expr = bcall.expr;
		}else if(iriRef.analize()){
			this.expr = iriRef.expr;
		}else if(rdfLit.analize()){
			this.expr = rdfLit.expr;
		}else if(numLit.analize()){
			this.expr = numLit.expr;
		}else if(blit.analize()){
			this.expr = blit.expr;
		}else if(var.analize()){
			this.expr = var.expr;
		}else{
			return false;
		}
		  

		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return construct(new Token[][]{
				get("BrackettedExpression").FIRSTS(),
				get("BuiltInCall").FIRSTS(),
				get("IRIrefOrFunction").FIRSTS(),
				get("RDFLiteral").FIRSTS(),
				get("NumericLiteral").FIRSTS(),
				get("BooleanLiteral").FIRSTS(),
				get("Var").FIRSTS(),
				});
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("UnaryExpression").FOLLOWS()
				});
	}
}