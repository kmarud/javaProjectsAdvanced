
import java.beans.*;

public class WidgetBeanInfo extends SimpleBeanInfo {


	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor dateFormatDescriptor = new PropertyDescriptor("dateFormat", Widget.class);
			dateFormatDescriptor.setPropertyEditorClass(DateFormatEditor.class);
			
			PropertyDescriptor messagesDescriptor = new PropertyDescriptor("messages", Widget.class);
			messagesDescriptor.setPropertyEditorClass(MessagesEditor.class);
			
			PropertyDescriptor dimentionDescriptor = new PropertyDescriptor("dimention", Widget.class);
			dimentionDescriptor.setPropertyEditorClass(DimentionEditor.class);
			
			return new PropertyDescriptor[] { dateFormatDescriptor, messagesDescriptor, dimentionDescriptor };
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
