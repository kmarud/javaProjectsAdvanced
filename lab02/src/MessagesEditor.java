import java.awt.*;
import java.beans.*;


public class MessagesEditor extends PropertyEditorSupport {
	public Component getCustomEditor() {
		return new MessagesEditorPanel(this);
	}

	public boolean supportsCustomEditor() {
		return true;
	}

	public String getJavaInitializationString() {
		String[] messages = (String[]) getValue();

		StringBuffer s = new StringBuffer();
		s.append("new String[]{");
		for (int i = 0; i < messages.length - 1; i++) {
			s.append("\"");
			s.append(messages[i]);
			s.append("\",");
		}
		s.append("\"");
		s.append(messages[messages.length - 1]);
		s.append("\"}");
		return s.toString();
	}

	public boolean isPaintable() {
		return false;
	}

	public String getAsText() {
		String[] messages = (String[]) getValue();
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < messages.length - 1; i++) {
			s.append(messages[i]);
			s.append(";");
		}
		s.append(messages[messages.length - 1]);
		return s.toString();
	}

	public void setAsText(String s) {
		String[] messages = s.split(";");
		setValue(messages);
	}
	
}
