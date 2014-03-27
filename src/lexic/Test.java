package lexic;

import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException{
		
		Alex alex = new Alex(new FileReader("text.ssql"));
		while(true){
			Symbol t = alex.yylex();
			System.out.println(t.toString());
		}
	}
}
