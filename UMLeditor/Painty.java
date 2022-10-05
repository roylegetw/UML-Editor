package UMLeditor;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.*;

import UMLeditor_Mode.Mode;
import UMLeditor_Shape.*;
import UMLeditor_Shape.Shape;

public class Painty extends JPanel {
	private EventListener listener = null;
	protected Mode currentMode = null;	// �ثe���Ҧ�

	private List<Shape> shapes = new ArrayList<Shape>();	// �Ϊ���list

	public Shape Line = null;
	public Rectangle SelectedArea = new Rectangle();	// �إ߿���Ŷ�
	public Shape selectedObject = null;
		
	private static Painty paint = null;
	
	// �s�W�ߤ@�@�ӧ@�e���O
	public static Painty getP() {
		if (paint == null) {
			paint = new Painty();
		}
		
		return paint;
	}
	
	public void setCurrentMode() {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = currentMode;
		// �s�W�ƹ��ƥ�
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	// ���m��ӿ������
	public void reset() {
		if(selectedObject != null){
			selectedObject.resetSelectedShape();   
			selectedObject = null;
		}
		SelectedArea.setBounds(0, 0, 0, 0);
	}
	
	// ���o���Ϊ�
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	// ���o�Ϊ���list		 
	public List<Shape> getShapeList() {
		return this.shapes;
	}

	// ���Group�d��
	public void GroupShape() {
		Group group = new Group();
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			if (shape.group_selected) {
				group.addShapes(shape);
				shapes.remove(i);
				i--;
			}
		}
		group.setBounds();
		shapes.add(group);
	}
	
	// �R��Group
	public void removeGroup() {
		Group group = (Group) selectedObject;
		List<Shape> groupShapes = group.getShapes();
		for(int i = 0; i < groupShapes.size(); i++){
			Shape shape = groupShapes.get(i);
			shapes.add(shape);
		}
		shapes.remove(selectedObject);
	}

	// �磌�󪺦W��
	public void changeObjName(String name) {
		if(selectedObject != null){
			selectedObject.changeName(name);
			repaint();
		}
	}

	// �T�{�ثe������d��
	private boolean checkSelectedArea(Shape shape) {
		Point upperleft = new Point(shape.getX1(), shape.getY1());
		Point lowerright = new Point(shape.getX2(), shape.getY2());
		if (SelectedArea.contains(upperleft) && SelectedArea.contains(lowerright)) {
			return true;
		}
		return false;
	}

	// �e��
	public void paint(Graphics g) {
		Dimension dim = getSize();
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			shape.draw(g);
			shape.group_selected = false;
			
			// �ݥثe����쪺�d�򦳨S���ϧ�(Group)
			if (!SelectedArea.isEmpty() && checkSelectedArea(shape)) {
				shape.show(g);
				shape.group_selected = true;
			}
			
		}

		// ��������S���u
		if (Line != null) {
			Line.draw(g);
		}
		
		// �p�G������쪫��
		if (this.selectedObject != null) {
			selectedObject.show(g);	
		}

		// �ثe������ϰ줣�O�Ū�
		if (!SelectedArea.isEmpty()) {
			g.setColor(new Color(40, 140, 200, 35));
			g.fillRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
			g.setColor(new Color(20, 150, 216));
			g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);

		}
	}
}
