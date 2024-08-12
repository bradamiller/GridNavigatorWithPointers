import java.util.LinkedList;

/**
 * WorkQueue object is a Queue of Nodes as they are processed.
 * A queue is simply a sequential list of Nodes that are processed one at a time. It
 * is implemented as a LinkedList, but could also be an ArrayList.
 * @author brad
 *
 */
public class WorkQueue {
	
	private LinkedList<Node> list;
	
/**
 * Constuctor for the WorkQueue
 */
	public WorkQueue() {
		list = new LinkedList<Node>();
	}
	
/**
 * Add a Node to the WorkQueue.
 * All the Nodes in the Grid will be added initially, then they'll be removed as the
 * algorithm considers each node on the grid.
 * @param n the Node to add
 */
	public void add(Node n) {
		list.add(n);
	}
	
/**
 * Remove the Node with the shortest distance.
 * The algorithm requires visiting all the nodes in the grid in order current shortest
 * distance. This simply searches the list for the one with the shortest distance, returns
 * it, and removes it from the list. That way it won't be processed a second time.
 * @return the Node with the shortest distance value.
 */
	public Node removeShortestDistance() {
		int shortestDistance = Node.infinity;
		Node bestNode = null;
		if (list.size() == 0) return null;
		for (Node n: list) {
			if (n.getDistance() < shortestDistance) {
				shortestDistance = n.getDistance();
				bestNode = n;
			}
		}
		list.remove(bestNode);
		return bestNode;
	}
	
	public int size() {
		return list.size();
	}
}
