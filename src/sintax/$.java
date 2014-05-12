package sintax;

import java.io.IOException;

import lexic.Alex;
import lexic.Symbol;

public class ${
	public static Alex alex;
	public static Symbol current;
	
	public static boolean analize(java.lang.String name) throws IOException{
		System.out.println(name);
		Production p = null;
		
		try {
			p = (Production) Class.forName("sintax."+name).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return p.analize();
	}
	
	public static Production get(java.lang.String name) throws IOException{
			Production p = null;
		
		try {
			p = (Production) Class.forName("sintax."+name).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return p;
	}
	
	public static void next() throws IOException{
		$.current = $.alex.yylex();
		//System.out.println($.current);
	}
}