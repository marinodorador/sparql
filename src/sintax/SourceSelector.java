package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * SourceSelector ::=   IRIref
 **/
public class SourceSelector extends Production{
	public com.hp.hpl.jena.query.Query  query;
	public String uri = null;
	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		IRIref ir = (IRIref)$.get("IRIref");
		boolean result = ir.analize();
		uri = ir.val;
		return result;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return construct(new Token[][]{
				get("DatasetClause").FOLLOWS()
				});
	}
}