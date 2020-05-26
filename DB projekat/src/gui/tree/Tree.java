package gui.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import actions.TreeSelection;
import gui.MainView;
import gui.Tab;
import gui.table.TableModel;
import model.DBNode;
import model.DBNodeComposite;
import model.categories.Table;
import model.tree.TreeModel;

public class Tree extends JTree{
	
	public Tree() {
		setCellRenderer(new TreeCellRenderer());
		addTreeSelectionListener(new TreeSelection());
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				DBNode selektovan = (DBNode) MainView.getinstance().getTree().getLastSelectedPathComponent();
				if(e.getClickCount() == 2) {
					if(selektovan instanceof Table) {
						Tab.getInstance().dodajTab(selektovan);
						
					}
				}
				
			}
		});
	}
	
	
	public void addNode(Table t) {
		((TreeModel)getModel()).addNode(t);
		SwingUtilities.updateComponentTreeUI(this);
	}
}
