package clock;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WallClock extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> peakOfSecondSticX = new ArrayList<>();
	private ArrayList<Integer> peakOfSecondSticY = new ArrayList<>();
	private ArrayList<Integer> peakOfMinuteSticX = new ArrayList<>();
	private ArrayList<Integer> peakOfMinuteSticY = new ArrayList<>();
	private ArrayList<Integer> peakOfHourSticX = new ArrayList<>();
	private ArrayList<Integer> peakOfHourSticY = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> circumferenceXY = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> subCircumferenceXY = new ArrayList<>();

	private int radius = 300;
	private int cornerOfBoundaryRectangleX = 400;
	private int cornerOfBoundaryRectangleY = 40;
	private int centerX=700;
	private int centerY=340;
    private	Timer timer;
	private int second=0;
	private int minute=0;
	private int hour=0;
	private int positionOfHourStick;
	
	public WallClock() {
		setLayout(null);
		
		takeXYofPeakOfSecondStics();
		takeXYPeakOfMinuteStics();
		takeXYPeakOfHourStics();
		takeXYtoDrawTimingLine();
		
		initialiseTime();
		
		timer=new Timer(1000, this);
		timer.start();
		
		
	}

	private void takeXYtoDrawTimingLine() {
		circumferenceXY = calcculatePoints(10);
		subCircumferenceXY = calcculatePoints(25);
	}

	private void initialiseTime() {
		ZoneId zone = ZoneId.of( "Asia/Dhaka" );
		ZonedDateTime zonedDateAndTime = ZonedDateTime.now(zone);
		hour = zonedDateAndTime.getHour();
		
		if(hour>12 || hour ==0){
			hour = Math.abs(hour - 12) ;
		}
		minute = zonedDateAndTime.getMinute();
		second = zonedDateAndTime.getSecond();
		positionOfHourStick = hour*5 +  (int)minute*5 /60;
		if(positionOfHourStick>=60)positionOfHourStick = positionOfHourStick-60;
	}

	private void takeXYPeakOfHourStics() {
		ArrayList< ArrayList<Integer>> allCoordinates = new ArrayList<>();
		allCoordinates = calcculatePoints(130);
		peakOfHourSticX = allCoordinates.get(0);
		peakOfHourSticY = allCoordinates.get(1);
	}

	private void takeXYPeakOfMinuteStics() {
		ArrayList< ArrayList<Integer>> allCoordinates = new ArrayList<>();
		allCoordinates = calcculatePoints(90);
		peakOfMinuteSticX = allCoordinates.get(0);
		peakOfMinuteSticY = allCoordinates.get(1);
	}

	private void takeXYofPeakOfSecondStics() {
		ArrayList< ArrayList<Integer>> all = new ArrayList<>();
		all = calcculatePoints(50);
		peakOfSecondSticX = all.get(0);
		peakOfSecondSticY = all.get(1);
	}

	private ArrayList<ArrayList<Integer>>calcculatePoints(int toMinimiseFromRadius) {
		int i;
		double angle;
		ArrayList<Integer> x = new ArrayList<>() ;
		ArrayList<Integer> y = new ArrayList<>() ;
		ArrayList< ArrayList<Integer>> all = new ArrayList<>();
		
		double tempX,tempY;
		for(i=0;i<60;i++){
			angle = (Math.PI/2)- ((2*Math.PI)/60)*i;
			tempX = (radius-toMinimiseFromRadius)* Math.cos(angle);
			tempY = (radius-toMinimiseFromRadius)* Math.sin(angle);
			x.add(Integer.valueOf((int)tempX+centerX));
			y.add(Integer.valueOf((int)(centerY-tempY)));
			
		}
		all.add(x);
		all.add(y);
		
		return all;
	}
	

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawOval(cornerOfBoundaryRectangleX, cornerOfBoundaryRectangleY, radius*2, radius*2);
		g2d.fillOval(centerX-10, centerY-10, 20, 20);
		drawTimingLines(g2d);
		g2d.setFont(new Font("sherif", Font.BOLD, 30));
		drawDigits(g2d);		
		g2d.setColor(Color.GREEN);
	    g2d.drawLine(centerX, centerY,peakOfSecondSticX.get(second).intValue(),
	    		peakOfSecondSticY.get(second).intValue());
		g2d.setColor(Color.BLACK);
		g2d.drawLine(centerX, centerY,peakOfMinuteSticX.get(minute).intValue(),
				peakOfMinuteSticY.get(minute).intValue());
		g2d.setColor(Color.MAGENTA);
		g2d.drawLine(centerX, centerY,peakOfHourSticX.get(positionOfHourStick).intValue(),
				peakOfHourSticY.get(positionOfHourStick).intValue());
		
		
	}
	
	private void drawDigits(Graphics2D g2d) {
		g2d.drawString("1", subCircumferenceXY.get(0).get(5)-30,
				subCircumferenceXY.get(1).get(5)+40);
		g2d.drawString("2", subCircumferenceXY.get(0).get(10)-35,
				subCircumferenceXY.get(1).get(10)+25);
		g2d.drawString("3", subCircumferenceXY.get(0).get(15)-35,
				subCircumferenceXY.get(1).get(15)+10);
		g2d.drawString("4", subCircumferenceXY.get(0).get(20)-30,
				subCircumferenceXY.get(1).get(20)-5);
		g2d.drawString("5", subCircumferenceXY.get(0).get(25)-25,
				subCircumferenceXY.get(1).get(25)-15);
		g2d.drawString("6", subCircumferenceXY.get(0).get(30)-5,
				subCircumferenceXY.get(1).get(25)+10);
		g2d.drawString("7", subCircumferenceXY.get(0).get(35)+10,
				subCircumferenceXY.get(1).get(35)-20);
		g2d.drawString("8", subCircumferenceXY.get(0).get(40)+25,
				subCircumferenceXY.get(1).get(40)-5);
		g2d.drawString("9", subCircumferenceXY.get(0).get(45)+25,
				subCircumferenceXY.get(1).get(45)+10);
		g2d.drawString("10", subCircumferenceXY.get(0).get(50)+15,
				subCircumferenceXY.get(1).get(50)+30);
		g2d.drawString("11", subCircumferenceXY.get(0).get(55)+5,
				subCircumferenceXY.get(1).get(55)+40);
		g2d.drawString("12", subCircumferenceXY.get(0).get(0)-15,
				subCircumferenceXY.get(1).get(0)+50);
	}

	private void drawTimingLines(Graphics g2d) {
		g2d.setColor(Color.MAGENTA);
		for(int i=0;i<60;i++){
			g2d.drawLine(circumferenceXY.get(0).get(i).intValue(),
					circumferenceXY.get(1).get(i).intValue(), 
					subCircumferenceXY.get(0).get(i).intValue(),
					subCircumferenceXY.get(1).get(i).intValue());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		
		second++;
		if(second ==60){
			minute++;
			second=0;
		}

		if(minute  ==60 ){
			hour++;
			minute=0;
		}  
		if(hour==12){
			hour=0;
		}
		positionOfHourStick = hour*5 +  (int)minute*5 /60;
		if(positionOfHourStick>=60) positionOfHourStick = positionOfHourStick-60;
		 repaint();
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(new WallClock());
		frame.setVisible(true);
		/*new WallClock();*/
	}
	
}
