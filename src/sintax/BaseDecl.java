package sintax; 
import lexic.Alex;

public class BaseDecl extends Analizer{
	
	public BaseDecl(Alex alex){
		this.alex = alex;
	}

	public boolean analize(){
		return true;
	}
}