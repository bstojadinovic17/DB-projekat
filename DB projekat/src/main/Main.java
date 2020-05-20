package main;

import db.SQLrepositoryImpl;
import gui.MainView;

public class Main {

	public static void main(String[] args) {
		AppCore appCore = new AppCore();
		MainView frame = MainView.getinstance();
		frame.setAppCore(appCore);
		frame.getAppCore().loadResource();
	}

}
