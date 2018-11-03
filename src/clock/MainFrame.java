package clock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;//01689980500
import javax.swing.JTabbedPane;

public class MainFrame {
	private JFrame mainFrame;
	private JTabbedPane tabbedPane;
	
	public MainFrame() {
		makeFrame();
		makeTabbedPane();
		mainFrame.setVisible(true);
	}

	private void makeTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Sherif",Font.PLAIN,20));
		tabbedPane.addTab("Analog_Watch", new WallClock());
		tabbedPane.addTab("Digital_Watch", new Digital_Clock());

		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		mainFrame.pack();
	}

	private void makeFrame() {
		mainFrame = new JFrame("সময়");
		mainFrame.setFont(new Font("Vrinda",Font.PLAIN,40));
		mainFrame.setLayout(null);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setMinimumSize(new Dimension(700, 500));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
