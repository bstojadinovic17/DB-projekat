package model;


public abstract class DBNode {

	private String name;
	private DBNode parent;
	
	public DBNode(String name, DBNode parent) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
}
