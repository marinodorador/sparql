package sintax; 

import java.io.IOException;

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
	public Token[] initFIRSTS() throws IOException {
		return construct(new Token[][]{
				get("BaseDecl").FIRSTS(),
				get("PrefixDecl").FIRSTS(),
				this.FOLLOWS(),
				});
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return new Token[]{ Token.SELECT };
	}
}