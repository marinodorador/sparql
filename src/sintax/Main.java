package sintax;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import lexic.Alex;

public class Main {
	public static void main(java.lang.String args[]) throws IOException{
		InputStream inputStream = new FileInputStream("text.ssql");
		Reader      reader      = new InputStreamReader(inputStream,"Cp1252");
		$.alex = new Alex(reader);
	       
	    $.next(); 	
	       
		Query analizer = new Query();
			
		if(analizer.analize()){
			System.out.println("La expresión es correcta.");
		}else{
			System.out.println("La expresión es incorrecta.");
		}
		
	}
}
