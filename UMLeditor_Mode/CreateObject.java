package UMLeditor_Mode;

import java.awt.event.* ;

import UMLeditor_Mode.ShapeForm;
import UMLeditor_Shape.*;

public class CreateObject extends Mode {
	private String objectType = null;
	private ShapeForm factory = new ShapeForm();
	
	public void mousePressed(MouseEvent e) {
		BasicObject basicObj = factory.createObj(objectType, e.getPoint());
		painty.addShape(basicObj);
		painty.repaint();
	}
	
	public CreateObject(String objectType) {
		this.objectType = objectType;
	}
	
	
}
