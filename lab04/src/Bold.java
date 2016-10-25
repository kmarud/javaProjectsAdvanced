
public class Bold implements TextDecorator{

	@Override
	public String decorate(String s) {
		s = s.replaceAll("<\\w+>", "<b>");
		s = s.replaceAll("<\\/\\w+>", "</b>");
		return s;
	}
}
