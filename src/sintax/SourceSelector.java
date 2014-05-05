package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * SourceSelector ::=   IRIref
 * FIRST : IRI_REF
 **/
public class SourceSelector extends Production{
	public com.hp.hpl.jena.query.Query  query;
	public String uri = null;
	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		if($.current.token == Token.IRI_REF)
		{
			IRIref ir = (IRIref)$.get("IRIref");
			boolean result = ir.analize();
			uri = ir.val;
			return result;
		}else{
			return false;
		}

	}

	@Override
	public Token[] FOLLOWS() throws IOException{
		// TODO Auto-generated method stub
		
		
		return construct(new Token[][]{
				get("DatasetClause").FOLLOWS()
				});
	}
}