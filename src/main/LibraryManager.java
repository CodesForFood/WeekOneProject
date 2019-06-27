package main;

import java.util.ArrayList;
import java.util.List;
import main.models.*;

public class LibraryManager {

	public static List<Author> authorList = new ArrayList<Author>();
	public static List<Book> bookList = new ArrayList<Book>();
	public static List<Publisher> publisherList = new ArrayList<Publisher>();
	
	public static final String AUTHOR = "Author";
	public static final String BOOK = "Book";
	public static final String PUBLISHER = "Publisher";
	
	FileDAO fDAO;
	
	public LibraryManager() {
		fDAO = new FileDAO();
	}		
	
	public void showAuthorList() {
		for(Author auth : authorList) {
			Program.say(auth.getId() + ") " + auth.getName());
		}
	}
	
	public void showPublisherList() {
		for(Publisher pub : publisherList) {
			Program.say(pub.getId() + ") " + pub.getName());
		}
	}
	
	public void showBookList() {
		for(Book book : bookList) {
			final String authName = book.getAuthor().getName();
			final String pubName = book.getPublisher().getName();		
			
			Program.say(book.getId() + ") " + book.getName() + ", Author: " + authName + ", Publisher: " + pubName);
		}
	}
	
	private int getMaxId(String type) {
		int max = 0;
		switch(type){
			case AUTHOR:
				for(Author auth : authorList) {
					if(auth.getId() > max) {
						max = auth.getId();
					}
				}
			break;
			case BOOK:
				for(Book book : bookList) {
					if(book.getId() > max) {
						max = book.getId();
					}
				}
			break;
			case PUBLISHER:
				for(Publisher pub : publisherList) {
					if(pub.getId() > max) {
						max = pub.getId();
					}
				}
			break;
		}
		return max;
	}
	
	public static Author getAuthorById(int id) {
		for(Author auth : authorList) {
			if(auth.getId() == id) {
				return auth;
			}
		}
		return new Author(0,"No Author");
	}
	
	public static Publisher getPublisherById(int id) {
		for(Publisher pub : publisherList) {
			if(pub.getId() == id) {
				return pub;
			}
		}
		return new Publisher(0, "No Publisher");
	}
	
	public static Book getBookById(int id) {
		for(Book book : bookList) {
			if(book.getId() == id) {
				return book;
			}
		}
		return new Book(0, "No Book", new Author(), new Publisher());
	}	

	public Author createAuthor() {
		Program.say("Please enter the name of the author");	
		String name = Menu.scan.nextLine();
		Author author = new Author(getMaxId(AUTHOR)+ 1, name);
		authorList.add(author);			
		
		fDAO.updateAuthors();
		
		return author;
	}
	
	public Publisher createPublisher() {
		Program.say("Please enter the name of the publisher");

		String name = Menu.scan.nextLine();
		
		Publisher pub = new Publisher(getMaxId(PUBLISHER) + 1, name);
		publisherList.add(pub);		
		
		fDAO.updatePublishers();
		
		return pub;
	}
	
	public Book createBook() {
		Program.say("Please enter the name of the book");
		String name = Menu.scan.nextLine();
		
		Author auth = newOrOldAuthor();
		Publisher pub = newOrOldPublisher();
		
		Book book = new Book(getMaxId(BOOK) + 1, name, auth, pub);
		bookList.add(book);
		
		fDAO.updateBooks();
		
		return book;
	}	
	
	
	public void updateAuthor() {
		boolean flag = true;
		
		while(flag) {
			Program.say("Which Author do you want to edit?");
			showAuthorList();	
			
			String input = Menu.scan.nextLine();
			if(Program.tryParseInt(input)) {
				int choice = Integer.parseInt(input);
				Author auth = getAuthorById(choice);
				Program.say("What is the new name of the Author?");
				String name = Menu.scan.nextLine();
				auth.setName(name);			
				
				fDAO.updateAuthors();
				flag = false;
			}
			else {
				Program.say("Invalid input");
				flag = true;
			}			
		}		
	}
	
	public void updatePublisher() {
		boolean flag = true;
		
		while(flag) {
			Program.say("Which Publisher do you want to edit?");
			showPublisherList();	
			
			String input = Menu.scan.nextLine();
			if(Program.tryParseInt(input)) {
				int choice = Integer.parseInt(input);
				Publisher pub = getPublisherById(choice);
				Program.say("What is the new name of the Publisher?");
				String name = Menu.scan.nextLine();
				pub.setName(name);			
				
				fDAO.updatePublishers();
				flag = false;
			}
			else {
				Program.say("Invalid input");
				flag = true;
			}			
		}		
	}
	
	public void deleteAuthor() {
		boolean flag = true;
		while(flag) {
			Program.say("Which author do you want to delete?");
			showAuthorList();
			
			String input = Menu.scan.nextLine();
			
			if(Program.tryParseInt(input)) {
				int choice = Integer.parseInt(input);
				authorList.remove(getAuthorById(choice));
				for(Book book : bookList) {
					if(book.getAuthor().getId() == choice) {
						book.setAuthor(new Author());
					}
				}
				fDAO.updateAuthors();
				fDAO.updateBooks();
				flag = false;
			}
			else {
				Program.say("Invalid option");				
			}
		}
		
	}
	
	
	private Publisher newOrOldPublisher() {
		boolean flag = true;
		while(flag) {
			Program.say("Would you like to \n<1>Create new Publisher \n<2>Select from an existing one");		
			String input = Menu.scan.nextLine();								
			
			if(Program.tryParseInt(input)) {
				int choice = Integer.parseInt(input);
				
				if(choice == 1) {
					flag = false;
					return createPublisher();
					
				}
				else if(choice == 2) {
					flag = false;
					showPublisherList();
					String strChoice = Menu.scan.nextLine();
					if(Program.tryParseInt(strChoice)) {
						int numChoice = Integer.parseInt(strChoice);
						return getPublisherById(numChoice);
					}
					else {
						Program.say("Not a valid choice");
						flag = true;
					}
									
				}	
				else {
					Program.say("Not a valid choice");
					flag = true;
				}					
			}
			else {
				Program.say("Not a valid choice");
				flag= true;
			}
		}	
		return new Publisher();
	}
	
	
	private Author newOrOldAuthor() {
		boolean flag = true;
		while(flag) {
			Program.say("Would you like to \n<1>Create new Author \n<2>Select from an existing one");		
			String choiceOne = Menu.scan.nextLine();								
			
			if(Program.tryParseInt(choiceOne)) {
				int choiceNum = Integer.parseInt(choiceOne);
				
				if(choiceNum == 1) {
					flag = false;
					return createAuthor();
				
				}
				else if(choiceNum == 2) {
					flag = false;
					showAuthorList();
					String strChoice = Menu.scan.nextLine();
					if(Program.tryParseInt(strChoice)) {
						int numChoice = Integer.parseInt(strChoice);
						return getAuthorById(numChoice);
					}
					else {
						Program.say("Not a valid choice");
						flag = true;
					}			
				}	
				else {
					Program.say("Not a valid choice");
					flag = true;
				}
					
			}
			else {
				Program.say("Not a valid choice");
				flag = true;
			}
		}
		return new Author();
	}
	
	
}
