package model;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;


@ToString(callSuper = true)
public abstract class DBNodeComposite extends DBNode{
	
	private List<DBNode> children;
	
	public DBNodeComposite(String name, DBNode parent) {
		super(name, parent);
		// TODO Auto-generated constructor stub
		this.children = new ArrayList<>();
	}
	
	public abstract void addChild(DBNode child);
	
	public DBNode getChildByName(String name) {
		for (DBNode child: this.getChildren()) {
			if(name.equals(child.getName())) {
				return child;
			}
		}
		
		return null;
	}
	
	public List<DBNode> getChildren() {
		return children;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
