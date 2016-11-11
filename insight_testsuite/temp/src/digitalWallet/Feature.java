/**
 * 
 */
package digitalWallet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author mayaoyu
 *
 */
public class Feature {

	private ArrayList<Boolean> trust;

	/**
	 * 
	 */
	public Feature() {
		trust = new ArrayList<Boolean>();
	}

	/**
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * 
	 */
	public boolean feature1(int id1, int id2,
			HashMap<Integer, ArrayList<Integer>> userMap)
			throws ParseException, FileNotFoundException,
			UnsupportedEncodingException {
		if (!userMap.containsKey(id1)) {
			// ArrayList<Integer> newIDs = new ArrayList<Integer>();
			// newIDs.add(id2);
			// userMap.put(id1, newIDs);
			return false;
		} else {
			if (!userMap.get(id1).contains(id2)) {
				// userMap.get(id1).add(id2);
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * 
	 */
	public boolean feature2(int id1, int id2,
			HashMap<Integer, ArrayList<Integer>> userMap)
			throws ParseException, FileNotFoundException,
			UnsupportedEncodingException {
		// System.out.println(userMap);
		if (!userMap.containsKey(id1)) {
			ArrayList<Integer> keys = new ArrayList<Integer>(userMap.keySet());
			// 4-1-5
			for (int key : keys) {
				if (userMap.get(key).contains(id1)
						&& userMap.get(key).contains(id2)) {
					// System.out.println("1!");
					return true;
				}
			}
			// new id come in
			// ArrayList<Integer> newIDs = new ArrayList<Integer>();
			// newIDs.add(id2);
			// userMap.put(id1, newIDs);
			// System.out.println("false!");
			return false;

		} else {
			if (userMap.get(id1).contains(id2)) {
				// 1st-order friend
				return true;
			} else {
				// 2nd-order friend
				ArrayList<Integer> id1friends = userMap.get(id1);
				for (int id : id1friends) {
					if (userMap.containsKey(id)
							&& userMap.get(id).contains(id2)) {
						// userMap.get(id1).add(id2);
						// System.out.println("true!");
						return true;
					}
				}
				// System.out.println(userMap);
				// beyond 2nd-order friend
				// userMap.get(id1).add(id2);
				// System.out.println(userMap);
				return false;
			}
		}
	}

	/**
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * 
	 */
	public boolean feature3(int id1, int id2, UserGraph userGraph) {
		if (!userGraph.userList.containsKey(id1))
			return false;
		if (!userGraph.userList.containsKey(id2))
			return false;
		// id1 and id2 are not new users:
		boolean result = userGraph.bfs(id1, id2, 4);
		userGraph.addConnection(id1, id2);
		return result;
	}

}
