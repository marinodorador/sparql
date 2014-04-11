package sintax; 

import java.io.IOException;

import lexic.Token;

public class SelectQuery extends Production{
	/**
	 * @author Romina
	 *
	 * SelectQuery ::= 'SELECT' ( 'DISTINCT' | 'REDUCED' )? ( Var+ | '*' ) DatasetClause* WhereClause SolutionModifier
	 
	 FIRST = 'SELECT'
	 
	 Var = VAR1 | VAR2
	 DatasetClause FIRST = 'FROM'
	 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		// 'SELECT'
		if ( $.current.token == Token.SELECT )
			$.next();
		else
			return false;
		
		// ( 'DISTINCT' | 'REDUCED' )?
		if ( $.current.token == Token.DISTINCT || $.current.token == Token.REDUCED )
			$.next();
		
		// ( Var+ | '*' )
		if ( $.current.token == Token.MULT )
			$.next();
		else if ( $.analize("Var") )
			while ( $.analize("Var") ) {}
		else
			return false;
		
		// DatasetClause* WhereClause SolutionModifier
		while ($.current.token == Token.FROM)
			if ( ! $.analize("DatasetClause") )
					return false;
		
		if ( ! $.analize("WhereClause") )
			return false;
		
		if ( ! $.analize("SolutionModifier") )
			return false;
		
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		return new Token[]{Token.END};
	}
}