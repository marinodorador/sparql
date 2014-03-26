package sintax;
public class ${
	public static boolean analize(java.lang.String name, Analizer subject){
		Analizer analizer = null;
		
		try {
			analizer = (Analizer) Class.forName(name).newInstance();
			analizer.init(subject.alex, subject.attrs, subject.current);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return analizer.analize();
	}
}