package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * PropertyListNotEmpty	::=  Verb ObjectList ( ';' ( Verb ObjectList )? )*
 */
public class PropertyListNotEmpty extends Production{

	@Override
	public boolean analize() throws IOException {
		
		if($.analize("Verb")){
			if($.analize("ObjectList")){
				while($.current.token == Token.SEMI){
					$.next();
					if($.analize("Verb")){
						if($.analize("ObjectList")){
						}else{
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}

}