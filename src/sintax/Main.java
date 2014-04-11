package sintax;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JOptionPane;

import lexic.Alex;

public class Main {
	public static void main(java.lang.String args[]) throws IOException{
		InputStream inputStream = new FileInputStream("text.ssql");
		Reader      reader      = new InputStreamReader(inputStream,"Cp1252");
		$.alex = new Alex(reader);
	       
	    $.next(); 	
	       
		Query analizer = new Query();
				
		if(analizer.analize() && MistakeLog.mistakesLog.isEmpty()){
			JOptionPane.showMessageDialog(null, "La expresion es correcta.");
		}else{
			JOptionPane.showMessageDialog(null, MistakeLog.report() );
		}
		
	}
}
