package main;

import db.SQLrepositoryImpl;
import gui.MainView;
import model.categories.Resourse;

public class Main {

	public static void main(String[] args) {
		AppCore appCore = new AppCore();
		MainView frame = MainView.getinstance();
		frame.setAppCore(appCore);
		frame.setVisible(true);
		frame.getAppCore().ReadDataFromTable("DEPARTMENTS");
	}

}
