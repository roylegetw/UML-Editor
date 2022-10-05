package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UMLeditor.Painty;

public class GOListener implements ActionListener {
	private Painty painty ;
	public void actionPerformed(ActionEvent e) {
		painty = Painty.getP();
		painty.GroupShape();
	}
}
