package sintax;
import java.io.IOException;
import java.util.HashMap;

import lexic.Token;
/**
 * 
 * @author esteban
 *
 * Abstract class to productions.
 */
public abstract class Production{

	public HashMap<String,Object> attrs = new HashMap<String,Object>();
	
	abstract boolean process() throws IOException;	

	public Object getAttr(String key){
		return attrs.get(key);
	}

	public void setAttr(String key, Object o){
		attrs.put(key, o);
	}

	public boolean attrExist(String key){
		return attrs.containsKey(key);
	}
	
	public boolean analize()
	{
		boolean ans=false;
		int trace= MistakeLog.mistakesLog.size();
		
		try{
			
			ans= process();
			
			if ( ans == false )
			{
				Token[] FOLLOWS= this.FOLLOWS;
				if( FOLLOWS == null )
					FOLLOWS= FOLLOWS();
				
				if ( FOLLOWS != null )
					ans = MistakeLog.driveTo(FOLLOWS);
			}
			
			if ( MistakeLog.mistakesLog.size() != trace )
				MistakeLog.reportParent(""+this.getClass().getSimpleName());
			
		}catch(Exception ex){}
		
		return ans;
	}
	
	/*
	 * FOLLOWS
	 */
	public Token[] FOLLOWS;
	public abstract Token[] FOLLOWS() throws IOException;
	
	/*
	 * AUXILIAR METHODS
	 */
	/*
	 * get
	 * method returns a named production instance
	 */
	public static Production get(java.lang.String name) throws IOException{
		Production p = null;
		
		try {
			p = (Production) Class.forName("sintax."+name).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
			
		return p;
	}
	
	/*
	 * construct
	 * method combines multiple arrays of tokens in a single one
	 */
	public static Token[] construct( Token[][] tokens )
	{
		int size=1;
		
		for ( int i=0 ; i<tokens.length; i++ )
			size += tokens[i].length-1;
		
		Token[] ans= new Token[size];
		
		int index=0;
		for ( int i=0 ; i<tokens.length; i++ )
		{
			for ( int ii=0 ; ii<tokens[i].length ; ii++ )
			{
				ans[index]=tokens[i][ii];
				index++;
			}
		}
		
		return ans;
	}
}