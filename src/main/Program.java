package main;

import java.util.Scanner;

import main.models.*;

public class Program {

	public static void main(String[] args) {		
		FileDAO.initDAO();
		
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
