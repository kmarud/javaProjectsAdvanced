
@Decorator
public class Bold{

	
	@Decorator
	public String decorate(String s) {
		s = s.replaceAll("<\\w+>", "<b>");
		s = s.replaceAll("<\\/\\w+>", "</b>");
		return s;
	}
	
	@Decorator
	public String removeAllSpace(String s){
		return s.replace(" ", "");
	}
}
