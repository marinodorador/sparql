package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.datatypes.BaseDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueString;

import lexic.Token;

public class RDFLiteral extends Production{
	public Node  node = null;
	public String type = null;
	public Expr expr = null;
	/**
	 * @author Romina
	 *
	 * RDFLiteral = String ( LANGTAG | ( '^^' IRIref ) )?
	 * FIRSTS: String.FIRSTS
	 *  
	 * @throws IOException 
	 */
	
	public boolean process() throws IOException{
		_String s = (_String) $.get("_String");
		
		if(!s.analize()) return false;
		
		this.expr = new NodeValueString(s.val);
		switch($.current.token){
			case TYPE:{
				
				IRIref iriRef = (IRIref) $.get("IRIref");
				if(!iriRef.analize()) return false;
				
				this.node = NodeFactory.createLiteral(s.val, new BaseDatatype(iriRef.val));
				this.expr = new NodeValueString(
						s.val, 
						node
				);
				$.next();
			}
				
			case LANGTAG: {
				String lang = $.current.lexeme;
				this.node = NodeFactory.createLiteral(s.val, lang, new BaseDatatype("http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dt-string"));
				this.expr = new NodeValueString(
						s.val, 
						node
				);
				$.next();
			}
		}

	return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("PrimaryExpression").FOLLOWS();
	}
}