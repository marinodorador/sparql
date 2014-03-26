package sintax; 
import lexic.Alex;

public class BooleanLiteral extends Analizer{
	
	public BooleanLiteral(Alex alex){
		this.alex = alex;
	}

	public boolean analize(){
		return true;
	}
}