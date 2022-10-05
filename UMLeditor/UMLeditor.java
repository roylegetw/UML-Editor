package UMLeditor;

import java.awt.*;
import javax.swing.*;



public class UMLeditor extends JFrame {
	private ToolBar toolbar ;
	private MenuBar menubar ;
	private Painty painty ;
	
	public UMLeditor() {
		toolbar = new ToolBar();
		menubar = new MenuBar();
		painty = Painty.getP() ;
		getContentPane().add(menubar, BorderLayout.NORTH);
		getContentPane().add(toolbar, BorderLayout.WEST);
		getContentPane().add(painty, BorderLayout.CENTER);
	}
	
	
	
	public static void main(String[] args) {
		UMLeditor mainWindow = new UMLeditor();
		mainWindow.setTitle("UML editor");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(1200, 720);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);

	}

}
