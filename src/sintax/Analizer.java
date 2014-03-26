package sintax;
import java.util.HashMap;
import lexic.Alex;
import lexic.Symbol;
public abstract class Analizer{
	public Alex alex;

	public HashMap<String,Object> attrs = new HashMap<String,Object>();
	
	public Symbol current;
	
	public abstract boolean analize();

	public Object getAttr(String key){
		return attrs.get(key);
	}

	public void setAttr(String key, Object o){
		attrs.put(key, o);
	}

	public boolean attrExist(String key){
		return attrs.containsKey(key);
	}
	
	public void init(Alex alex, HashMap<String,Object> attrs, Symbol current){
		this.alex = alex;
		this.attrs = attrs;
		this.current = current;
	}
}