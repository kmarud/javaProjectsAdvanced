
public class ColorBlue implements TextDecorator {

	@Override
	public String decorate(String s) {
		s = s.replace("<i>", "<i style=\"color:blue;\">");
		return s;
	}
}
