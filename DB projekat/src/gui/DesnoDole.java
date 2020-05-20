package gui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class DesnoDole extends JPanel implements Observer{
	
	private static DesnoDole instance=null;
	
	private DesnoDole() {
		setSize(new Dimension(700, 350));
		setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
	public static DesnoDole getInstance() {
		if(instance==null) {
			instance=new DesnoDole();
		}
		return instance;
	}
}
