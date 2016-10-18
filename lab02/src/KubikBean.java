import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;


public class KubikBean {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KubikBean window = new KubikBean();
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
	public KubikBean() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 429);
//		frame.getContentPane().setLayout(new BorderLayout(0, 0));
//		frame.getContentPane().setLayout(new GridLayout());
		Widget widget = new Widget();
		widget.setDateFormat(2);
		widget.setDimention(new Integer[]{400,400});
		widget.setBackgroundColor(Color.RED);
		widget.setMessages(new String[]{"jeden","dwa","trzy"});
//		widget.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				//widget.setBackgroundColor(Color.DARK_GRAY);
				//widget.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(widget);
		//widget.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/*ChartBean chartBean = new ChartBean();
		chartBean.setGraphColor(Color.DARK_GRAY);
		chartBean.setValues(new Double[]{1.0,2.0});
		chartBean.setTitle("Title\r\ncsdc");
		frame.getContentPane().add(chartBean, BorderLayout.CENTER);*/
	}

}
