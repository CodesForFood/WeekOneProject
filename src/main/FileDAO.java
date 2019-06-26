package main;

import java.io.*;
import java.util.Scanner;
import main.models.*;

public class FileDAO {

	public final static String PATH = "src/main/resources/";	
	
	private static File authorFile;
	private static File bookFile;
	private static File publisherFile;	
	
	public static void initDAO() {
		authorFile = new File(PATH + "Author.csv");
		bookFile = new File(PATH + "Book.csv");
		publisherFile = new File(PATH + "Publisher.csv");		
		
		readAll();
	}	
	
	public static File getAuthorFile() { return authorFile; }
	public static File getBookFile() { return bookFile; } 
	public static File getPublisherFile() { return publisherFile; }
	
	
	public static void readAll() {
		readAuthors();
		readPublishers();
		readBooks();
	}
	
	public static void readAuthors() {		
		try {				
			if(getAuthorFile().exists()) {
				Scanner scan = new Scanner(getAuthorFile());
				LibraryManager.authorList.clear();
				
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					String[] delimitedLine = line.split(",");
					
					Author author = new Author(Integer.parseInt(delimitedLine[0]), delimitedLine[1]);
					
					LibraryManager.authorList.add(author);								
				}
				scan.close();			
			}							
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
	public static void readPublishers() {
		try {			
			if(getPublisherFile().exists()) {
				Scanner scan = new Scanner(getPublisherFile());
				LibraryManager.publisherList.clear();
				
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					String[] delimitedLine = line.split(",");
					
					Publisher publisher = new Publisher(Integer.parseInt(delimitedLine[0]), delimitedLine[1]);
					
					LibraryManager.publisherList.add(publisher);								
				}
				scan.close();			
			}					
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public static void readBooks() {
		try {						
			if(getBookFile().exists()) {
				Scanner scan = new Scanner(getBookFile());
				LibraryManager.bookList.clear();
				
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					String[] delimitedLine = line.split(",");
					
					int id = Integer.parseInt(delimitedLine[0]);
					String name = delimitedLine[1];					
					Author author = LibraryManager.getAuthorById(Integer.parseInt(delimitedLine[2]));
					Publisher publisher = LibraryManager.getPublisherById(Integer.parseInt(delimitedLine[3]));
					
					Book book = new Book(id, name, author, publisher);
					LibraryManager.bookList.add(book);
				}
				scan.close();				
			}					
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	public static void writeAuthors() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getAuthorFile()));			
			for(Author auth : LibraryManager.authorList) {
				final String line = auth.getId() + "," + auth.getName();
				writer.write(line);
				writer.newLine();				
			}
			writer.close();						
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();			
		}	
	}
	
	public static void writePublishers() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getPublisherFile()));
			for(Publisher pub : LibraryManager.publisherList) {
				final String line = pub.getId() + "," + pub.getName();
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public static void writeBooks() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getBookFile()));
			for(Book book : LibraryManager.bookList) {
				final String line = book.getId() + "," + book.getName() + "," + book.getAuthor().getId() + "," + book.getPublisher().getId();
				writer.write(line);
				writer.newLine();				
			}
			writer.close();
		}
		catch(Exception ex) {
			Program.say(ex.getMessage());
		}
	}
	
	
	
}
	
