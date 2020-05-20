package model.categories;

import model.DBNode;
import model.DBNodeComposite;

public class Table extends DBNodeComposite{

	public Table(String name, DBNode parent) {
		super(name, parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addChild(DBNode child) {
		// TODO Auto-generated method stub
		if (child != null && child instanceof Attribute){
            Attribute attribute = (Attribute) child;
            this.getChildren().add(attribute);
        }
	}

}
