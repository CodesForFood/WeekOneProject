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
			UI.say(auth.getId() + ") " + auth.getName());
		}
	}
	
	public void showPublisherList() {
		for(Publisher pub : publisherList) {
			UI.say(pub.getId() + ") " + pub.getName());
		}
	}
	
	public void showBookList() {
		for(Book book : bookList) {
			final String authName = book.getAuthor().getName();
			final String pubName = book.getPublisher().getName();		
			
			UI.say(book.getId() + ") " + book.getName() + ", Author: " + authName + ", Publisher: " + pubName);
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
		UI.say("Please enter the name of the author");	
		String name = UI.readLine();
		Author author = new Author(getMaxId(AUTHOR)+ 1, name);
		authorList.add(author);			
		
		fDAO.updateAuthors();
		
		return author;
	}
	
	public Publisher createPublisher() {
		UI.say("Please enter the name of the publisher");
		String name = UI.readLine();
		
		Publisher pub = new Publisher(getMaxId(PUBLISHER) + 1, name);
		publisherList.add(pub);		
		
		fDAO.updatePublishers();
		
		return pub;
	}
	
	public Book createBook() {
		UI.say("Please enter the name of the book");
		String name = UI.readLine();
		
		Author auth = newOrOldAuthor();
		Publisher pub = newOrOldPublisher();
		
		Book book = new Book(getMaxId(BOOK) + 1, name, auth, pub);
		bookList.add(book);
		
		fDAO.updateBooks();
		
		return book;
	}	
	
	public void updateBook() {
		boolean flag = true;
		while(flag) {
			UI.say("Which book do you want to update?");
			showBookList();		
			
			int choice = UI.readInt();
			
			Book book = getBookById(choice);
			if(book.getId() == 0) {
				UI.say("Not a valid option");
				continue;
			}
			UI.say("Which do you want to change? \n<1>Book's name \n<2>Author of book \n<3>Publisher of book");
			choice = UI.readInt();
				
			if(choice == 1) {
				UI.say("What is the new name of the book?");
				String name = UI.readLine();
				book.setName(name);
				flag = false;
				fDAO.updateBooks();
			}
			else if(choice == 2) {
				Author auth = promptGetAuthor();
				book.setAuthor(auth);
				flag = false;
				fDAO.updateBooks();
			}
			else if(choice == 3) {
				Publisher pub = promptGetPublisher();
				book.setPublisher(pub);
				flag = false;
				fDAO.updateBooks();
			}
			else {
				UI.badInput();
				flag = true;
			}
		}
	}
	
	private Author promptGetAuthor() {
		boolean flag = true;
		Author auth = new Author();
		while(flag) {
			UI.say("Which author do you want to use?");
			showAuthorList();		
			int choice = UI.readInt();
			auth = getAuthorById(choice);
			if(auth.getId() == 0) {
				UI.badInput();
				flag = true;
				continue;
			}
			else {
				flag = false;
			}					
		}
		return auth;		
	}
	
	private Publisher promptGetPublisher() {
		boolean flag = true;
		Publisher pub = new Publisher();
		
		while(flag) {
			UI.say("Which publisher do you want to use?");
			showPublisherList();			
			int choice = UI.readInt();
			pub = getPublisherById(choice);
			if(pub.getId() == 0) {
				UI.badInput();
				flag = true;
				continue;
			}
			else {
				flag = false;					
			}					
		}
		return pub;		
	}
	
	public void updateAuthor() {
		boolean flag = true;
		
		while(flag) {
			UI.say("Which Author do you want to edit?");
			showAuthorList();	
			
			int choice = UI.readInt();
			Author auth = getAuthorById(choice);
			
			if(auth.getId() == 0) {
				UI.badInput();
				flag = true;
				continue;
			}
			else {
				UI.say("What is the new name of the Author?");
				String name = UI.readLine();
				auth.setName(name);			
				
				fDAO.updateAuthors();
				flag = false;
			}						
		}		
	}
	
	public void updatePublisher() {
		boolean flag = true;
		
		while(flag) {
			UI.say("Which Publisher do you want to edit?");
			showPublisherList();			
		
			int choice = UI.readInt();
			Publisher pub = getPublisherById(choice);
			if(pub.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				UI.say("What is the new name of the Publisher?");
				String name = UI.readLine();
				pub.setName(name);			
				
				fDAO.updatePublishers();
				flag = false;
			}				
		}		
	}
	
	public void deleteAuthor() {
		boolean flag = true;
		while(flag) {
			UI.say("Which author do you want to delete?");
			showAuthorList();
			
			int choice = UI.readInt();
			Author auth = getAuthorById(choice);
			
			if(auth.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				authorList.remove(auth);
				for(Book book : bookList) {
					if(book.getAuthor().getId() == choice) {
						book.setAuthor(new Author());
					}
				}
				fDAO.updateAuthors();
				fDAO.updateBooks();
				flag = false;	
			}		
		}
	}
	
	public void deletePublisher() {
		boolean flag = true;
		while(flag) {
			UI.say("Which publisher do you want to delete?");
			showPublisherList();			
			
			int choice = UI.readInt();
			Publisher pub = getPublisherById(choice);
			if(pub.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				publisherList.remove(pub);
				for(Book book : bookList) {
					if(book.getPublisher().getId() == choice) {
						book.setPublisher(new Publisher());
					}
				}
				fDAO.updatePublishers();
				fDAO.updateBooks();
				flag = false;	
			}	
		}	
	}
	
	public void deleteBook() {
		boolean flag = true;
		while(flag) {
			UI.say("Which book do you want to delete?");
			showBookList();			
			
			int choice = UI.readInt();
			Book book = getBookById(choice);
			if(book.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				bookList.remove(book);
				fDAO.updateBooks();			
				flag = false;	
			}			
		}			
	}
	
	
	private Publisher newOrOldPublisher() {
		boolean flag = true;
		Publisher pub = new Publisher();
		
		while(flag) {
			UI.say("Would you like to \n<1>Create new Publisher \n<2>Select from an existing one");		
			int choice = UI.readInt();				
			
			if(choice == 1) {
				flag = false;
				pub = createPublisher();				
			}
			else if(choice == 2) {
				flag = false;
				showPublisherList();
				int numChoice = UI.readInt();
				pub = getPublisherById(numChoice);
				
				if(pub.getId() == 0) {
					UI.badInput();
					continue;
				}
				else {
					flag = false;					
				}				
			}		
			else {
				UI.say("Not a valid choice");
				flag = true;
			}					
			
		}	
		return pub;
	}
	
	
	private Author newOrOldAuthor() {
		boolean flag = true;
		Author auth = new Author();
		while(flag) {
			UI.say("Would you like to \n<1>Create new Author \n<2>Select from an existing one");		
		
			int choiceNum = UI.readInt();
			
			if(choiceNum == 1) {
				flag = false;
				auth = createAuthor();				
			}
			else if(choiceNum == 2) {
				showAuthorList();				
				int numChoice = UI.readInt();
				auth = getAuthorById(numChoice);
				
				if(auth.getId() == 0) {
					UI.badInput();
					continue;
				}
				else {
					flag = false;
				}					
			}	
			else {
				UI.badInput();
				continue;
			}		
		}
		return auth;
	}
	
	
}
