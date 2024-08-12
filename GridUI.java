import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GridUI extends JComponent implements MouseListener {
	
	int rows, cols;
	int rowWidth, colWidth;
	Node robotNode = null;
	private static final int circleDiameter = 15;
	
	public GridUI(int r, int c) {
		rows = r;
		cols = c;
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		int height = getHeight();
		int width = getWidth();
		Graphics2D g2d = (Graphics2D) g;

		rowWidth = height / (rows+1);
		colWidth = width / (cols+1);
		System.out.format("Width: (%d, %d)\n", rowWidth, colWidth);

		BasicStroke stroke = new BasicStroke(3);
		g2d.setStroke(stroke);
		
		/*
		 * Draw the grid
		 */
		for (int r = 0; r < rows; r++) {
			int span = (r + 1) * rowWidth;
			g2d.drawLine(colWidth, span, width - colWidth - cols + 1, span);
		}
		for (int c = 0; c < cols; c++) {
			int span = (c + 1) * colWidth;
			g2d.drawLine(span, rowWidth, span, height - rowWidth - rows + 1);
		}
		
		/*
		 * Draw the blocked nodes
		 */
		g2d.setColor(new Color(0xff0000));
		for (Node n: Node.getNodes()) {
			if (n.isBlocked()) {
				drawCircle(g2d, n);
			}
		}
		
		/*
		 * Draw the robot
		 */
		if (robotNode != null) {
			g2d.setColor(new Color(0xff));
			drawCircle(g2d, robotNode);
		}
	}

	private void drawCircle(Graphics2D g2d, Node n) {
		int x = (n.getColumn() + 1) * colWidth;
		int y = (n.getRow() + 1) * rowWidth;
		g2d.fillOval(x - circleDiameter/2, y - circleDiameter/2, circleDiameter, circleDiameter);
	}
	
	public void setRobotPosition(Node n) {
		robotNode = n;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.format("Mouse clicked: (%d, %d)\n", x, y);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
