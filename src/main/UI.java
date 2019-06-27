package main;

import java.util.Scanner;

public class UI {

	public static Scanner scan = new Scanner(System.in);
	
	public static String readLine() {
		return scan.nextLine(); 
	}
	
	public static int readInt() {
		String input = scan.nextLine();
		
		if(tryParseInt(input)) {
			return Integer.parseInt(input);
		}
		else {
			return -1;
		}
	}	
	
	public static boolean tryParseInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public static void badInput() {
		say("Invalid option");
	}
	
	public static void say(String text) {
		System.out.println(text);
	}
	
	public static void closeScanner() {
		scan.close();
	}
	
}
