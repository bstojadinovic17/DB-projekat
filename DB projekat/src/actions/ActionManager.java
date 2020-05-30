package actions;

public class ActionManager {
	
	private AddAction addAction;
	private DeleteAction deleteAction;
	public ActionManager() {
		initialization();
	}

	public void initialization() {
		// TODO Auto-generated method stub
		addAction = new AddAction();
		deleteAction = new DeleteAction();
	}
	
	public AddAction getAddAction() {
		return addAction;
	}
	public DeleteAction getDeleteAction() {
		return deleteAction;
	}
}
