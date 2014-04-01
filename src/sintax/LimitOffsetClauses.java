package sintax; 

import java.io.IOException;

public class LimitOffsetClauses extends Production{

	@Override
	public boolean analize() throws IOException {
		if($.analize("LimitClause")){
			if ($.analize("OffsetClause")){
				return true;
			}
			return true;
		}else if ($.analize("OffsetClause")){
			if($.analize("LimitClause")) return true;
			return true;
		}
		return false;
	}

}