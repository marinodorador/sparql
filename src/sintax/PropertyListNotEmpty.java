package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;

import lexic.Token;
/*
 * PropertyListNotEmpty	::=  Verb ObjectList ( ';' ( Verb ObjectList )? )*
 * FIRST: {VAR1, VAR2, IRIref, 'a',PNAME_LN,PNAME_NS}
 */
public class PropertyListNotEmpty extends Production{
	public ArrayList<Triple> triples = new ArrayList<Triple>();
	public Node subject = null;
	@Override
	public boolean process() throws IOException {
		boolean first = $.current.token == Token.VAR1 ||  $.current.token ==Token.VAR2 
				||  $.current.token == Token.IRI_REF ||  $.current.token == Token.A ||  $.current.token == Token.PNAME_LN ||  $.current.token == Token.PNAME_NS   ;
		if(first){
			Verb v = (Verb) $.get("Verb");
			if(v.analize()){
				ObjectList ol  = (ObjectList) $.get("ObjectList");

				if(ol.analize()){
					for(int i = 0;i<ol.objects.size();i++){
						triples.add(new Triple(subject,v.node, ol.objects.get(i)));
					}
				}

				v = (Verb) $.get("Verb");
				ol = (ObjectList) $.get("ObjectList");

				while($.current.token == Token.SEMI){
					v = (Verb) $.get("Verb");
					ol = (ObjectList) $.get("ObjectList");
					$.next();
					if(first){
						if(v.analize()){
							if(ol.analize()){
								for(int i = 0;i<ol.objects.size();i++){
									triples.add(new Triple(subject,v.node, ol.objects.get(i)));
								}
							}else{
								return false;
							}
						}
					}
				}
				return true;

			}
			return false;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("TriplesSameSubject").FOLLOWS(),
				get("PropertyList").FOLLOWS()
				});
	}
}