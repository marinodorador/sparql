package lexic;


/**
 * Symbol 
 **/
public class Symbol {
	public Token token;
	public String lexeme;
	public Symbol(Token token){
		this.token = token;
	}
	public Symbol(Token token, String lexeme){
		this.token = token;
		this.lexeme = lexeme;
	}
	public String toString(){
		return "("+token+","+lexeme+")";
		
	}
}
