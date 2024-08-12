import java.util.ArrayList;
import java.util.List;

/**
 * The Node class represents an intersection on the Grid.
 * This class simply holds data about an intersection on the grid.
 * @author brad
 *
 */
public class Node {

	public final static int infinity = 1000;
	
	private boolean blocked;
	private Node previous;
	private int distance;
	private int col;
	private int row;
	private ArrayList<Node> neighbors;
	private static ArrayList<Node> nodes = new ArrayList<Node>();
	
/**
 * Constructor for a Node.
 * Make a node at (r,c) and initialize it to not-blocked and with a distance of infinity.
 * @param r the row of the Node
 * @param c the column of the Node
 */
	public Node(int r, int c) {
		row = r;
		col = c;
		setBlocked(false);
		setDistance(infinity);
		neighbors = new ArrayList<Node>();
		nodes.add(this);
	}
	
	public static Node getNodeAt(int row, int col) {
		for (Node n: nodes) {
			if (n.row == row && n.col == col)
				return n;
		}
		return null;
	}
	
	public static List<Node> getNodes() {
		return nodes;
	}
	
/**
 * Reset the distance and previous values of a Node.
 * Resets the values when rerunning the shortest path algorithm
 */
	public void resetValues() {
		setDistance(infinity);
		setPrevious(null);
	}
	
/**
 * Convert the Node to a String for printing.
 * @return the coordinates for this Node as (r,c) format.
 */
	public String toString() {
		return "(" + row + "," + col + ")";
	}

/**
 * Set this Node to blocked or clear.
 * @param blocked true if the node is blocked, false if not blocked.
 */
	void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

/**
 * Tell if a Node is blocked.
 * @return true if the node is blocked otherwise false.
 */
	boolean isBlocked() {
		return blocked;
	}

/**
 * Set the distance value for this Node.
 * @param distance the current distance value for this node.
 */
	void setDistance(int distance) {
		this.distance = distance;
	}

/**
 * Get the distance value for this Node.
 * @return the current distance value for this Node.
 */
	int getDistance() {
		return distance;
	}

/**
 * Set the pointer to the previous node.
 * The previous node is the current previous node to this one as the shortest
 * path algorithm runs.
 * @param previous a reference to the previous Node object.
 */
	public void setPrevious(Node previous) {
		this.previous = previous;
	}

/**
 * Get the previous Node.
 * @return the reference to the previous node on the current shortest path.
 */
	public Node getPrevious() {
		return previous;
	}
	
	public void addNeighbor(Node n) {
		if (n == null) return;
		neighbors.add(n);
	}
	
	public List<Node> getNeighbors() {
		return neighbors;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return col;
	}
}
