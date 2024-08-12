import java.util.List;
import java.util.Stack;

/**
 * Grid maintains the grid of Nodes and computes the shortest path from one node
 * to another.
 */
public class Grid {
	protected int rows, cols;

	/**
	 * Initialize the grid array[rows][cols] with Node objects
	 * 
	 * @param rows
	 *            the number of rows in the grid
	 * @param cols
	 *            the number of cols in the grid
	 */
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		makeNode(0, 0);
	}

/**
 * Create the graph of all nodes.
 * Recursively create all the nodes in the grid. Create a single node, then create
 * all the neighbors. If a neighbor hasn't been created, then recursively create all
 * the other nodes.
 * @param row the row of this node
 * @param col the column of this node
 * @return the created Node object
 */
	private Node makeNode(int row, int col) {
		if (row < 0 || col < 0 || row >= rows || col >= cols)
			return null;
		Node n = Node.getNodeAt(row, col);
		if (n == null) {
			n = new Node(row, col);
			n.addNeighbor(makeNode(row + 1, col));
			n.addNeighbor(makeNode(row - 1, col));
			n.addNeighbor(makeNode(row, col + 1));
			n.addNeighbor(makeNode(row, col - 1));
		}
		return n;
	}

	private void resetGrid() {
		for (Node n : Node.getNodes())
			n.resetValues();
	}

	private WorkQueue getWorkQueue() {
		WorkQueue queue = new WorkQueue();
		for (Node n : Node.getNodes())
			if (!n.isBlocked())
				queue.add(n);
		return queue;
	}

	/**
	 * Set a Node as blocked. This sets a given node at a specified row and
	 * column as blocked.
	 * 
	 * @param row
	 *            the row of the blocked node
	 * @param col
	 *            the column of the blocked node
	 */
	public void setBlocked(int row, int col) {
		Node.getNodeAt(row, col).setBlocked(true);
	}

	/**
	 * Print a picture of all the node distances. This is used primarily for
	 * debugging the algorithm. It's important to have methods like this to
	 * easily see what's going on as the algorithm runs. The output looks like
	 * this: 0 1 2 3 4 5 ---------------------- 0| 6 5 4 3 4 5 1| X 4 3 2 3 4 2|
	 * 4 3 2 1 2 3 3| 3 2 1 0 X 4 4| 4 3 2 1 X X 5| 5 4 3 X 7 8 6| 6 5 4 5 6 7
	 * 7| 7 6 5 6 7 8 where the values are the distance back for the starting
	 * point (the node with the 0 value) and the X represents blocked nodes.
	 */
	public void print() {
		System.out.print("    ");
		for (int col = 0; col < cols; col++)
			System.out.format("%3d", col);
		System.out.println();
		System.out.println("----------------------");
		for (int row = 0; row < rows; row++) {
			System.out.format("%2d| ", row);
			for (int col = 0; col < cols; col++) {
				Node n = Node.getNodeAt(row, col);
				if (n.isBlocked())
					System.out.print("  X");
				else
					System.out.format("%3d", n.getDistance());
			}
			System.out.println();
		}
	}
	
	/**
	 * Compute the shortest path from a source Node to a destination Node. The
	 * algorithm computes the shortest distance to all the nodes and returns the
	 * path that represents the shortest distance from the source to the
	 * destination. The method implements Dykstra's algorithm as described in
	 * the Wikipedia article.
	 * 
	 * @param sourceNode
	 *            the starting Node
	 * @param destNode
	 *            the destination Node
	 * @return a Stack<Node> that represents the path when viewed from the first
	 *         to the last. Why a stack? Because the previous pointers in the
	 *         nodes link from the destination back to the source and the robot
	 *         needs to drive from the source so the stack reverses the order.
	 */
	/*
	 1 function Dijkstra(Graph, source):
	 2      for each vertex v in Graph:           // Initializations
	 3          dist[v] := infinity               // Unknown distance function from source to v
	 4          previous[v] := undefined          // Previous node in optimal path from source
	 5      dist[source] := 0                     // Distance from source to source
	 6      Q := the set of all nodes in Graph
	 // All nodes in the graph are unoptimized - thus are in Q
	 7      while Q is not empty:                 // The main loop
	 8          u := vertex in Q with smallest dist[]
	 9          if dist[u] = infinity:
	 10              break                         // all remaining vertices are inaccessible from source
	 11          remove u from Q
	 12          for each neighbor v of u:         // where v has not yet been removed from Q.
	 13              alt := dist[u] + dist_between(u, v)
	 14              if alt < dist[v]:             // Relax (u,v,a)
	 15                  dist[v] := alt
	 16                  previous[v] := u
	 17      return dist[]
	 */
	public List<Node> computeDistances(Node sourceNode, Node destNode) {
		resetGrid();
		sourceNode.setDistance(0);
		WorkQueue queue = getWorkQueue();
		while (queue.size() > 0) {
			Node n = queue.removeShortestDistance();
			for (Node neighbor : n.getNeighbors()) {
				int alt = n.getDistance() + 1;
				if (neighbor.getDistance() > alt) {
					neighbor.setDistance(alt);
					neighbor.setPrevious(n);
				}
			}
		}
		Stack<Node> shortestPath = new Stack<Node>();
		for (Node n = destNode; n != sourceNode; n = n.getPrevious())
			shortestPath.add(n);
		return shortestPath;
	}
}
