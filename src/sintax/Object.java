package sintax; 

import java.io.IOException;

public class Object extends Production{

	@Override
	public boolean analize() throws IOException {
		return $.analize("GraphNode");
	}

}