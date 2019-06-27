package main;

public class Program {

	public static void main(String[] args) {				
		Menu.mainProgram();			
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
	
	
	public static void say(String text) {
		System.out.println(text);
	}

}
