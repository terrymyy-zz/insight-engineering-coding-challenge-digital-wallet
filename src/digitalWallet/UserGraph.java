/**
 * 
 */
package digitalWallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mayaoyu
 *
 */
public class UserGraph {

	// private final int MAX_USERS = 75304;
	HashMap<Integer, Boolean> userList; // list of users
	HashMap<Integer, ArrayList<Integer>> adjMat; // adjacency matrix
	int nUsers; // current number of users
	Queue<Integer> userQueue;

	/**
	 * 
	 */
	public UserGraph() // constructor
	{
		userList = new HashMap<Integer, Boolean>();
		// adjacency matrix
		adjMat = new HashMap<Integer, ArrayList<Integer>>();
		nUsers = 0;
		userQueue = new LinkedList<Integer>();
	} // end constructor

	/**
	 * 
	 * @param id
	 */
	public void addUser(int id) {
		userList.put(id, false);
		ArrayList<Integer> newList = new ArrayList<Integer>();
		adjMat.put(id,newList);
	}

	/**
	 * 
	 * @param start
	 * @param end
	 */
	public void addConnection(int id1, int id2) {
		adjMat.get(id1).add(id2);
		adjMat.get(id2).add(id1);
	}

	public boolean bfs(int id1, int id2, int depth) // breadth-first search
	{// begin at user id1
		userList.put(id1, true); // mark it
		userQueue.add(id1); // add at tail
		int user2;
		int degree = 1;
		int flagUser = userQueue.peek();
		while (!userQueue.isEmpty()) { // until queue empty,
			int user1 = userQueue.remove(); // remove user at head
			// until it has no unvisited neighbors
			while ((user2 = getConnetedUnvisitedUser(user1)) != -1) {
				userList.put(user2, true);
				userQueue.add(user2);
				if (user2 == id2 && degree <= depth) {
					return true;
				} else if (degree > depth) {
					return false;
				}
			} // end while
			if (flagUser == user1) {
				flagUser = user2;
				degree++;
			}
		} // end while(queue not empty)

		// queue is empty, so we’re done
		for (int id : userList.keySet())
			userList.put(id,false);
		return true;
	} // end bfs()

	// returns an unvisited user connected to id
	public int getConnetedUnvisitedUser(int id) {
		for (int key : userList.keySet())
			if (adjMat.get(id).contains(key)
					&& userList.get(key) == false)
				return key;
		return -1;
	} // end getAdjUnvisitedVertex()

}
