
public class ColorRedBreakline implements TextDecorator {

	@Override
	public String decorate(String s) {
		s = s.replace("<i>", "<i style=\"color:red;\"><br>");
		return s;
	}
}
