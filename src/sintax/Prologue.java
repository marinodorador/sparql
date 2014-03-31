package sintax; 

import java.io.IOException;

import lexic.Token;

public class Prologue extends Production{
	/**
	 * @author Romina
	 *
	 * Prologue ::=  BaseDecl? PrefixDecl*
	 * 
	 * FIRSTS = BaseDecl => 'BASE' | PrefixDecl => 'PREFIX' | none
	 * 
	 * @throws IOException 
	 */
	public boolean analize() throws IOException{
		
		if ( $.current.token == Token.BASE )
		{
			if ( ! $.analize("BaseDecl") )
				return false;
		}
		
		while ( $.current.token == Token.PREFIX )
		{
			if ( ! $.analize("PrefixDecl") )
				return false;
		}
		
		return true;
	}
}