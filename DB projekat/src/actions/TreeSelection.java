package actions;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import model.DBNode;
import model.DBNodeComposite;

public class TreeSelection implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		for(int i=0;i<path.getPathCount(); i++) {
			DBNodeComposite node = (DBNodeComposite) path.getLastPathComponent();
			break;
		}
	}

}
