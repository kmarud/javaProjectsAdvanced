package lab05;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



public class FormsFrame {

	public JFrame frame;

	ArrayList<MainFrame.Form> formsLocal = new ArrayList<MainFrame.Form>();

	static class Form{
		public JTextField typ; 
		//public JTextField walidator;
		public static int position= 10;
		public Form(JFrame frame){
			typ = new JTextField();		
			//typ.setModel(new DefaultComboBoxModel(new String[] {"email", "wiek", "imie","plec"}));
			typ.setBounds(149, position, 100, 23);
			frame.getContentPane().add(typ);
			
			//walidator = new JTextField();
			//walidator.setModel(new DefaultComboBoxModel(new String[] {"walidator_Imie", "walidator_wiek"}));
			//walidator.setBounds(267, position, 150, 22);
			//frame.getContentPane().add(walidator);
			position+=40;
		}
	}
	


	/**
	 * Create the application.
	 */
	public FormsFrame(ArrayList<MainFrame.Form> forms) {
		formsLocal=forms;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int position=50;
		for(MainFrame.Form f:formsLocal){
			JTextField typ = new JTextField();
			typ.setText("Pole typu " + String.valueOf(f.getTyp()) + " z walidatorem " + String.valueOf(f.getWalidator()));
			typ.setBounds(10, position, 200, 23);
			position += 50;
			frame.getContentPane().add(typ);
		}

		JButton btnRun = new JButton("Save");
		btnRun.setBounds(335, 227, 89, 23);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new JFrame();
			}
		});
		frame.getContentPane().add(btnRun);
	}
}
