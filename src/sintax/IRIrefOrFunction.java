package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.graph.Node_URI;
import com.hp.hpl.jena.sparql.expr.E_Function;
import com.hp.hpl.jena.sparql.expr.E_IRI;
import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.ExprList;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueNode;

import lexic.Token;

/**
 * 
 * @author esteban
 *  IRIrefOrFunction ::=  IRIref ArgList?
 * 	FIRST(IRIrefOrFunction) = {IRI_REF, PNAME_LN, PNAME_NS}
 * 	FIRST(ArgList?) = {NIL, '(' }
 * 	FOLLOW(ArgList?) = {*, /,+,-INTEGER_POSITIVE,DECIMAL_POSITIVE,DOUBLE_POSITIVE,INTEGER_NEGATIVE
 *						,DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, =, !=, <, > , <=, >=,
 *						&&, ||, COMMA,')'}
 */
public class IRIrefOrFunction extends Production{
	public Expr expr = null;
	public boolean process() throws IOException{
		if($.current.token == Token.IRI_REF || $.current.token == Token.PNAME_LN || $.current.token == Token.PNAME_NS){

			IRIref iref = (IRIref)$.get("IRIref");

			if(!iref.analize1()) return false;


			this.expr = new NodeValueNode(NodeFactory.createURI(iref.val));

			if($.current.token == Token.NIL || $.current.token == Token.LEFT_PARENTH){
				ArgList al = (ArgList)$.get("ArgList");
				if(!al.analize1()) return false;
				this.expr = new E_Function(iref.val,al.expr);
			}

			
			if($.current.token != Token.MULT && $.current.token != Token.DIV && $.current.token != Token.PLUS
					 && $.current.token != Token.SUB && $.current.token != Token.INTEGER_POSITIVE && $.current.token != Token.DECIMAL_POSITIVE
					 && $.current.token != Token.DOUBLE_POSITIVE && $.current.token != Token.INTEGER_NEGATIVE && $.current.token != Token.DECIMAL_NEGATIVE
					 && $.current.token != Token.LESS && $.current.token != Token.GREATER && $.current.token != Token.LET
					 && $.current.token != Token.GET && $.current.token != Token.AND && $.current.token != Token.OR
					 && $.current.token != Token.COMMA && $.current.token != Token.RIGTH_PARENTH
					) return false;

		}else{
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
			return $.get("IRIref").FOLLOWS();
		}

	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return $.get("PrimaryExpression").FOLLOWS();
	}
}