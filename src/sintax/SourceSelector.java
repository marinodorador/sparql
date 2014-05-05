package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("IRIref").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return get("DatasetClause").FOLLOWS();
	}
}