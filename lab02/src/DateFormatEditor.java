import java.beans.*;


public class DateFormatEditor extends PropertyEditorSupport {
	public boolean supportsCustomEditor() {
		return false;
	}
	
	public String getJavaInitializationString() {
		Integer value = (Integer) getValue();
		return value.toString();
	}
	
	public String getAsText() {
		int value = ((Integer) getValue()).intValue();
		return options[value];
	}

	public void setAsText(String s) {
		for (int i = 0; i < options.length; i++) {
			if (options[i].equals(s)) {
				setValue(new Integer(i));
				return;
			}
		}
	}

	public String[] getTags() {
		return options;
	}

	private String[] options = { "DD/MM/YYYY", "MM/DD/YYYY", "DNAME_DD_MM_YYYY"};
}
