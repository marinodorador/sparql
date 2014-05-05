package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * ValueLogical	::= RelationalExpression
 */
public class ValueLogical extends Production{
	Expr expr = null;
	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		RelationalExpression re = (RelationalExpression) $.get("RelationalExpression");
		boolean result = re.analize();
		this.expr = re.expr;
		return result;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("RelationalExpression").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.AND );
		
		for ( Token t : get("ConditionalAndExpression").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}

}