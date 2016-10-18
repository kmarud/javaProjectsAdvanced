import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;


public class MessagesEditorPanel extends JPanel {

	private static final long serialVersionUID = 5L;

	public MessagesEditorPanel(PropertyEditorSupport ed) {
		editor = ed;
		setArray((String[]) ed.getValue());

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 100;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		add(addField, gbc, 0, 0, 1, 1);
		add(valueField, gbc, 0, 1, 1, 1);

		gbc.fill = GridBagConstraints.NONE;

		add(addButton, gbc, 1, 0, 1, 1);
		add(valueButton, gbc, 1, 1, 1, 1);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addValue();
			}
		});

		valueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changeValue();
			}
		});

		gbc.weighty = 100;
		gbc.fill = GridBagConstraints.BOTH;

		add(new JScrollPane(elementList), gbc, 0, 2, 2, 1);

		elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		elementList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int i = elementList.getSelectedIndex();
				if (i < 0)
					return;
				valueField.setText("" + array[i]);
			}
		});

		elementList.setModel((ListModel<String>) model);
		elementList.setSelectedIndex(0);
	}

	public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
	}

	public void addValue() {
		
		
		String newValue = addField.getText();

		setArray((String[]) arrayGrow(array, array.length+1));
		setArray(array.length-1, newValue);
		editor.setValue(array);
		addField.setText("");
		editor.firePropertyChange();
	}


	public void changeValue() {
		String v = "";
		try {
			v = valueField.getText();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "" + e, "Input Error", JOptionPane.WARNING_MESSAGE);
			valueField.requestFocus();
			return;
		}
		int currentIndex = elementList.getSelectedIndex();
		setArray(currentIndex, v);
		editor.firePropertyChange();
	}

	public void setArray(String[] v) {
		if (v == null)
			array = new String[0];
		else
			array = v;
		model.setArray(array);
		if (array.length > 0) {
			valueField.setText("" + array[0]);
			elementList.setSelectedIndex(0);
		} else
			valueField.setText("");
	}

	public String[] getArray() {
		return (String[]) array.clone();
	}

	public void setArray(int i, String value) {
		if (0 <= i && i < array.length) {
			model.setValue(i, value);
			elementList.setSelectedIndex(i);
			valueField.setText("" + value);
		}
	}

	public String getArray(int i) {
		if (0 <= i && i < array.length)
			return array[i];
		return "";
	}

	private static Object arrayGrow(Object a, int newLength) {
		Class<? extends Object> cl = a.getClass();
		if (!cl.isArray())
			return null;
		Class<?> componentType = a.getClass().getComponentType();
		int length = Array.getLength(a);

		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
		return newArray;
	}

	private PropertyEditorSupport editor;
	private String[] array;
	private JTextField addField = new JTextField(4);
	private JTextField valueField = new JTextField(12);
	private JButton addButton = new JButton("Add");
	private JButton valueButton = new JButton("Change");
	private JList<String> elementList = new JList<String>();
	private DoubleArrayListModel model = new DoubleArrayListModel();
}

class DoubleArrayListModel extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;

	public int getSize() {
		return array.length;
	}

	public String getElementAt(int i) {
		return  array[i];
	}

	public void setArray(String[] a) {
		int oldLength = array == null ? 0 : array.length;
		array = a;
		int newLength = array == null ? 0 : array.length;
		if (oldLength > 0)
			fireIntervalRemoved(this, 0, oldLength);
		if (newLength > 0)
			fireIntervalAdded(this, 0, newLength);
	}

	public void setValue(int i, String value) {
		array[i] = value;
		fireContentsChanged(this, i, i);
	}

	private String[] array;
}
