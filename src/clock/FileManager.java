package clock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
	
	private static String filesOfDigit[]= {"zero.txt","one.txt","two.txt","three.txt",
			"four.txt","five.txt","six.txt","seven.txt","eight.txt","nine.txt"};
	
	public static ArrayList<Integer> readFromFile(int indexOfFile) {
		ArrayList<Integer>coordinates=new ArrayList<Integer>() ;
		
		try {

			int x,y;
			
			Scanner input = new Scanner(new File("digits/"+filesOfDigit[indexOfFile]));

			while ((input.hasNext())){
				
				x = input.nextInt();
				y = input.nextInt();
				coordinates.add(x);
				coordinates.add(y);
			}
			
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return coordinates;
	}
	
}
