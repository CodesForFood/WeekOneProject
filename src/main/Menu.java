package main;

public class Menu {

	
	private static LibraryManager manager;
	
	public final static String DIVIDER = "======================================================";
	
	public final static String MAINMENU = "<1>Manage Books \n"
			+ "<2>Manage Authors \n"
			+ "<3>Manage Publishers \n"
			+ "<99>Exit Program";
	
	public final static String BOOKMENU = "<1>View all Book \n"
			+ "<2>Get single book by name \n"
			+ "<3>Add a Book \n"
			+ "<4>Update a Book \n"
			+ "<5>Delete a Book \n"
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
			UI.closeScanner();
		}
		
	}
	
	private static void runMainMenu() {				
		boolean flag = true;
		while(flag) {
			UI.say(MAINMENU);	
			int choiceNum = UI.readInt();				
			switch(choiceNum) {
				case 1:
					flag = false;
					printDivider();
					runBookMenu();
				break;					
				case 2:
					flag = false;
					printDivider();
					runAuthorMenu();
				break;
				case 3:
					flag = false;
					printDivider();
					runPublisherMenu();
				break;					
				case 99:
					flag = false;
				break;
				default:
					flag = true;
					UI.badInput();					
				break;
			}								
		}				
	}
	
	private static void runBookMenu() {		
		boolean flag = true;
		while(flag) {
			UI.say(BOOKMENU);	
			
			int choiceNum = UI.readInt();
			
			switch(choiceNum) {
				case 1:
					printDivider();
					if(LibraryManager.bookList.size() <= 0) {
						UI.emptyList(LibraryManager.BOOK);
					}
					else {
						manager.showBookList();	
					}						
					printDivider();
					break;
				case 2:
					printDivider();
					manager.displayBookByName();
					break;
				case 3:
					printDivider();
					manager.createBook();
					break;
				case 4:					
					printDivider();
					if(LibraryManager.bookList.size() <= 0) {
						UI.emptyList(LibraryManager.BOOK);
					}
					else {
						manager.updateBook();	
					}															
					break;
				case 5:					
					printDivider();					
					if(LibraryManager.bookList.size() <= 0) {
						UI.emptyList(LibraryManager.BOOK);
					}
					else {
						manager.deleteBook();	
					}
					break;
				case 99:
					flag = false;
					printDivider();
					runMainMenu();
				break;
				default:
					UI.say("Not a valid option");						
					break;
			}					
		}		
	}
	
	private static void runAuthorMenu() {		
		boolean flag = true;
		while(flag) {
			UI.say(AUTHORMENU);
			
			int choiceNum = UI.readInt();
			
			switch(choiceNum) {
				case 1:
					printDivider();
					if(LibraryManager.authorList.size() <= 0) {
						UI.emptyList(LibraryManager.AUTHOR);
					}
					else {
						manager.showAuthorList();
					}
					printDivider();
					break;
				case 2:					
					printDivider();
					manager.createAuthor();
					break;
				case 3:					
					printDivider();
					if(LibraryManager.authorList.size() <= 0) {
						UI.emptyList(LibraryManager.AUTHOR);
					}
					else {
						manager.updateAuthor();
					}					
					break;
				case 4:
					printDivider();
					if(LibraryManager.authorList.size() <= 0) {
						UI.emptyList(LibraryManager.AUTHOR);
					}
					else {
						manager.deleteAuthor();	
					}				
					break;
				case 99:
					flag = false;
					printDivider();
					runMainMenu();
					break;
				default:
					UI.say("Not a valid option");						
					break;
			}								
		}		
	}
	
	private static void runPublisherMenu() {
		boolean flag = true;
		while(flag) {
			UI.say(PUBLISHERMENU);
			int choiceNum = UI.readInt();
				
			switch(choiceNum) {
				case 1:
					printDivider();
					if(LibraryManager.authorList.size() <= 0) {
						UI.emptyList(LibraryManager.AUTHOR);
					}
					else {
						manager.showPublisherList();
					}
					printDivider();
					break;
				case 2:
					printDivider();
					manager.createPublisher();
					break;
				case 3:
					printDivider();
					if(LibraryManager.publisherList.size() <= 0) {
						UI.emptyList(LibraryManager.PUBLISHER);
					}
					else {
						manager.updatePublisher();
					}											
					break;
				case 4:
					printDivider();
					if(LibraryManager.publisherList.size() <= 0) {
						UI.emptyList(LibraryManager.PUBLISHER);
					}
					else {
						manager.deletePublisher();
					}
					break;
				case 99:
					flag = false;
					printDivider();
					runMainMenu();
					break;
				default:
					UI.say("Not a valid option");						
					break;
			}		
		}			
	}
	
	private static void printDivider() { UI.say(DIVIDER); }
}
