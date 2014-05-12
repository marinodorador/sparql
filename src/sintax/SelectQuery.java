package sintax; 

import java.io.IOException;
import java.util.ArrayList;
import com.hp.hpl.jena.sparql.syntax.Element;

import lexic.Token;

public class SelectQuery extends Production{
	public Element queryPattern;
	public boolean distinct = false;
	public boolean reduced = false;
	public boolean queryResultStart = false;
	public boolean isSelect = false;
	public ArrayList<String> graphUris = new ArrayList<String>();
	public ArrayList<String> namedGraphUris = new ArrayList<String>();
	public ArrayList<String> resultVars = new ArrayList<String>();
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
		if ( $.current.token != Token.SELECT ) return false;
			isSelect = true;
			$.next();
		
		// ( 'DISTINCT' | 'REDUCED' )?
		if ( $.current.token == Token.DISTINCT){
			distinct = true;
			$.next();
		}else if ($.current.token == Token.REDUCED ){
			reduced = true;
			$.next();
		}
		
		Var var = (Var)$.get("Var");
		// ( Var+ | '*' )
		if ( $.current.token == Token.MULT ){
			queryResultStart = true;
			$.next();
		}else if ( var.analize() ){
			resultVars.add(var.value);
			while (var.analize()) {
				resultVars.add(var.value);
			}
		}else
			return false;
		

		
		// DatasetClause* WhereClause SolutionModifier
		while ($.current.token == Token.FROM){
		   DatasetClause dsc = (DatasetClause) $.get("DatasetClause");
		   
		   
			if ( ! dsc.analize() )return false;
			if(!dsc.isNamed)
				graphUris.add(dsc.uri);
			else
				namedGraphUris.add(dsc.uri);
		}
		WhereClause where = (WhereClause) $.get("WhereClause");
		
		if ( ! where.analize()) return false;
		
		queryPattern = where.element;
		SolutionModifier sm = (SolutionModifier) $.get("SolutionModifier");
		if ( ! sm.analize()) return false;
		
		
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.SELECT);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.END);
		
		return ans;
	}
}