package sintax;

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;

public class MistakeLog {
	
	public static ArrayList<java.lang.String> mistakesLog= new ArrayList<java.lang.String>();
	
	public static boolean driveTo ( Token[]follows ) throws IOException
	{
		java.lang.String msg="";
		
		while($.current.token != Token.END)
		{			
			for ( int i=0 ; i<follows.length ; i++ )
				if(follows[i] == $.current.token )
				{
					mistakesLog.add( msg );
					return true;
				}
			
			if ( $.current.lexeme==null )
				msg += $.current.token.name() + " ";
			else
				msg += $.current.lexeme + " ";
			
			
			$.next();
		}
		
		return false;
	}
	
	public static void reportParent(java.lang.String parent)
	{
		java.lang.String s= mistakesLog.remove(mistakesLog.size()-1);
		mistakesLog.add(s+" @ "+parent);
	}
	
	public static java.lang.String report()
	{
		java.lang.String s="";
		
		if ( mistakesLog.size()==1 )
			s+=mistakesLog.size() +" mistake was found: ";
		else
			s+=mistakesLog.size() +" mistakes were found: ";
		
		for ( java.lang.String line: mistakesLog )
			s+="\n"+line;
		
		return s;
	}
	
}
