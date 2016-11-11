package digitalWallet;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ReadDataTest {

	String test1;
	String test2;
	String test3;
	String test4;
	String output1;
	String output2;
	HashMap<Integer, ArrayList<Integer>> userMap1;
	HashMap<Integer, ArrayList<Integer>> userMap2;
	ReadData reader = new ReadData();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		test1 = "../DigitWallet/paymo_input/test1.csv";
		test2 = "../DigitWallet/paymo_input/test2.csv";
		output1 = "../DigitWallet/paymo_output/test1out.txt";
		userMap1 = reader.readMap(test1);
		test3 = "../DigitWallet/paymo_input/test3.csv";
		test4 = "../DigitWallet/paymo_input/test4.csv";
		output2 = "../DigitWallet/paymo_output/test2out.txt";
		userMap2 = reader.readMap(test3);
	}

	/**
	 * Test method for {@link digitalWallet.Feature#read(java.lang.String)}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testReadMap() throws ParseException {
		Set keys1 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 6));
		assertEquals(userMap1.keySet(), keys1);

		ArrayList<Integer> id1friends = new ArrayList<Integer>();
		id1friends.add(2);
		id1friends.add(3);
		id1friends.add(4);
		id1friends.add(5);
		Collections.sort(userMap1.get(2));
		assertEquals(userMap1.get(1), id1friends);

		ArrayList<Integer> id2friends = new ArrayList<Integer>();
		id2friends.add(3);
		id2friends.add(5);
		Collections.sort(userMap1.get(2));
		assertEquals(userMap1.get(2), id2friends);

		ArrayList<Integer> id3friends = new ArrayList<Integer>();
		id3friends.add(6);
		assertEquals(userMap1.get(3), id3friends);

		ArrayList<Integer> id6friends = new ArrayList<Integer>();
		id6friends.add(7);
		assertEquals(userMap1.get(6), id6friends);
		
		
		Set keys2 = new HashSet<Integer>(Arrays.asList(1,2));
		assertEquals(userMap2.keySet(), keys2);

		ArrayList<Integer> id1friends1 = new ArrayList<Integer>();
		id1friends1.add(2);
		Collections.sort(userMap2.get(2));
		assertEquals(userMap2.get(1), id1friends1);

		ArrayList<Integer> id2friends1 = new ArrayList<Integer>();
		id2friends1.add(3);
		Collections.sort(userMap2.get(2));
		assertEquals(userMap2.get(2), id2friends1);
	}

	/**
	 * Test method for
	 * {@link digitalWallet.Feature#write(java.lang.String, java.lang.String, java.util.HashMap)}
	 * .
	 * 
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	@Test
	public final void testWriteMap() throws FileNotFoundException,
			UnsupportedEncodingException, ParseException {
		ArrayList<Boolean> trust1 = reader.writeMap(test2, output1, userMap1,1);
		ArrayList<Boolean> result1 = new ArrayList<Boolean>();
		result1.add(true);
		result1.add(true);
		result1.add(false);
		result1.add(false);
		result1.add(false);
		result1.add(false);
		assertEquals(trust1, result1);
		
		ArrayList<Boolean> trust2 = reader.writeMap(test4, output2, userMap2,2);
		ArrayList<Boolean> result2 = new ArrayList<Boolean>();
		result2.add(false);
		result2.add(false);
		result2.add(true);
		result2.add(false);
		result2.add(false);
		result2.add(true);
		result2.add(true);
		assertEquals(trust2, result2);
	}
	

}
