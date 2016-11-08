package lab05;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



public class MainFrame {

	private JFrame frame;

	static class Form{
		public JComboBox typ; 
		public JComboBox walidator;
		public static int position= 10;
		public Form(JFrame frame){
			typ = new JComboBox();		
			typ.setModel(new DefaultComboBoxModel(new String[] {"email", "wiek", "imie"}));
			typ.setBounds(149, position, 100, 23);
			frame.getContentPane().add(typ);
			
			walidator = new JComboBox();
			walidator.setModel(new DefaultComboBoxModel(new String[] {"walidator_Imie", "walidator_wiek"}));
			walidator.setBounds(267, position, 150, 22);
			frame.getContentPane().add(walidator);
			position+=40;
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
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
		
		JButton btnAddNewForm = new JButton("Add new form");
		btnAddNewForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Form(frame);
				frame.repaint();
			}
		});
		btnAddNewForm.setBounds(10, 11, 129, 23);
		frame.getContentPane().add(btnAddNewForm);
		Form form = new Form(frame);
		//new Form(frame);
//		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setModel(new DefaultComboBoxModel(new String[] {"email", "wiek", "imie"}));
//		comboBox.setBounds(149, 11, 108, 23);
//		frame.getContentPane().add(comboBox);
//		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"walidator_Imie", "walidator_wiek"}));
//		comboBox_1.setBounds(267, 11, 122, 22);
//		frame.getContentPane().add(comboBox_1);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnRun);
	}
}
