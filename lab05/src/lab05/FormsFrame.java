package lab05;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.DefaultComboBoxModel;



class Forma{
	public Forma(JLabel typ, JTextField dane, JLabel werdykt, String validatorName){
		this.typ = typ;
		this.dane = dane;
		this.werdykt = werdykt;
		this.validatorName = validatorName;
	}
	JLabel typ;
	JTextField dane;
	JLabel werdykt;
	String validatorName;
}

public class FormsFrame {

	public JFrame frame;

	ArrayList<MainFrame.Form> formsLocal = new ArrayList<MainFrame.Form>();
	ArrayList<Forma> forma = new ArrayList<Forma>();
	//ArrayList<JTextField> pola = new ArrayList<JTextField>();
	//ArrayList<JLabel> resultaty = new ArrayList<JLabel>();
	/*static class Form{
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
	}*/
	


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
			JLabel labelka = new JLabel(f.getTyp() + ": ");
			JLabel resultat = new JLabel("tu");
			resultat.setBounds(250, position, 200, 20);
			resultat.setForeground(Color.red);
			//labelka.setText(String.valueOf(f.getTyp()));
			//JLabel labelka = new JLabel(f.getTyp() + ": ");
			labelka.setBounds(30, position, 200, 20);
			JTextField typ = new JTextField();
			//typ.setText("Pole typu " + String.valueOf(f.getTyp()) + " z walidatorem " + String.valueOf(f.getWalidator()));
			typ.setBounds(100, position, 100, 23);
			position += 50;
			forma.add(new Forma(labelka, typ, resultat, f.getWalidator()));
			frame.getContentPane().add(labelka);
			frame.getContentPane().add(typ);
			frame.getContentPane().add(resultat);
		}

		JButton btnRun = new JButton("Save");
		btnRun.setBounds(335, 227, 89, 23);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
				for(Forma form: forma){
					try {
					//	engine.eval(new FileReader("ImieWalidator.js"));
						engine.eval(new FileReader(form.validatorName +  ".js"));
						Invocable invocable = (Invocable) engine;
						
						Object result = invocable.invokeFunction("validate", form.dane.getText());
						form.werdykt.setText(result.toString());
					//	resultaty.get(pol)result);
					//	System.out.println(result.getClass());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		frame.getContentPane().add(btnRun);
	}
}
