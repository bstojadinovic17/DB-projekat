package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DesnoGore extends JPanel{
	
	private JFrame frame = MainView.getinstance();
	private static DesnoGore instance = null;
	private DesnoGore() {
		// TODO Auto-generated constructor stub
		setSize(new Dimension(700, 350));
		setVisible(true);
		
		
		}
	
		
		
		
		public static DesnoGore getInstance() {
			if(instance == null) {
				instance = new DesnoGore();
			}
			return instance;
		}
	}

