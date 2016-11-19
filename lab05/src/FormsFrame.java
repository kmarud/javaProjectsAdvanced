
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

class Form {
	public Form(JLabel fieldType, JTextField data, JLabel errorDescription, String validatorName) {
		this.fieldType = fieldType;
		this.data = data;
		this.errorDescription = errorDescription;
		this.validatorName = validatorName;
	}

	JLabel fieldType;
	JTextField data;
	JLabel errorDescription;
	String validatorName;
}

public class FormsFrame {

	public JFrame frame;

	ArrayList<MainFrame.Form> formsLocal = new ArrayList<MainFrame.Form>();
	ArrayList<Form> forms = new ArrayList<Form>();

	/**
	 * Create the application.
	 */
	public FormsFrame(ArrayList<MainFrame.Form> forms) {
		formsLocal = forms;
		initialize();
	}

	public void prepareForms(){
		int position = 50;
		for (MainFrame.Form f : formsLocal) {
			JLabel fieldType = new JLabel(f.getFieldType() + ": ");
			JLabel errorDescription = new JLabel("");
			errorDescription.setBounds(210, position, 200, 20);
			errorDescription.setForeground(Color.red);
			fieldType.setBounds(30, position, 100, 20);
			JTextField data = new JTextField();
			data.setBounds(100, position, 100, 23);
			position += 40;
			
			forms.add(new Form(fieldType, data, errorDescription, f.getValidatorName()));
			frame.getContentPane().add(fieldType);
			frame.getContentPane().add(data);
			frame.getContentPane().add(errorDescription);
		}
	}
	
	public void validateFields(){
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		for (Form form : forms) {
			try {
				engine.eval(new FileReader(form.validatorName + ".js"));
				Invocable invocable = (Invocable) engine;

				Object result = invocable.invokeFunction("validate", form.data.getText());
				form.errorDescription.setText(result.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		prepareForms();
		JLabel statement = new JLabel("Please fill the following fields");
		statement.setBounds(30, 10, 200, 20);
		frame.getContentPane().add(statement);
		JButton btnRun = new JButton("Save");
		btnRun.setBounds(335, 227, 89, 23);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validateFields();
			}
		});
		frame.getContentPane().add(btnRun);
	}
}
