package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.models.*;

public class LibraryManager {

	public static List<Author> authorList = new ArrayList<Author>();
	public static List<Book> bookList = new ArrayList<Book>();
	public static List<Publisher> publisherList = new ArrayList<Publisher>();
	
	
	public void createAuthor() {
		Program.say("Please enter the name of the author");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		Author author = new Author(authorList.size() + 1, name);
		authorList.add(author);
		
		scan.close();
		
		FileDAO.writeAuthors();
		FileDAO.readAuthors();		
	}
	
	public void createPublisher() {
		Program.say("Please enter the name of the publisher");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		
		Publisher pub = new Publisher(publisherList.size() + 1, name);
		publisherList.add(pub);
		
		scan.close();
		
		FileDAO.writePublishers();
		FileDAO.readPublishers();
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
	
	
	
	
}
