package sintax;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import lexic.Token;
/**
 * 
 * @author esteban
 *
 * Abstract class to productions.
 */
public abstract class Production{

	public HashMap<_String,Object> attrs = new HashMap<_String,Object>();
	
	abstract boolean process() throws IOException;	

	public Object getAttr(_String key){
		return attrs.get(key);
	}

	public void setAttr(_String key, Object o){
		attrs.put(key, o);
	}

	public boolean attrExist(_String key){
		return attrs.containsKey(key);
	}
	
	/*
	 * CLASE PRINCIPAL PARA EJECUTAR EL ANALISIS
	 * 1. checks FIRSTS
	 * 2. calls process, which is individually built in each instance
	 * 3. if process ended with error, advances to a FOLLOW
	 */
	public boolean analize() throws IOException{
		
		System.out.println(this.getClass().getSimpleName());
		boolean ans=false;
		
		int trace= MistakeLog.mistakesLog.size();
		Token current= $.current.token;
		
		// 1 checks FIRSTS
		for ( Token FIRST : FIRSTS() )
		{
			if(current == FIRST)
			{
				//2 calls process, which is individually built in each instance
				ans= process();
				break;
			}
		}
		
		//3 if process ended with error, advances to a FOLLOW
		if ( ans == false )
		{
			if( current == $.current.token )
				return MistakeLog.spected(this.getClass().getSimpleName());
			
			ans = MistakeLog.driveTo(FOLLOWS());
		}
		
		if ( MistakeLog.mistakesLog.size() != trace )
			MistakeLog.reportParent(""+this.getClass().getSimpleName());
			
		
		
		return ans;
	}
	
	abstract ArrayList<Token> FIRSTS() throws IOException;
	abstract ArrayList<Token> FOLLOWS() throws IOException;
	
	/*
	 * AUXILIAR METHODS
	 */
	/*
	 * get
	 * method returns a named production instance
	 */
	public Production get(java.lang.String name) throws IOException{
		Production p = null;
		
		try {
			p = (Production) Class.forName("sintax."+name).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
			
		return p;
	}
}