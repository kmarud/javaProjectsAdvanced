

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MainFrame {

	private JFrame frame;
	public ArrayList<Form> forms = new ArrayList<Form>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static class Form {
		JComboBox fieldType;
		JComboBox validatorName;
		String[] fieldTypesModels = { "Imie", "Pesel", "E-mail", "Wiek", "Data", "Strona URL" };
		String[][] validatorsModels = { { "ImieWalidator" }, { "PeselValidator" }, { "EmailValidator" },
				{ "WiekValidator" }, { "DzienMiesiacRok", "MiesiacDzienRok" }, { "UrlValidator" } };
		
		static int position = 40;

		
		public Form(JFrame frame) {
			fieldType = new JComboBox();
			validatorName = new JComboBox();

			fieldType.setModel(new DefaultComboBoxModel(fieldTypesModels));
			fieldType.setBounds(149, position, 100, 23);
			
			validatorName.setModel(new DefaultComboBoxModel(validatorsModels[0]));
			validatorName.setBounds(267, position, 150, 22);
				
			fieldType.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					validatorName.setModel(new DefaultComboBoxModel(validatorsModels[fieldType.getSelectedIndex()]));
				}
			});
		
			frame.getContentPane().add(fieldType);
			frame.getContentPane().add(validatorName);
			position += 40;
		}

		String getFieldType() {
			return fieldTypesModels[fieldType.getSelectedIndex()];
		}

		String getValidatorName() {
			return validatorsModels[fieldType.getSelectedIndex()][validatorName.getSelectedIndex()];
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
		JLabel type = new JLabel("Field type:");
		JLabel validatorName = new JLabel("Validator name:");
		type.setBounds(170, 10, 100, 20);
		validatorName.setBounds(290, 10, 100, 20);
		frame.getContentPane().add(type);
		frame.getContentPane().add(validatorName);
		JButton btnAddNewForm = new JButton("Add new form");
		btnAddNewForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				forms.add(new Form(frame));
				frame.repaint();
				frame.revalidate();
			}
		});
		btnAddNewForm.setBounds(10, 11, 129, 23);
		frame.getContentPane().add(btnAddNewForm);

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
