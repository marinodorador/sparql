package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.E_Bound;
import com.hp.hpl.jena.sparql.expr.E_Datatype;
import com.hp.hpl.jena.sparql.expr.E_IsBlank;
import com.hp.hpl.jena.sparql.expr.E_IsIRI;
import com.hp.hpl.jena.sparql.expr.E_IsLiteral;
import com.hp.hpl.jena.sparql.expr.E_IsURI;
import com.hp.hpl.jena.sparql.expr.E_Lang;
import com.hp.hpl.jena.sparql.expr.E_LangMatches;
import com.hp.hpl.jena.sparql.expr.E_Regex;
import com.hp.hpl.jena.sparql.expr.E_SameTerm;
import com.hp.hpl.jena.sparql.expr.E_Str;
import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.ExprAggregator;

import lexic.Token;

public class BuiltInCall extends Production{
	public Expr expr = null;
	/**
	 * @author Romina
	 *
     *	BuiltInCall	  			::=  	'STR' '(' Expression ')' 
									|	'LANG' '(' Expression ')' 
									|	'LANGMATCHES' '(' Expression ',' Expression ')' 
									|	'DATATYPE' '(' Expression ')' 
									|	'BOUND' '(' Var ')' 
									|	'sameTerm' '(' Expression ',' Expression ')' 
									|	'isIRI' '(' Expression ')' 
									|	'isURI' '(' Expression ')' 
									|	'isBLANK' '(' Expression ')' 
									|	'isLITERAL' '(' Expression ')' 
									|	 RegexExpression
	 *			
	 *	FIRSTS : { 'STR' | 'LANG' | 'LANGMATCHES' | 'DATATYPE' | 'BOUND' | 'sameTerm' | 'isIRI' |	'isURI'
	 *			 | 'isBLANK' |	'isLITERAL' | RegexExpression }
	 *		 
	 *		 
	 * @throws IOException 
	 *  
	 */
	
	public boolean process() throws IOException{
		Expression e = (Expression)$.get("Expression");
		
		switch($.current.token){

			case STR:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( !  e.analize() ) return false;
				expr = new E_Str(e.expr);

				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    //System.out.println(expr.toString());
				return true;
			}
			case LANG:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( ! e.analize() ) return false;
				expr = new E_Lang(e.expr);
		
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    //System.out.println(expr.toString());
				return true;
			}
			case DATATYPE:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( ! e.analize() ) return false;
				expr = new E_Datatype(e.expr);
			
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    System.out.println(expr.toString());
				return true;
			}
			case ISIRI:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( ! e.analize() ) return false;
				expr = new E_IsIRI(e.expr);
			
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    //System.out.println(expr.toString());
				return true;
			}
			case ISURI:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( ! e.analize() ) return false;
				expr = new E_IsURI(e.expr);
			    System.out.println(expr.toString());
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    //System.out.println(expr.toString());
				return true;
			}
			case ISBLANK:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( ! e.analize() ) return false;
				expr = new E_IsBlank(e.expr);
			
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    //System.out.println(expr.toString());
				return true;
			}
			case ISLITERAL:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( ! e.analize() ) return false;
				expr = new E_IsLiteral(e.expr);
			
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    //System.out.println(expr.toString());
				return true;
			}
			
			case LANGMATCHES:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
				
				if ( ! e.analize() ) return false;
			
				
				if ( $.current.token != Token.COMMA ) return false;
				$.next();
				Expression e2 = (Expression)$.get("Expression");
				if ( ! e2.analize() ) return false;
				
				expr = new E_LangMatches(e.expr,e2.expr);
				
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
			    //System.out.println(expr.toString());
				return true;
			}
			case SAMETERM:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				$.next();
					
				if ( ! e.analize() ) return false;
				
				if ( $.current.token != Token.COMMA ) return false;
				$.next();
				
				Expression e2 = (Expression)$.get("Expression");
				
				if ( ! e2.analize() ) return false;
				
				expr = new E_SameTerm(e.expr, e2.expr);
		
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();

			    //System.out.println(expr.toString());
				return true;
			}
				
			case BOUND:{
				$.next(); 
				if ( $.current.token != Token.LEFT_PARENTH ) return false;
				
				$.next(); 
				
				Var v = (Var) $.get("Var");
				if ( ! v.analize() ) return false;
				this.expr = new E_Bound(v.expr);
	
				
				if ( $.current.token != Token.RIGTH_PARENTH ) return false;
				$.next();
				
			    //System.out.println(expr.toString());
				return true;
			}
			default:{
				RegexExpression re = (RegexExpression) $.get("RegexExpression");
				boolean result = re.analize();
				this.expr = re.expr;
				return result;
			}
			
		}
	
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.STR);
		ans.add(Token.LANG);
		ans.add(Token.LANGMATCHES);
		ans.add(Token.DATATYPE);
		ans.add(Token.BOUND);
		ans.add(Token.SAMETERM);
		ans.add(Token.ISIRI);
		ans.add(Token.ISURI);
		ans.add(Token.ISBLANK);
		ans.add(Token.ISLITERAL);
		ans.add(Token.REGEX);
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("PrimaryExpression").FOLLOWS();
	}
}