package main;

import java.util.Scanner;


public class Menu {

	public static Scanner scan = new Scanner(System.in);
	private static LibraryManager manager;
	
	public final static String DIVIDER = "======================================================";
	
	public final static String MAINMENU = "<1>Manage Books \n"
			+ "<2>Manage Authors \n"
			+ "<3>Manage Publishers \n"
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
			manager = new LibraryManager();
			runMainMenu();			
		}
		catch(Exception ex) {
		}
		finally {
			scan.close();
		}
		
	}
	
	private static void runMainMenu() {				
		boolean flag = true;
		while(flag) {
			Program.say(MAINMENU);	
			String choice = scan.nextLine();
			if(Program.tryParseInt(choice)) {
				int choiceNum = Integer.parseInt(choice);
				
				switch(choiceNum) {
					case 1:
						printDivider();
						runBookMenu();
					break;					
					case 2:
						printDivider();
						runAuthorMenu();
					break;
					case 3:
						printDivider();
						runPublisherMenu();
					break;					
					case 99:
						flag = false;
					break;
					default:
						Program.say("Not a valid option");						
					break;
				}								
			}
			else {
				Program.say("Invalid option");
				flag = true;
			}
		}					
	}
	
	private static void runBookMenu() {		
		boolean flag = true;
		while(flag) {
			Program.say(BOOKMENU);
			String choice = scan.nextLine();
			if(Program.tryParseInt(choice)) {
				int choiceNum = Integer.parseInt(choice);
				
				switch(choiceNum) {
					case 1:
						printDivider();
						manager.showBookList();						
					break;
					case 2:
						printDivider();
						manager.createBook();
					break;
					case 3:
						
						
					break;
					case 4:
					break;
					case 99:
						flag = false;
						printDivider();
						runMainMenu();
					break;
					default:
						Program.say("Not a valid option");						
					break;
				}								
			}
			else {
				Program.say("Not a valid option");				
			}
		}	
		
	}
	
	private static void runAuthorMenu() {		
		boolean flag = true;
		while(flag) {
			Program.say(AUTHORMENU);
			String choice = scan.nextLine();
			if(Program.tryParseInt(choice)) {
				int choiceNum = Integer.parseInt(choice);
				
				switch(choiceNum) {
					case 1:
						printDivider();
						manager.showAuthorList();						
					break;
					case 2:
						printDivider();
						manager.createAuthor();
					break;
					case 3:
						printDivider();
						manager.updateAuthor();
					break;
					case 4:
						printDivider();
						manager.deleteAuthor();
					break;
					case 99:
						flag = false;
						printDivider();
						runMainMenu();
					break;
					default:
						Program.say("Not a valid option");						
					break;
				}								
			}
			else {
				Program.say("Not a valid option");
			}
		}	
		
	}
	
	private static void runPublisherMenu() {
		boolean flag = true;
		while(flag) {
			Program.say(PUBLISHERMENU);
			String choice = scan.nextLine();
			if(Program.tryParseInt(choice)) {
				int choiceNum = Integer.parseInt(choice);
				
				switch(choiceNum) {
					case 1:
						printDivider();
						manager.showPublisherList();						
					break;
					case 2:
						printDivider();
						manager.createPublisher();
					break;
					case 3:
						printDivider();
						manager.updatePublisher();						
					break;
					case 4:
					break;
					case 99:
						flag = false;
						printDivider();
						runMainMenu();
					break;
					default:
						Program.say("Not a valid option");						
					break;
				}								
			}
			else {
				Program.say("Not a valid option");
			}
		}	
		
	}
	
	private static void printDivider() { Program.say(DIVIDER); }
}
