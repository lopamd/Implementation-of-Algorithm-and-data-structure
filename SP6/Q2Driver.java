/**
 * 
 */
package cs6350.g45;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Driver method to read input and get the codes
 * @author Lopamudra 
 */

public class Q2Driver {
	public static void main(String[] args) {
		File f = new File(args[0]);
		ArrayList<String> inp = new ArrayList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			inp.add(sc.nextLine());
		}

		HuffmanCoding h = new HuffmanCoding(inp);

		h.buildHuffTree();
		h.readTree(h.huffCode.poll());

	}

}
