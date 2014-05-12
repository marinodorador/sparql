package sintax; 
import java.io.IOException;
import java.util.ArrayList;

import static lexic.Token.*;
import lexic.Token;

/**
 * @author esteban
 *
 * Fisrts: {'BASE', 'PREFIX', 'SELECT'}
 */

public class Query extends Production{
	
	public static com.hp.hpl.jena.query.Query query = null;
	
	public boolean process() throws IOException{
		if($.current.token == BASE || $.current.token == PREFIX || $.current.token == SELECT){
			Prologue p =  (Prologue)$.get("Prologue");
			SelectQuery sq =(SelectQuery)$.get("SelectQuery");
			
			if(p.analize()) query = new com.hp.hpl.jena.query.Query(p.prologue);
			
			if(query == null){
				query = new com.hp.hpl.jena.query.Query();
			}
	
			if(!sq.analize()) return false;
			
			
			
			query.setQueryPattern(sq.queryPattern);
			query.setDistinct(sq.distinct);
		 	query.setReduced(sq.reduced);
		 	query.setQueryResultStar(sq.queryResultStart);

		 	
		 	//Add vars
		 	for(int i = 0;i<sq.resultVars.size();i++){
		 		query.addResultVar(sq.resultVars.get(i));
		 	}
		
		 	if(sq.isSelect)query.setQuerySelectType();
		 	
		 	
		 	//Add graph uris
		 	for(int i = 0 ; i< sq.graphUris.size(); i++) 
		 		query.addGraphURI(sq.graphUris.get(i));
		 	
		 	//Add named graph uris
		 	for(int i = 0 ; i< sq.namedGraphUris.size(); i++) 
		 		query.addNamedGraphURI(sq.graphUris.get(i));
		 	
			$.next();
			if($.current.token != END) return false;
			
		}else{
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("Prologue").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.END);
		
		return ans;
	}
}