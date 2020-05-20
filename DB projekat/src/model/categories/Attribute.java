package model.categories;

import model.DBNode;
import model.DBNodeComposite;
import model.enums.AttributeType;


public class Attribute extends DBNodeComposite{
	
	private AttributeType attributeType;
	private int lenght;
	private Attribute inRelationWith;
	
	public Attribute(String name, DBNode parent) {
		super(name, parent);
		// TODO Auto-generated constructor stub
	}
	
	public Attribute(String name, DBNode parent, AttributeType attrtype, int lenght) {
		super(name, parent);
		this.attributeType = attrtype;
		this.lenght = lenght;
	}
	@Override
	public void addChild(DBNode child) {
		// TODO Auto-generated method stub
		if (child != null && child instanceof AttributeConstraints){
            AttributeConstraints attributeConstraint = (AttributeConstraints) child;
            this.getChildren().add(attributeConstraint);
        }
	}
	
	
	public AttributeType getAttributeType() {
		return attributeType;
	}
	public int getLenght() {
		return lenght;
	}
	public Attribute getInRelationWith() {
		return inRelationWith;
	}
	
}
