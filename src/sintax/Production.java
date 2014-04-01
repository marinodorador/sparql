package sintax;
import java.io.IOException;
import java.util.HashMap;
/**
 * 
 * @author esteban
 *
 * Abstract class to productions.
 */
public abstract class Production{

	public HashMap<String,Object> attrs = new HashMap<String,Object>();
	

	
	public abstract boolean analize() throws IOException;

	public Object getAttr(String key){
		return attrs.get(key);
	}

	public void setAttr(String key, Object o){
		attrs.put(key, o);
	}

	public boolean attrExist(String key){
		return attrs.containsKey(key);
	}
	
}