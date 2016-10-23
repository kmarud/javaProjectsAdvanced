
public class Bold {
	public String witaj(String name){
		name = name.replaceAll("<\\w+>", "<b>");
		name = name.replaceAll("<\\/\\w+>", "</b>");
		return name; 
	}
}
