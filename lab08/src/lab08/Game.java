package lab08;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Game extends NotificationBroadcasterSupport implements GameMBean{
	
    private static MBeanServer mbs = null;
	private JFrame frame;
	private JTable table;
	private JButton column1Button;
	private JButton column2Button;
	private JButton column3Button;
	private JButton column4Button;
	private JButton column5Button;
	private JButton column6Button;
	private JButton column7Button;
	private static int sequenceNumber=0;
	private JTextField textField;

	final int ROWS = 6;
    final int COLUMNS = 7;
    int[][] fields = new int[ROWS][COLUMNS];
    Random random = new Random();
    String board;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
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
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 529, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table = new JTable();
		table.setBounds(10, 10, 490, 96);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(table);
		
		column1Button = new JButton("1");
		column1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseColumn(1);
			}
		});
		column1Button.setBounds(10, 116, 70, 23);
		frame.getContentPane().add(column1Button);
		
		column2Button = new JButton("2");
		column2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseColumn(2);
			}
		});
		column2Button.setBounds(80, 116, 70, 23);
		frame.getContentPane().add(column2Button);
		
		column3Button = new JButton("3");
		column3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseColumn(3);
			}
		});
		column3Button.setBounds(150, 116, 70, 23);
		frame.getContentPane().add(column3Button);
		
		column4Button = new JButton("4");
		column4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseColumn(4);
			}
		});
		column4Button.setBounds(220, 116, 70, 23);
		frame.getContentPane().add(column4Button);
		
		column5Button = new JButton("5");
		column5Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseColumn(5);
			}
		});
		column5Button.setBounds(290, 116, 70, 23);
		frame.getContentPane().add(column5Button);
		
		column6Button = new JButton("6");
		column6Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseColumn(6);
			}
		});
		column6Button.setBounds(360, 116, 70, 23);
		frame.getContentPane().add(column6Button);
		
		column7Button = new JButton("7");
		column7Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseColumn(7);
			}
		});
		column7Button.setBounds(430, 116, 70, 23);
		frame.getContentPane().add(column7Button);
		
		textField = new JTextField();
		textField.setBounds(10, 150, 490, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNowaGra = new JButton("Nowa gra");
		btnNowaGra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newGame();
			}
		});
		
		
		btnNowaGra.setBounds(10, 181, 89, 23);
		frame.getContentPane().add(btnNowaGra);
		
		
        mbs = ManagementFactory.getPlatformMBeanServer();
        //Hello helloBean = new Hello();
        ObjectName helloName = null;

        try {
            // Uniquely identify the MBeans and register them with the platform MBeanServer
            helloName = new ObjectName("GRA:name=GRA");
            mbs.registerMBean(this, helloName);
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	void newGame(){
		sequenceNumber=0;
		Notification n = new Notification("Nowa gra",this,sequenceNumber++, System.currentTimeMillis(),"Nowa gra");
        sendNotification(n);
		textField.setText("");
		fields = new int[ROWS][COLUMNS];
		showFields();
	}
	
	@Override
	public void chooseColumn(int choosenColumn){
		choosenColumn--;
        if(!userMove(choosenColumn))
           endGameDraw();
        
        if(checkWin(1)){
        	winPlayer();
        }else{

        if(!computerMove())
            endGameDraw();
        
        if(checkWin(2)){
        	winComputer();
        }
        }
	}
	
	public void showFields(){
		board = "";
        for (int i=0; i< ROWS;i++) {
            for(int j=0; j< COLUMNS;j++){
            	table.setValueAt(fields[i][j]==0 ? "" : fields[i][j], i, j);
            	table.revalidate();
            	table.repaint(1);
            	board += fields[i][j];
//                System.out.print(fields[i][j] + " ");
            }
            board += ";";
       //     System.out.println();
        }
    }
	
	void winPlayer(){
		textField.setText("Wygral gracz ! ");
		  Notification n = new Notification("Wygrana",this,sequenceNumber++, System.currentTimeMillis(),"Wygrana gracza");
          sendNotification(n);
	}
	
	void winComputer(){
		textField.setText("Wygral komputer !");
		Notification n = new Notification("Wygrana",this,sequenceNumber++, System.currentTimeMillis(),"Wygrana komputera");
        sendNotification(n);
	}
	
	void endGameDraw(){
		textField.setText("Koniec gry, remis!");
		 Notification n = new Notification("Remis",this,sequenceNumber++, System.currentTimeMillis(),"koniec gry, remis!");
        sendNotification(n);
	}
	
	 public boolean userMove(int choosenColumn){
	        Notification n = new Notification("Ruch gracza",this,sequenceNumber++, System.currentTimeMillis(),"Gracz wybiera kolumne " + (choosenColumn+1));
	        sendNotification(n);
	        if(enter(choosenColumn,1) == 0)
	            return false;
	        return true;
	    }
	 
	 public boolean computerMove(){
       int col = random.nextInt(7);
       
       Notification n = new Notification("Ruch komputera",this,sequenceNumber++, System.currentTimeMillis(),"PC wybiera kolumne " + (col+1));
       sendNotification(n);
      
       if(enter(col,2) == 0)
           return false;
       return true;
   }
	 
	 public int enter(int column, int player){
	        for(int i=ROWS-1; i>=0; i--)
	            if(fields[i][column] == 0){
	                fields[i][column] = player;
	                showFields();
	                return i;
	            }
	        return -1;
	    }
	 
	 public boolean checkWin(int player){

	        int count=0;
	        for(int j=0; j< ROWS; j++) {
	            for (int i = 0; i < COLUMNS; i++) {
	                if (fields[j][i] == player)
	                    count++;
	                else
	                    count = 0;

	                if (count >= 4)
	                    return true;
	            }
	        }

	        for (int j = 0; j < COLUMNS; j++) {
	            for (int i = 0; i < ROWS; i++) {
	                if (fields[i][j] == player)
	                    count++;
	                else
	                    count = 0;

	                if (count >= 4)
	                    return true;
	            }
	        }
	        
	        for(int  rowStart = 0; rowStart <=  ROWS - 4; rowStart++){
	            count = 0;
	            int rowa, col;
	            for( rowa = rowStart, col = 0; rowa < ROWS && col < COLUMNS; rowa++, col++ ){
	                if(fields[rowa][col] == player){
	                    count++;
	                    if(count >= 4) return true;
	                }
	                else {
	                    count = 0;
	                }
	            }
	        }
	        
	        for(int  colStart = 1; colStart <= COLUMNS - 4; colStart++){
	            count = 0;
	            int rowa, col;
	            for( rowa = 0, col = colStart; rowa < ROWS && col < COLUMNS; rowa++, col++ ){
	                if(fields[rowa][col] == player){
	                    count++;
	                    if(count >= 4) return true;
	                }
	                else {
	                    count = 0;
	                }
	            }
	        }
	        return false;
	    }

	@Override
	public String getBoard() {
		return board;
	}
}
