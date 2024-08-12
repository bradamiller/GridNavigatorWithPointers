import java.util.List;

/**
 * GridNavigator is a test class to verify that the Grid can correctly compute the shortest path
 * from one node to another.
 * In your programs this will be eventually replaced with code that gets the path and makes the 
 * robot drive along the path (until the destination square or a blocked square).
 * @author brad
 *
 */
public class GridNavigator {

	private Grid grid;
	private Node currentNode;
	private RobotMonitor robotMonitor;
	
/**
 * Constructor for the GridNavigator test class
 * @param rows the number of rows in our grid
 * @param cols the number of columns in our grid
 */
	public GridNavigator(int rows, int cols) {
		grid = new Grid(rows, cols);
		currentNode = Node.getNodeAt(0, 0);
		robotMonitor = new RobotMonitor(rows, cols);
		robotMonitor.createUI();
	}
	
	public void GotoNode(int row, int col) {
		Node destination = Node.getNodeAt(row, col);
		List<Node> shortestPath = grid.computeDistances(currentNode, destination);
		System.out.format("\n\nShortest path from %s to %s\n", currentNode, destination);
		grid.print();
		for (Node n: shortestPath)
			System.out.print(n);
		System.out.println();
		currentNode = destination;
		robotMonitor.setRobotPosition(currentNode);
	}
	
/**
 * Test method to verify that the shortest path code in the Grid works.
 */
	public void test() {
		grid.setBlocked(1, 0);
		grid.setBlocked(4, 5);
		GotoNode(5, 5);
		grid.setBlocked(3, 4);
		grid.setBlocked(4, 4);
		grid.setBlocked(5, 3);
		GotoNode(3, 3);
		GotoNode(2, 4);
	}
	
	public static void main(String[] args) {
		GridNavigator gridNavigator = new GridNavigator(8, 6);
		gridNavigator.test();
	}
}
