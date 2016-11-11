/**
 * 
 */
package digitalWallet;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import digitalWallet.ReadData;

/**
 * @author mayaoyu
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException, ParseException {
		// read in raw data
		ReadData reader = new ReadData();
		// File dir = new File("../paymo_output/");
		// dir.mkdir();
		String batchFile = "../paymo_input/batch_payment.txt";
		String streamFile = "../paymo_input/stream_payment.txt";
		String outputFile1 = "../paymo_output/output1.txt";
		String outputFile2 = "../paymo_output/output2.txt";
		String outputFile3 = "../paymo_output/output3.txt";

		// Feature 1
		System.out.println("Feature 1:");

		System.out.println("reading batch_payment.csv....");
		HashMap<Integer, ArrayList<Integer>> batchUserMap = reader
				.readMap(batchFile);
		System.out.println("Finish!");

		System.out
				.println("reading stream_payment.csv and write output file....");
		reader.writeMap(streamFile, outputFile1, batchUserMap, 1);
		System.out.println(batchUserMap.size());
		System.out.println("Finish!");

		// Feature 2
		System.out.println("Feature 2:");
		long startTime = System.currentTimeMillis();
		System.out.println("reading batch_payment.csv....");
		HashMap<Integer, ArrayList<Integer>> batchUser2 = reader
				.readMap(batchFile);
		System.out.println("Finish!");

		System.out
				.println("reading stream_payment.csv and write output file....");
		reader.writeMap(streamFile, outputFile2, batchUser2, 2);
		System.out.println("Finish!");
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		// 872530 ms

		// Feature 3
		System.out.println("Feature 3:");
		startTime = System.currentTimeMillis();

		System.out.println("reading batch_payment.csv....");
		UserGraph batchUser3 = reader.readGraph(batchFile);
		System.out.println("Finish!");
		endTime = System.currentTimeMillis();
	    totalTime = endTime - startTime;
		System.out.println(totalTime);

		System.out
				.println("reading stream_payment.csv and write output file....");
		reader.writeGraph(streamFile, outputFile3, batchUser3);
		System.out.println("Finish!");
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println(totalTime);
	}

}
