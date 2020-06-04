package actions;

public class ActionManager {
	
	private AddAction addAction;
	private DeleteAction deleteAction;
	private UpdateAction updateAction;
	private SortAction sortAction;
	private CountAction countAction;
	private AverageAction averageAction;
	private RefreshAction refreshAction;
	public ActionManager() {
		initialization();
	}

	public void initialization() {
		// TODO Auto-generated method stub
		addAction = new AddAction();
		deleteAction = new DeleteAction();
		updateAction = new UpdateAction();
		sortAction = new SortAction();
		countAction = new CountAction();
		averageAction = new AverageAction();
		refreshAction = new RefreshAction();
	}
	
	public AddAction getAddAction() {
		return addAction;
	}
	public DeleteAction getDeleteAction() {
		return deleteAction;
	}
	public RefreshAction getRefreshAction() {
		return refreshAction;
	}
	public UpdateAction getUpdateAction() {
		return updateAction;
	}
	public SortAction getSortAction() {
		return sortAction;
	}
	public CountAction getCountAction() {
		return countAction;
	}
	public AverageAction getAverageAction() {
		return averageAction;
	}
}
