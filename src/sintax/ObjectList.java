package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.Node;

import lexic.Token;

public class ObjectList extends Production{
	public ArrayList<Node> objects = new  ArrayList<Node>();
	/**
	 * @author Romina
	 *
	 * ObjectList = Object ( ',' Object )*
	 * FIRST = Object
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		
		sintax.Object o = (sintax.Object)$.get("Object");
		
		if ( o.analize() )
		{
			objects.add(o.node);
			
			o = (sintax.Object)$.get("Object");
			while ( $.current.token == Token.COMMA )
			{
				$.next();
				if ( ! o.analize())
					objects.add(o.node);
					return false;
			}
			return true;
		}
		
		return false;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("Object").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.SEMI );
		
		for ( Token t : get("PropertyListNotEmpty").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}