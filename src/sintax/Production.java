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
	
	public boolean analize() throws IOException{
		System.out.println(this.getClass().getSimpleName());
		boolean ans=false;
		
		int trace= MistakeLog.mistakesLog.size();
		Token current= $.current.token;
		

			ans= process();
			
			if ( ans == false )
			{
				if( current == $.current.token )
					return MistakeLog.spected(this.getClass().getSimpleName());
					
				
				if( FOLLOWS == null )
					FOLLOWS= FOLLOWS();
				
				if ( FOLLOWS != null )
					ans = MistakeLog.driveTo(FOLLOWS);
			}
			
			if ( MistakeLog.mistakesLog.size() != trace )
				MistakeLog.reportParent(""+this.getClass().getSimpleName());
			
		
		
		return ans;
	}
	
	/*
	 * FOLLOWS
	 */
	private Token[] FOLLOWS;
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
			size += tokens[i].length;
		
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