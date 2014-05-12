package sintax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import lexic.Token;

public class MistakeLog {
	
	private static java.lang.String message="";
	private static Token spected_position= null;
	public static HashSet<Token> spected = new HashSet<Token>();
	
	public static ArrayList<java.lang.String> mistakesLog= new ArrayList<java.lang.String>();
	
	
	public static boolean driveTo ( ArrayList<Token>follows ) throws IOException
	{
		java.lang.String msg="";
		
		if (spected_position != $.current.token )
			message="";
		
		while(true)
		{
			for ( Token FOLLOW : follows)
				if(FOLLOW == $.current.token )
				{
					if(msg.isEmpty()){
						String str = "";
						if(spected.size() > 0){
							str+="\nSe esperaba uno de los siguientes Tokens: \n";
							for(Token t: spected){
								str+= "   \"" + t.name() + "\"\n";
							}
						}
						spected.clear();
						mistakesLog.add( "Se encontró <EOF>" +message + str);
					}
					else{
						String str = "";
						if(spected.size() > 0){
							str+="\nSe esperaba uno de los siguientes Tokens: \n";
							for(Token t: spected){
								str+= "   \"" + t.name() + "\"\n";
							}
						}
						spected.clear();
						mistakesLog.add( "Se encontró \"" +msg + "\"" +message + str);
					}
					return true;
				}
			
			if ( $.current.lexeme==null )
			{
				if( msg.endsWith(" ")) msg += $.current.token.name() + " ";
				else msg += " " + $.current.token.name() + " ";
			}
			else msg += $.current.lexeme;
			
			if($.current.token == Token.END)
				break;
			
			$.next();
		}
		
		msg="";
		return false;
	}
	
	public static void reportParent(java.lang.String parent)
	{
		java.lang.String s= mistakesLog.remove(mistakesLog.size()-1);
		mistakesLog.add(s+" en "+parent);
	}
	
	public static java.lang.String report()
	{
		java.lang.String s="";
		
		if ( mistakesLog.size()==1 )
			s+= "Se encontró " + mistakesLog.size()+ " error: \n";
		else
			s+="Se encontraron "+ mistakesLog.size() +" errores: \n";
		
		for(String str: mistakesLog){
			s+=str + "\n";
		}
//		if(spected.size() > 0){
//			s+="\n Se esperaba uno de los siguientes Tokens: \n";
//			for(Token t: spected){
//				s+= "   \"" + t.name() + "\"\n";
//			}
//		}
		
		return s;
	}
	
	
	public static void reset()
	{
		message="";
		spected_position= null;
		spected.clear();
		mistakesLog.clear();
	}
	
}
