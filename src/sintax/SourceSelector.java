package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * SourceSelector ::=   IRIref
 **/
public class SourceSelector extends Production{

	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		return $.analize("IRIref");
	}

	@Override
	public Token[] FOLLOWS() throws IOException{
		// TODO Auto-generated method stub
		return construct(new Token[][]{
				get("DefaultGraphClause").FOLLOWS(),
				get("NamedGraphClause").FOLLOWS()
				});
	}
}