package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.query.SortCondition;

import lexic.Token;

public class OrderCondition extends Production{
	public SortCondition sortCondition = null;
	/**
	 * @author Romina
	 *
	 * OrderCondition ::= ( ( 'ASC' | 'DESC' ) BrackettedExpression )| ( Constraint | Var )
	 * 
	 * FIRSTS = 'ASC' | 'DESC' | Constraint=> '(' | Var=> VAR1 | VAR2
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		switch($.current.token){

			case ASC:{
				BrackettedExpression be = (BrackettedExpression) $.get("BrackettedExpression");
				if(!be.analize()) return false;
				sortCondition = new SortCondition(be.expr,com.hp.hpl.jena.query.Query.ORDER_ASCENDING);
				$.next();
			}
			case DESC:{
				BrackettedExpression be = (BrackettedExpression) $.get("BrackettedExpression");
				if(!be.analize()) return false;
				sortCondition = new SortCondition(be.expr,com.hp.hpl.jena.query.Query.ORDER_DESCENDING);
				$.next();
	
			}
			case LEFT_PARENTH:{
				Constraint constraint = (Constraint) $.get("Constraint");
				if(! constraint.analize()) return false;
				sortCondition = new SortCondition(constraint.expr, com.hp.hpl.jena.query.Query.ORDER_DESCENDING);
				
			}
			default:{
				 Var v = (Var) $.get("Var");
				 if(!v.analize()) return false;
				 sortCondition = new SortCondition(v.node, com.hp.hpl.jena.query.Query.ORDER_DESCENDING);
			}
		}
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.ASC );
		ans.add( Token.DESC );
		
		for ( Token t : get("Constraint").FIRSTS() )
			ans.add(t);
		for ( Token t : get("Var").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("OrderClause").FOLLOWS();
	}
}