package temp;

import java.util.Date;

public class GenerateEmailDemo {

	public static void main(String[] args) {
		
		Date date = new Date();
		String toString = date.toString();
		String removeSpace = toString.replaceAll("\\s", "");
		String removeCollon = removeSpace.replaceAll("\\:", "");
		System.out.println(removeCollon);
		String emailadd = removeCollon + "@gmail.com";
		
		
		
	}
	
}
