import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class MainWindow {

	private JFrame frame;
	private JList list = new JList();
	private JTextPane txtField = new JTextPane();
	private MyClassLoader myClassLoader;
	private Class[] classes;
	private String directoryPath = "C:/Users/Kamil/workspace/javaProjectsAdvanced/lab03/decorators/";

	private String loremIpsum = "<i>Lorem ipsum</i> dolor sit amet, <i>consectetur adipiscing elit "
			+ "</i>. In diam magna, <i>pharetra sed</i> metus ac, tincidunt lobortis augue. "
			+ "Etiam pellentesque turpis eu <i> urna </i> venenatis finibus. <i>Nam pretium lobortis sem</i>.";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	
	private void loadClasses(){
		File root = new File(directoryPath);
		File[] files = root.listFiles();
		ArrayList<String> classesNames = new ArrayList<>();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile() && files[i].getName().contains(".class")) {
				classesNames.add(files[i].getName().replace(".class", ""));
			}
		}

		classes = new Class[classesNames.size()];
		
		myClassLoader = new MyClassLoader();
		myClassLoader.setPathToDirectory(directoryPath);
		
		for (int i=0; i < classesNames.size(); i++) {
			classes[i] = myClassLoader.findClass(classesNames.get(i));
		}
	}
	
	private void unloadAllClasses(){
		classes = null;
		myClassLoader = null;
		System.gc();
	}
	
	private void decorate(){
		
		Class decoratorClass = classes[list.getSelectedIndex()];

		Method method;
		try {
			method = decoratorClass.getMethod("decorate", java.lang.String.class);
			txtField.setText((String) method.invoke(decoratorClass.newInstance(), loremIpsum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtField.setContentType("text/html");
		txtField.setText(loremIpsum);
		txtField.setBounds(10, 11, 414, 180);
		
		frame.getContentPane().add(txtField);

		JButton btnLoadClass = new JButton("Load Classes");

		btnLoadClass.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				loadClasses();

				String[] classesNames = new String[classes.length];
				for (int i = 0; i < classes.length; i++) {
					classesNames[i] = classes[i].getName();
				}

				list.setModel(new AbstractListModel() {
					String[] values = classesNames;

					public int getSize() {
						return values.length;
					}

					public Object getElementAt(int index) {
						return values[index];
					}
				});
			}
		});
		btnLoadClass.setBounds(10, 202, 128, 23);
		frame.getContentPane().add(btnLoadClass);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				decorate();
			}
		};
		
		list.addMouseListener(mouseListener);
		list.setBounds(162, 205, 174, 100);
		frame.getContentPane().add(list);

		JButton btnUnloadAllClass = new JButton("Unload all Classes");
		btnUnloadAllClass.setBounds(10, 236, 128, 23);
		btnUnloadAllClass.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {

				unloadAllClasses();
				
				list.setModel(new AbstractListModel() {
					String[] values = new String[] {};

					public int getSize() {
						return values.length;
					}

					public Object getElementAt(int index) {
						return values[index];
					}
				});
			}
		});
		frame.getContentPane().add(btnUnloadAllClass);
	}
}
