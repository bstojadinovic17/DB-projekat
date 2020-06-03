package actions;

public class ActionManager {
	
	private AddAction addAction;
	private DeleteAction deleteAction;
	private UpdateAction updateAction;
	private FilterAction filterAction;
	private SortAction sortAction;
	private RefreshAction refreshAction;
	public ActionManager() {
		initialization();
	}

	public void initialization() {
		// TODO Auto-generated method stub
		addAction = new AddAction();
		deleteAction = new DeleteAction();
		refreshAction = new RefreshAction();
		updateAction = new UpdateAction();
		filterAction = new FilterAction();
		sortAction = new SortAction();
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
	public FilterAction getFilterAction() {
		return filterAction;
	}
	public SortAction getSortAction() {
		return sortAction;
	}
}
