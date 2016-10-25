
@Decorator
public class ColorBlue {

	@Decorator
	public String pomaluj(String s) {
		s = s.replace("<i>", "<i style=\"color:blue;\">");
		return s;
	}
}
