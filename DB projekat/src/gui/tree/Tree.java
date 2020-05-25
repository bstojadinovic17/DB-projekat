package gui.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import actions.TreeSelection;
import gui.MainView;
import gui.table.TableModel;
import model.DBNode;
import model.DBNodeComposite;
import model.categories.Table;

public class Tree extends JTree{
	
	public Tree() {
		addTreeSelectionListener(new TreeSelection());
		setCellRenderer(new TreeCellRenderer());
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
				if(e.getClickCount() == 2) {
					
				}
				
			}
		});
	}
	
	
	
}
