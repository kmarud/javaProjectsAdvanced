import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class MainWindow {

	private JFrame frame;
	private JList list = new JList();
	private MyClassLoader myClassLoader;
	ArrayList<Class> classes;
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnKuczma = new JTextPane();
		txtpnKuczma.setContentType("text/html");
		txtpnKuczma.setText("<i>Lorem ipsum</i> dolor sit amet, <i>consectetur adipiscing elit </i>. In diam magna, <i>pharetra sed</i> metus ac, tincidunt lobortis augue. Etiam pellentesque turpis eu urna venenatis finibus. Nam pretium lobortis sem.");
		txtpnKuczma.setBounds(10, 11, 414, 156);
		frame.getContentPane().add(txtpnKuczma);
		
		JButton btnLoadClass = new JButton("Load Class");
		
		
		
	//	System.out.println(fil);
		btnLoadClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File root = new File("C:\\Users\\Kamil\\workspace\\javaProjectsAdvanced\\lab03\\decorators\\");
				File[] files = root.listFiles();
				String[] classesNames = new String[files.length];
				//ArrayList<String> filesArray = new ArrayList<>();
				for(int i=0; i< files.length; i++) {
				  if(files[i].isFile()) {
					  classesNames[i] = (files[i].getName().replace(".class", ""));
				  }
				}
				
				classes = new ArrayList<>();
				myClassLoader = new MyClassLoader();
				for(String nam: classesNames){
					classes.add(myClassLoader.findClass(nam));
				}
				
				
				//classes.add(myClassLoader.findClass("Test2"));
				
				list.setModel(new AbstractListModel() {
					String[] values = classesNames;//new String[] {classes.get(0).getName(), classes.get(1).getName()};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				//Class myOject = myClassLoader.loadClass("Test");
				//System.out.println(
//				
//				try {
//					//Method methods = myOject.getMethod("witaj", java.lang.String.class);
//					//txtpnKuczma.setText(methods.invoke(myOject.newInstance(), txtpnKuczma.getText()).toString());
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}	
			}
		});
		btnLoadClass.setBounds(10, 220, 89, 23);
		frame.getContentPane().add(btnLoadClass);
		
		
		/*list.setModel(new AbstractListModel() {
			String[] values = new String[] {"jeden", "dwa", "trzy"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});*/

		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		       // if (e.getClickCount() == 2) {

		    	  //	System.out.println("selected value" + );
		    		Class clas = classes.get(list.getSelectedIndex());
		           Method method;
		           
		           try {
		        	   method = clas.getMethod("witaj", java.lang.String.class);
		        	   String lorem = "<i>Lorem ipsum</i> dolor sit amet, <i>consectetur adipiscing elit </i>. In diam magna, "
		        	   		+ "<i>pharetra sed</i> metus ac, tincidunt lobortis augue. Etiam pellentesque turpis eu urna venenatis finibus. Nam pretium lobortis sem.";
					txtpnKuczma.setText((String) method.invoke(clas.newInstance(), lorem));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		           // add selectedItem to your second list.
		          /* DefaultListModel model = (DefaultListModel) list2.getModel();
		           if(model == null)
		           {
		                 model = new DefaultListModel();
		                 list2.setModel(model);
		           }
		           model.addElement(selectedItem);
*/
		        // }
		    }
		};
		list.addMouseListener(mouseListener);
		
		list.setBounds(134, 173, 70, 100);
		frame.getContentPane().add(list);
		
		JButton btnUnloadAllClass = new JButton("Unload all class");
		btnUnloadAllClass.setBounds(238, 220, 89, 23);
		btnUnloadAllClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				list.setModel(new AbstractListModel() {
					String[] values = new String[] {};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				for(int i=0; i<classes.size(); i++){
					Class a = classes.get(i);
					a = null;
				}
				classes= null;
				myClassLoader = null;
				System.gc();
			//	myClassLoader = new MyClassLoader();
			}
		});
		frame.getContentPane().add(btnUnloadAllClass);
	}
}
