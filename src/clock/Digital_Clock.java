package clock;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Digital_Clock extends JPanel {
	private static final long serialVersionUID = 1L;
	private  int hour;
	private  int minute;
	private  int second;
	private ArrayList<ArrayList<Integer>>digits = new ArrayList<ArrayList<Integer>>();
 
	private int firstDigit;
	private int secondDigit;
	private int thirdDigit;
	private int fourthDigit;
	private int fifthDigit;
	private int sixthDigit;
	private Timer timer;
	private String format;
	private ZoneId  zone ;
    private	ZonedDateTime zonedDateAndTime;
    private JFrame editorFrame;
	private JComboBox<String> secondList;
	private JComboBox<String> minuteList;
	private JComboBox<String> hourList;
	private JComboBox<String> formateCbox;
	
	public Digital_Clock() {
		super();
		setLayout(null);
		makeEditButton();
		for(int i=0;i<10;i++){
			digits.add(FileManager.readFromFile(i));

		}
		
		initializeTimeAndDate();
		
		setSize(1550,652);
		timer= new Timer(1000, new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {    
	        repaint();
	        sixthDigit++;
	        
	        if(sixthDigit==10){
	        	sixthDigit=0;
	        	fifthDigit++; 
	        }
	        if(fifthDigit==6){
	        	fifthDigit = 0;
	        	fourthDigit++; 
	        }
	        if(fourthDigit==10){
	        	fourthDigit=0;
	        	thirdDigit++;
	        }
	        if(thirdDigit==6){
	        	thirdDigit=0;
	        	secondDigit++;
	        }
	        if(secondDigit==10){
	        	secondDigit=0;
	        	firstDigit++;
	        }
	        if(firstDigit == 1 && secondDigit == 3){
	        	firstDigit=0;
	        	secondDigit=1;
	        }
	        
	        if((firstDigit==1) && (secondDigit ==2) && (thirdDigit+fourthDigit+fifthDigit+sixthDigit == 0)){
	        	if(format=="সকাল")format = "বিকাল";
	        	else format = "সকাল";
	        }	             
	      }
	    });
		timer.start();
	
		
	}
	
	
	private void makeEditorFrame() {
		editorFrame = new JFrame("সম্পাদন করুন");
		editorFrame.setBounds(300,100,600,600);
		editorFrame.setLayout(null);
		editorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editorFrame.setMinimumSize(new Dimension(500, 500));
		makeInputCombobox();
		makeOKButtonOfEditor();
		editorFrame.setVisible(true);
		
	}
	
	private void makeOKButtonOfEditor() {
		JButton okButton = new JButton("ঠিক");
		okButton.setBounds(270,200,100,50);
		okButton.setFont(new Font("Vrinda",Font.PLAIN,40));
		okButton.setFocusPainted(false);
		okButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			    int indexOfFormate; 
			
				second = secondList.getSelectedIndex()-1;
				minute = minuteList.getSelectedIndex()-1;
				hour = hourList.getSelectedIndex();
				indexOfFormate = formateCbox.getSelectedIndex();
				if(second==-1 || minute==-1 || hour==0 || indexOfFormate ==0){				
					JLabel message = new JLabel("সঠিক তথ্য দিন");
					message.setFont(new Font("Vrinda",Font.PLAIN,30));
					JOptionPane.showMessageDialog(null,message,"ভুল",JOptionPane.ERROR_MESSAGE );
				}
				else{
					if(formateCbox.getSelectedIndex()==1)format = "সকাল";
					else if(formateCbox.getSelectedIndex()==2)format ="বিকাল";
					setDigits();
					editorFrame.dispose();
				}
		}
	});
	editorFrame.add(okButton);
}


	private void makeInputCombobox() {
		
		JLabel textLabel = new JLabel("সম্পাদন করুন:");
		textLabel.setFont(new Font("Vrinda",Font.PLAIN,50));
		textLabel.setForeground(Color.MAGENTA);
		textLabel.setBounds(200,10,230,40);
		editorFrame.add(textLabel);
		
		
		String []seconds = new String[61];
		seconds[0]="সেকেন্ড";
		for(int i=0;i<6;i++){
			for(int j=0;j<10;j++){
				seconds[i*10+j+1] = String.valueOf((char)((int)'০'+i)) + String.valueOf((char)((int)'০'+j));
			}
		}
		
		secondList = new JComboBox<String>(seconds);
		secondList.setSelectedIndex(0);
		secondList.getEditor().getEditorComponent().setBackground(Color.GRAY);
		secondList.setFont(new Font("Vrinda",Font.PLAIN,30));
		secondList.setBounds(112,80,100,40);
		
		editorFrame.add(secondList);
		
		
		String []minutes = new String[61];
		minutes[0]="মিনিট";
		for(int i=0;i<6;i++){
			for(int j=0;j<10;j++){
				minutes[i*10+j+1] = String.valueOf((char)((int)'০'+i)) + String.valueOf((char)((int)'০'+j));
			}
			
		}
		
		minuteList = new JComboBox<String>(minutes);
		minuteList.setSelectedIndex(0);
		minuteList.getEditor().getEditorComponent().setBackground(Color.GRAY);
		minuteList.setFont(new Font("Vrinda",Font.PLAIN,30));
		minuteList.setBounds(216,80,100,40);
		
		editorFrame.add(minuteList);
		
		String []hours = new String[13];
		hours[0]="ঘন্টা";
		for(int i=0;i<2;i++){
			for(int j=0;j<10;j++){
				if(i==1 && j==2) {i=2;break;}
				else if(i==0 && j==9) hours[i*10+j+1] = String.valueOf('১') + String.valueOf('০');
				else hours[i*10+j+1] = String.valueOf((char)((int)'০'+i)) + String.valueOf((char)((int)'১'+j));
			}
			
		}
		
	    hourList = new JComboBox<String>(hours);
		hourList.setSelectedIndex(0);
		hourList.getEditor().getEditorComponent().setBackground(Color.GRAY);
		hourList.setFont(new Font("Vrinda",Font.PLAIN,30));
		hourList.setBounds(320,80,100,40);
		
	    editorFrame.add(hourList);
	    
	    String[] formats = new String[]{"ফরমেট","সকাল","বিকাল"};
	    formateCbox = new JComboBox<>(formats);
	    formateCbox.setFont(new Font("Vrinda", Font.PLAIN, 30));
	    formateCbox.setSelectedIndex(0);
	    formateCbox.setBounds(422,80,100,40);
	    editorFrame.add(formateCbox);
		
		
	}



	private void makeEditButton() {
		JButton editButton = new JButton("সম্পাদন করুন");
		editButton.setFont(new Font("Vrinda", Font.PLAIN, 40));
		editButton.setBounds(1000,50,200,60);
		editButton.setFocusPainted(false);
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				makeEditorFrame();
				
			}
		});
		add(editButton);
	}


	private void initializeTimeAndDate() {
		
		zone = ZoneId.of( "Asia/Dhaka" );
		zonedDateAndTime = ZonedDateTime.now(zone);
		hour = zonedDateAndTime.getHour();
		if(hour >=12)format = "বিকাল";
		else format = "সকাল" ;
		
		if(hour>12 || hour ==0){
			hour = Math.abs(hour - 12);
		}
		minute = zonedDateAndTime.getMinute();
		second = zonedDateAndTime.getSecond();
		
		setDigits();
	}


	private void setDigits() {
		firstDigit = (int)(hour / 10);
		secondDigit=hour % 10 ;
		thirdDigit = minute / 10;
		fourthDigit = minute % 10;
		fifthDigit = second / 10;
		sixthDigit = second % 10;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.BLUE);
		drawFormat(g);		
		drawColons(g);
		drawSixthDigit(g);  
		if(firstDigit!=0) drawFirstDigit(g);
		drawSecondDigit(g);
		drawThirdDigit(g);
		drawFourthDigit(g);
		drawFifthDigit(g);
	}
	
	
	private void drawFormat(Graphics g) {
		g.setFont(new Font("Vrinda", Font.BOLD, 70));
		g.drawString(format, 1200, 600);
	}

	private void drawSixthDigit(Graphics g) {
		for(int i =0;i<digits.get(sixthDigit).size();i=i+2){
			g.fillOval(digits.get(sixthDigit).get(i)+845, digits.get(sixthDigit).get(i+1), 12, 12);
		
		}
	}

	private void drawFifthDigit(Graphics g) {

			for(int i =0;i<digits.get(fifthDigit).size();i=i+2){
				g.fillOval(digits.get(fifthDigit).get(i)+682, digits.get(fifthDigit).get(i+1), 12, 12);
		
			}
	}

	private void drawFourthDigit(Graphics g) {
		
			for(int i =0;i<digits.get(fourthDigit).size();i=i+2){
				g.fillOval(digits.get(fourthDigit).get(i)+499, digits.get(fourthDigit).get(i+1), 12, 12);
			}
	}

	private void drawThirdDigit(Graphics g) {
		
			for(int i =0;i<digits.get(thirdDigit).size();i=i+2){
				g.fillOval(digits.get(thirdDigit).get(i)+336, digits.get(thirdDigit).get(i+1), 12, 12);
		
			}
	}

	private void drawSecondDigit(Graphics g) {
		
			for(int i =0;i<digits.get(secondDigit).size();i=i+2){
				g.fillOval(digits.get(secondDigit).get(i)+123, digits.get(secondDigit).get(i+1), 12, 12);
		
			}
	}

	private void drawFirstDigit(Graphics g) {
		
			for(int i=0;i<digits.get(firstDigit).size();i=i+2){
				g.fillOval(digits.get(firstDigit).get(i)-40, digits.get(firstDigit).get(i+1), 12, 12);
			}
	}
	

	private void drawColons(Graphics g) {
		g.fillRect(456, 280, 10, 10);
		g.fillRect(456, 350, 10, 10);

		g.fillRect(812, 280, 10, 10);
		g.fillRect(812, 350, 10, 10);
	}	

}
