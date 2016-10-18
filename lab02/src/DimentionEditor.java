import java.beans.*;


public class DimentionEditor extends PropertyEditorSupport {
	public boolean supportsCustomEditor() {
		return false;
	}
	
	public String getJavaInitializationString() {
		Integer[] values = (Integer[]) getValue();
		
		StringBuffer s = new StringBuffer();
		s.append("new Integer[]{");
		s.append(values[0]);
		s.append(",");
		s.append(values[1]);
		s.append("}");
		return s.toString();

	}
	
	public String getAsText() {
		Integer[] values = (Integer[]) getValue();
		
		StringBuffer s = new StringBuffer();
		s.append(values[0]);
		s.append(",");
		s.append(values[1]);
		return s.toString();
	}

	public void setAsText(String s) {
		String[] messages = s.split(",");
		int w = Integer.parseInt(messages[0]);
		int h = Integer.parseInt(messages[1]);
		setValue(new Integer[]{h,w});
	}

}

