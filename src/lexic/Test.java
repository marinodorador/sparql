package lexic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Test {
	public static void main(String args[]) throws IOException{
		InputStream inputStream = new FileInputStream("text.ssql");
		Reader      reader      = new InputStreamReader(inputStream,"Cp1252");
		Alex alex = new Alex(reader);
		Symbol t = alex.yylex();
		while(t.token != Token.END){
			System.out.println(t.toString());
			t = alex.yylex();
		}
					
	}
}
