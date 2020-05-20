package model.categories;

import model.DBNode;
import model.enums.ConstraintType;

public class AttributeConstraints extends DBNode{
	
	private ConstraintType constraintType;
	
	public AttributeConstraints(String name, DBNode parent, ConstraintType constraintType) {
        super(name, parent);
        this.constraintType = constraintType;
    }
}
