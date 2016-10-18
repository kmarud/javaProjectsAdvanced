
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Widget extends JPanel implements Serializable{

	private JTextField dateField = new JTextField();
	private JTextPane messagesText = new JTextPane();
	private JButton btnNextWish = new JButton("Next wish");
	
	private int actualWish = 0;
	
	public Widget() {
		this.add(dateField);
		
		this.add(btnNextWish);
		this.add(messagesText);
		btnNextWish.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(++actualWish >= messages.length){
	        		 actualWish=0;
	        	 }	
	        	 messagesText.setText(messages[actualWish]);
	          }          
	       });
		
		messagesText.setPreferredSize(new Dimension(200,200));
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
		Date date = new Date();
		dateField.setText(dateFormat.format(date)); //2014/08/06 15:59:48
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		DateFormat dateForma;
		if(dateFormat == DD_MM_YYYY)
			dateForma = new SimpleDateFormat("dd/MM/yyyy");
		else if (dateFormat == MM_DD_YYYY)
			dateForma = new SimpleDateFormat("MM/dd/yyyy");
		else if(dateFormat == DNAME_DD_MM_YYYY)
			dateForma = new SimpleDateFormat("EEE, d MMM yyyy");
		else 
			dateForma = new SimpleDateFormat("dd/dd/dd");
		Date date = new Date();
		dateField.setText(dateForma.format(date));
		messagesText.setText(messages[0]);
		this.setBackground(backgroundColor);
		messagesText.setPreferredSize(new Dimension(200,200));
//		messagesText.setPreferredSize(new Dimension(dimention[0],dimention[1]));
	}
	
	public void setBackgroundColor(Color backgroundColor){
		this.backgroundColor = backgroundColor;
		repaint();
	}
	
	public Color getBackgroundColor(){
		return backgroundColor;
	} 
	
	public void setDateFormat(int dateFormat){
		this.dateFormat = dateFormat;
	}
	
	public int getDateFormat(){
		return dateFormat;
	}
	
	public void setDimention(Integer[] dimention){
		this.dimention = dimention;
	}
	
	public Integer[] getDimention(){
		return dimention;
	}
	
	public void setMessages(String[] m){
		messages = new String[m.length];
		for(int i=0; i<m.length; i++)
			messages[i] = m[i];
	}
	
	public String[] getMessages(){
		return messages;
	}
	

	public void setMessages(int i, String message){
		if (0 <= i && i < messages.length)
			messages[i] = message;
	}

	public String getMessages(int i){
		if (0 <= i && i < messages.length)
			return messages[i];
		return "";
	}

	public Dimension getPreferredSize() {
		return new Dimension(dimention[0], dimention[1]);
	}

	private static final int DD_MM_YYYY = 0;
	private static final int MM_DD_YYYY = 1;
	private static final int DNAME_DD_MM_YYYY = 2;
	
	private Color backgroundColor = Color.blue;
	private int dateFormat = DD_MM_YYYY;
	private Integer[] dimention = {300,300};
	private String[] messages = {"empty"};
}
