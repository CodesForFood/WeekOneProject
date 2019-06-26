package main.models;

public class Publisher {	

	private int id = 0;
	private String name = "No Publisher";
	
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }	
	
	
	public Publisher() {}
	public Publisher(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
