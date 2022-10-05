package UMLeditor;

import javax.swing.*;

import Listener.CNListener;
import Listener.GOListener;
import Listener.UOListener;

public class MenuBar extends JMenuBar {
	public MenuBar() {
		
		JMenu menu;
		JMenuItem item;

		menu = new JMenu("File");
		add(menu);
		menu = new JMenu("Edit");
		add(menu);
		
		item = new JMenuItem("Change Object Name");
		menu.add(item);
		item.addActionListener(new CNListener());
		
		item = new JMenuItem("Group");
		menu.add(item);
		item.addActionListener(new GOListener());
		
		item = new JMenuItem("Ungroup");
		menu.add(item);
		item.addActionListener(new UOListener());
	}
	
	
}
