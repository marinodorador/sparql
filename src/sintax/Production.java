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
				
		ans= process();
		
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
	
	/*
	 * FIRSTS
	 */
	private Token[] FIRSTS;
	abstract Token[] initFIRSTS() throws IOException;
	public Token[] FIRSTS()
	{
		try{
			if( FIRSTS == null )
				FIRSTS= initFIRSTS();
		}catch(Exception ex){}
		
		if( FIRSTS == null )
			FIRSTS= new Token[]{};
		
		return FIRSTS;
	}
	
	/*
	 * FOLLOWS
	 */
	private Token[] FOLLOWS;
	abstract Token[] initFOLLOWS() throws IOException;
	public Token[] FOLLOWS()
	{
		try{
			if( FOLLOWS == null )
				FOLLOWS= initFOLLOWS();
		}catch(Exception ex){}
		
		if( FOLLOWS == null )
			FOLLOWS= new Token[]{};
		
		return FOLLOWS;
	}
	
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