package sintax; 

import java.io.IOException;

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
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.AND },
				get("ConditionalAndExpression").FOLLOWS()
				});
	}

}