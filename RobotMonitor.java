import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RobotMonitor implements Runnable {
	
	LabeledField nxtNameField;
	LabeledField statusField;
	LabeledField xField;
	LabeledField yField;
	GridUI gridUI;
	
	JButton connectButton;
	int rows, cols;
	
	public RobotMonitor(int r, int c) {
		rows = r;
		cols = c;
	}
	
	public void createUI() {
		EventQueue.invokeLater(this);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void run() {
		JFrame mainFrame = new JFrame("Robot monitor control panel");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
		
		Box nxtConnectionPanel = new Box(BoxLayout.X_AXIS);
		nxtConnectionPanel.add(nxtNameField = new LabeledField("NXT name: "));
		nxtConnectionPanel.add(Box.createHorizontalGlue());
		nxtConnectionPanel.add(connectButton = new JButton("Connect"));
		statusPanel.add(nxtConnectionPanel);
		
		Box coordinatesPanel = new Box(BoxLayout.X_AXIS);
		coordinatesPanel.add(xField = new LabeledField("X: ", false));
		coordinatesPanel.add(Box.createHorizontalGlue());
		coordinatesPanel.add(yField = new LabeledField("Y: ", false));
		coordinatesPanel.add(Box.createHorizontalGlue());
		coordinatesPanel.add(new JButton("Send"));
		statusPanel.add(coordinatesPanel);
		
		statusPanel.add(statusField = new LabeledField("Status: ", false));
		
		mainPanel.add(statusPanel, BorderLayout.NORTH);
		
		JPanel gridPanel = new JPanel(new BorderLayout());
		Box clearMapBox = new Box(BoxLayout.X_AXIS);
		clearMapBox.add(Box.createHorizontalGlue());
		clearMapBox.add(new JButton("Clear Map"));
		clearMapBox.add(Box.createHorizontalGlue());
		gridPanel.add(clearMapBox, BorderLayout.NORTH);

		gridPanel.add(gridUI = new GridUI(rows, cols), BorderLayout.CENTER);
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		mainFrame.setPreferredSize(new Dimension(600, 800));
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public void setStatus(String status) {
		statusField.setText(status);
	}
	
	public void setX(String x) {
		xField.setText(x);
	}
	
	public void setY(String y) {
		yField.setText(y);
	}
	
	public void setRobotPosition(Node n) {
		gridUI.setRobotPosition(n);
	}
}
