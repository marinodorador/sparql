package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;

import lexic.Token;

public class Prologue extends Production{
	com.hp.hpl.jena.sparql.core.Prologue prologue = new com.hp.hpl.jena.sparql.core.Prologue() ;
	
	/**
	 * @author Romina
	 *
	 * Prologue ::=  BaseDecl? PrefixDecl*
	 * 
	 * FIRSTS = BaseDecl => 'BASE' | PrefixDecl => 'PREFIX' | none
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		prologue.setPrefixMapping(new PrefixMappingImpl());
		
		if ( $.current.token == Token.BASE ){
			BaseDecl base = (BaseDecl)$.get("BaseDecl");
			if ( ! base.analize()) return false;
			prologue.setBaseURI(base.baseUri);
		}
		
		while ( $.current.token == Token.PREFIX )
		{
			PrefixDecl prefixDecl = (PrefixDecl) $.get("PrefixDecl");
			if ( ! prefixDecl.analize() )return false;
			PrefixMappingImpl pm = (PrefixMappingImpl)prologue.getPrefixMapping();
			pm.setNsPrefix(prefixDecl.prefix, prefixDecl.uri);
		}
		
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("BaseDecl").FIRSTS() )
			ans.add(t);
		for ( Token t : get("PrefixDecl").FIRSTS() )
			ans.add(t);
		for ( Token t : this.FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("SelectQuery").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
}