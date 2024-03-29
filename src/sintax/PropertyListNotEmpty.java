package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;

import lexic.Token;
/*
 * PropertyListNotEmpty	::=  Verb ObjectList ( ';' ( Verb ObjectList )? )*
 */
public class PropertyListNotEmpty extends Production{
	public ArrayList<Triple> triples = new ArrayList<Triple>();
	public Node subject = null;
	@Override
	public boolean process() throws IOException {
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
				return true;
			
		}
		return false;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("Verb").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("TriplesSameSubject").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("PropertyList").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}