package sintax;

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;

public class MistakeLog {
	
	private static java.lang.String message="";
	private static Token spected_position= null;
	
	public static ArrayList<java.lang.String> mistakesLog= new ArrayList<java.lang.String>();
	
	public static boolean driveTo ( Token[]follows ) throws IOException
	{
		java.lang.String msg="";
		
		if (spected_position != $.current.token )
			message="";
		
		while(true)
		{
			for ( int i=0 ; i<follows.length ; i++ )
				if(follows[i] == $.current.token )
				{
					if(msg.isEmpty())
						mistakesLog.add( "FOUND nothing" +message+"\n" );
					else
						mistakesLog.add( "FOUND\n\"" +msg + "\"" +message+"\n" );
					return true;
				}
			
			if ( $.current.lexeme==null )
			{
				if( msg.endsWith(" "))
					msg += $.current.token.name() + " ";
				else
					msg += " " + $.current.token.name() + " ";
			}
			else
				msg += $.current.lexeme;
			
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
		mistakesLog.add(s+" at "+parent);
	}
	
	public static java.lang.String report()
	{
		java.lang.String s="";
		
		if ( mistakesLog.size()==1 )
			s+=mistakesLog.size() +" mistake was found: ";
		else
			s+=mistakesLog.size() +" mistakes were found: ";
		
		for ( java.lang.String line: mistakesLog )
			s+="\n------------------------------\n"+line;
		
		return s;
	}
	
	public static boolean spected( java.lang.String msg )
	{
		if ( spected_position==$.current.token )
			message+= " / " + msg;
		else
			message= "\n\nSPECTED\n" + msg;
		spected_position=$.current.token;
		
		return false;
	}
	
	public static void reset()
	{
		message="";
		spected_position= null;
		mistakesLog.clear();
	}
	
}
