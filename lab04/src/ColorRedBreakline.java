@Decorator
public class ColorRedBreakline  {

	public String decorate(String s) {
		s = s.replace("<i>", "<i style=\"color:red;\"><br>");
		return s;
	}
}
