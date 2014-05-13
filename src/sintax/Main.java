package sintax;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import lexic.Alex;

public class Main {
	public static void main(java.lang.String args[]) throws IOException{
		InputStream inputStream = new FileInputStream("text.ssql");
		Reader      reader      = new InputStreamReader(inputStream,"Cp1252");
		$.alex = new Alex(reader);
	       
	    $.next(); 	
	       
		Query analizer = new Query();
				
		if(analizer.analize() && MistakeLog.mistakesLog.isEmpty()){
			{
				System.out.println("La expresion es correcta.");
				
				QueryExecution qe = QueryExecutionFactory.create(Query.query);
				
				ResultSet results = qe.execSelect();
				ResultSetFormatter.out(System.out, results, Query.query) ;
			}
		}else{
			{
				System.out.println("La expresion es incorrecta.\n\nLOG\n"+MistakeLog.report() );
			}
		}
		
		
		
	}
}
