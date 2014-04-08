package sintax; 

import java.io.IOException;

import lexic.Token;

public class BuiltInCall extends Production{
	
	/**
	 * @author Romina
	 *
	 *	 BuiltInCall =  ( 'STR' | 'LANG' |  'DATATYPE' | 'isIRI' | 'isURI' | 'isBLANK' | 'isLITERAL' ) '(' Expression ')' 
	 *				|	( 'LANGMATCHES' | 'sameTerm' ) '(' Expression ',' Expression ')'
	 *				|	'BOUND' '(' Var ')'
	 *				|	RegexExpression
	 *			
	 *	FIRSTS : { 'STR' | 'LANG' | 'LANGMATCHES' | 'DATATYPE' | 'BOUND' | 'sameTerm' | 'isIRI' |	'isURI'
	 *			 | 'isBLANK' |	'isLITERAL' | RegexExpression }
	 *		 
	 *		 
	 * @throws IOException 
	 *  
	 */
	
	public boolean process() throws IOException{
		
		switch($.current.token){

		case STR:
		case LANG:
		case DATATYPE:
		case ISIRI:
		case ISURI:
		case ISBLANK:
		case ISLITERAL:
		{
			$.next(); if ( $.current.token != Token.LEFT_PARENTH ) return false;
			
			$.next(); if ( ! $.analize("Expression") ) return false;
			
			$.next();
			if ( $.current.token == Token.RIGTH_PARENTH )
			{
				$.next();
				return true;
			}
			
			return false;
		}
		
		case LANGMATCHES:
		case SAMETERM:
		{
			$.next(); if ( $.current.token != Token.LEFT_PARENTH ) return false;
			
			$.next(); if ( ! $.analize("Expression") ) return false;
			
			$.next(); if ( $.current.token != Token.COMMA ) return false;
			
			$.next(); if ( ! $.analize("Expression") ) return false;
			
			$.next();
			if ( $.current.token == Token.RIGTH_PARENTH )
			{
				$.next();
				return true;
			}
			
			return false;
		}
			
		case BOUND:
		{
			$.next(); if ( $.current.token != Token.LEFT_PARENTH ) return false;
			
			$.next(); if ( ! $.analize("Var") ) return false;
			
			$.next();
			if ( $.current.token == Token.RIGTH_PARENTH )
			{
				$.next();
				return true;
			}
			
			return false;
		}
			
		}
		
		return $.analize("RegexExpression");
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("PrimaryExpression").FOLLOWS();
	}
}