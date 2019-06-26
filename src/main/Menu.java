package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.models.*;

public class Menu {

	private static Scanner scan = new Scanner(System.in);
	private static LibraryManager manager = new LibraryManager();
	
	public final static String DIVIDER = "======================================================";
	
	public final static String MAINMENU = "<1>Manage Books \n"
			+ "<2>Manage Authors \n"
			+ "<3>Manage Publishers \n "
			+ "<99>Exit Program";
	
	public final static String BOOKMENU = "<1>View all Book \n"
			+ "<2>Add a Book \n"
			+ "<3>Update a Book \n"
			+ "<4>Delete a Book \n"
			+ "<99>Go Back";
	
	public final static String AUTHORMENU = "<1>View all Authors \n"
			+ "<2>Add an Author \n"
			+ "<3>Update an Author \n"
			+ "<4>Delete an Author \n"
			+ "<99>Go Back";
	
	public final static String PUBLISHERMENU = "<1>View all Publishers \n"
			+ "<2>Add a Publisher \n"
			+ "<3>Update a Publisher \n"
			+ "<4>Delete a Publisher \n"
			+ "<99>Go Back";
	
	public static void mainProgram() {
		try {
			runMainMenu();
			
		}
		catch(Exception ex) {
		}
		finally {
			scan.close();
		}
		
	}
	
	private static void runMainMenu() {
		Program.say(MAINMENU);	
		
		int choice = scan.nextInt();
		
		if(choice == 1) {
			runBookMenu();
		}
		else if(choice == 2) {
			
		}
		else if(choice == 3) {
			
		}
		else if(choice == 4) {
			
		}
		else if(choice == 99) {
			
		}
		else {
			Program.say("Invalid Input");
			runMainMenu();
		}		
	}
	
	private static void runBookMenu() {
		Program.say(BOOKMENU);
		
		int choice = scan.nextInt();
		
		if(choice == 1) {
			manager. showBookList();
			printDivider();
			runBookMenu();
		}
		else if(choice == 2) {
			
		}
		else if(choice == 3) {
			
		}
		else if(choice == 4) {
			
		}
		else if(choice == 99) {
			printDivider();
			runMainMenu();
		}
		else {
			Program.say("Invalid Input");
			runBookMenu();
		}
		
	}
	
	private static void printDivider() { Program.say(DIVIDER); }
}
