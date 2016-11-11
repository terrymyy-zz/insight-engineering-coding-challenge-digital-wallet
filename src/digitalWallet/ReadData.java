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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author mayaoyu
 *
 */
public class ReadData {

	private Feature feat;

	/**
	 * 
	 */
	public ReadData() {
		feat = new Feature();
	}

	/**
	 * search for the transaction information {@link #readBatch()}
	 * 
	 * @return collection
	 * @throws ParseException
	 */
	public HashMap<Integer, ArrayList<Integer>> readMap(String fileName)
			throws ParseException {
		HashMap<Integer, ArrayList<Integer>> userMap = new HashMap<Integer, ArrayList<Integer>>();
		File file = new File(fileName);
		int counter = 0;
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String header = reader.readLine();
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				line = line.trim();
				if (line.equals(""))
					continue; // ignore possible blank lines
				String[] info = line.split(", ");
				if (info.length != 5)
					continue; // ignore none-transaction lines
				// String timeString = info[0];
				int id1 = Integer.parseInt(info[1]);
				int id2 = Integer.parseInt(info[2]);
				if (id1 > id2) {
					int temp = id1;
					id1 = id2;
					id2 = temp;
				}
				if (!userMap.containsKey(id1)) {
					ArrayList<Integer> newIDs = new ArrayList<Integer>();
					newIDs.add(id2);
					// System.out.println(""+id1+" "+id2);
					userMap.put(id1, newIDs);
				} else {
					if (!userMap.get(id1).contains(id2)) {
						// System.out.println(""+id1+" "+id2);
						userMap.get(id1).add(id2);
					}
				}
				// float amount = Float.parseFloat(info[3]);
				// String message = info[4];
				// Transaction newTran = new Transaction(timeString, id1, id2,
				// amount, message);
				// collection.add(newTran);
				counter++;
				// newTweet.print();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Finish reading " + counter + " transactions!");
		return userMap;
	}

	/**
	 * search for the transaction information {@link #readBatch()}
	 * 
	 * @return collection
	 * @throws ParseException
	 */
	public UserGraph readGraph(String fileName) throws ParseException {
		UserGraph userGraph = new UserGraph();
		File file = new File(fileName);
		int counter = 0;
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String header = reader.readLine();
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				line = line.trim();
				if (line.equals(""))
					continue; // ignore possible blank lines
				String[] info = line.split(", ");
				if (info.length != 5)
					continue; // ignore none-transaction lines
				// String timeString = info[0];
				int id1 = Integer.parseInt(info[1]);
				int id2 = Integer.parseInt(info[2]);
				if (id1 > id2) {
					int temp = id1;
					id1 = id2;
					id2 = temp;
				}
				if (!userGraph.userList.containsKey(id1))
					userGraph.addUser(id1);
				if (!userGraph.userList.containsKey(id2))
					userGraph.addUser(id2);
				userGraph.addConnection(id1, id2);
				// float amount = Float.parseFloat(info[3]);
				// String message = info[4];
				// Transaction newTran = new Transaction(timeString, id1, id2,
				// amount, message);
				// collection.add(newTran);
				counter++;
				// newTweet.print();
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Finish reading " + counter + " transactions!");
		return userGraph;
	}

	/**
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * 
	 */
	public ArrayList<Boolean> writeMap(String streamFile, String outputFile,
			HashMap<Integer, ArrayList<Integer>> userMap, int feature)
			throws ParseException, FileNotFoundException,
			UnsupportedEncodingException {
		ArrayList<Boolean> trust = new ArrayList<Boolean>();
		File file = new File(streamFile);
		PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
		int counter = 0;
		int counter2 = 0;
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String header = reader.readLine();
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				line = line.trim();
				if (line.equals(""))
					continue; // ignore possible blank lines

				String[] info = line.split(", ");
				if (info.length != 5)
					continue; // ignore none-transaction lines

				// String timeString = info[0];
				int id1 = Integer.parseInt(info[1]);
				int id2 = Integer.parseInt(info[2]);

				if (id1 > id2) {
					int temp = id1;
					id1 = id2;
					id2 = temp;
				}
				boolean result = false;
				if (feature == 1) {
					result = feat.feature1(id1, id2, userMap);
				} else if (feature == 2) {
					result = feat.feature2(id1, id2, userMap);
				}

				trust.add(result);
				if (result) {
					writer.println("trusted");
				} else {
					writer.println("unverified");
				}
				counter++;
				counter2++;
				if(counter2 >= 10000){
					System.out.println(counter/10000);
					counter2 = 0;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Finish writing " + counter + " transactions!");
		writer.close();
		return trust;
	}

	/**
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * 
	 */
	public ArrayList<Boolean> writeGraph(String streamFile, String outputFile,
			UserGraph userGraph) throws ParseException, FileNotFoundException,
			UnsupportedEncodingException {
		ArrayList<Boolean> trust = new ArrayList<Boolean>();
		File file = new File(streamFile);
		PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
		int counter = 0;
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String header = reader.readLine();
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				line = line.trim();
				if (line.equals(""))
					continue; // ignore possible blank lines

				String[] info = line.split(", ");
				if (info.length != 5)
					continue; // ignore none-transaction lines

				// String timeString = info[0];
				int id1 = Integer.parseInt(info[1]);
				int id2 = Integer.parseInt(info[2]);

				if (id1 > id2) {
					int temp = id1;
					id1 = id2;
					id2 = temp;
				}
				
				boolean result = feat.feature3(id1, id2, userGraph);

				trust.add(result);
				if (result) {
					writer.println("trusted");
				} else {
					writer.println("unverified");
				}
				counter++;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Finish writing " + counter + " transactions!");
		writer.close();
		return trust;
	}
}
