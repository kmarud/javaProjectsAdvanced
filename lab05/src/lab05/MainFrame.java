package lab05;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



public class MainFrame {

	private JFrame frame;
	public ArrayList<Form> forms = new ArrayList<Form>();

	static class Form{
		public JComboBox typ; 
		public JComboBox walidator;
		public String[] models = {"Imie", "Pesel", "email", "wiek", "data", "URL"};
		public String[][] validators = {{"ImieWalidator"}, {"PeselValidator"}, {"EmailValidator"}, 
				{"WiekValidator"}, {"DzienMiesiacRok", "MiesiacDzienRok"}, {"UrlValidator"}};
		public static int position= 10;
		public Form(JFrame frame){
			typ = new JComboBox();	
			Object index;
			typ.setModel(new DefaultComboBoxModel(models));
			typ.setBounds(149, position, 100, 23);
			frame.getContentPane().add(typ);
			typ.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					walidator.setModel(new DefaultComboBoxModel(validators[typ.getSelectedIndex()]));
				}
			});
			walidator = new JComboBox();
		//	walidator.setModel(new DefaultComboBoxModel(validators[typ.getSelectedIndex()]));
			walidator.setBounds(267, position, 150, 22);
			frame.getContentPane().add(walidator);
			position+=40;
		}
		
		String getTyp(){
			//return 99;
			//return "abc";
			//return typ.getSelectedIndex();
			return models[typ.getSelectedIndex()];
		}
		
		String getWalidator(){
			//return "cos";
			return validators[typ.getSelectedIndex()][walidator.getSelectedIndex()];
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
				forms.add(new Form(frame));
				//framnew Form(frame);
				frame.repaint();
				frame.revalidate();
			}
		});
		btnAddNewForm.setBounds(10, 11, 129, 23);
		frame.getContentPane().add(btnAddNewForm);
		//new Form(frame);

		JButton btnRun = new JButton("Run");
		btnRun.setBounds(335, 227, 89, 23);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormsFrame frameWindow = new FormsFrame(forms);
				frameWindow.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnRun);
	}
}
