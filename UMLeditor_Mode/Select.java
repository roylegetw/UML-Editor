package UMLeditor_Mode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import UMLeditor_Shape.*;
import UMLeditor_Shape.Shape;

public class Select extends Mode {
	private List<Shape> shapes;
	private Point start = null;
	private String Tinside = null;
	private Line selectedLine = null;
	
	public void mousePressed(MouseEvent e) {
		start = e.getPoint(); // ���o�ƹ��ƥ�_�l
		shapes = painty.getShapeList();

		// ���m���O(�N����)
		painty.reset();

		
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			
			Tinside = shape.inside(e.getPoint());
			// ��ثe���������
			if (Tinside != null) {
				painty.selectedObject = shape;
				break;
			}
		}
		painty.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		int moveX = e.getX() - start.x;
		int moveY = e.getY() - start.y;
		if (painty.selectedObject != null) {
			
			if (Tinside == "insideLine") {
				selectedLine = (Line) painty.selectedObject;	// �૬
				selectedLine.resetStartEnd(e.getPoint());
			}
			else {
				painty.selectedObject.resetLocation(moveX, moveY);
			}
			start.x = e.getX();
			start.y = e.getY();
		}
		else {
			// �쥪�W��k�U����m
			if (e.getX() > start.x)
				painty.SelectedArea.setBounds(start.x, start.y, Math.abs(moveX), Math.abs(moveY));
			else
				painty.SelectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));

		}
		painty.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		
		if (painty.selectedObject != null) {
			// �ݦ�����O�_���u�q
			if (Tinside == "insideLine") {
				selectedLine = (Line) painty.selectedObject;
				reconnectLine(e.getPoint()); // ���s�Խu
				
			}
		}
		else { // �Ogroup������
			painty.SelectedArea.setSize(Math.abs(e.getX() - start.x), Math.abs(e.getY() - start.y));
		}
		
		painty.repaint();
	}

	// ���s�e�u
	private void reconnectLine(Point p) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			int portIndex;
			String Tinside = shape.inside(p);
			// �D�u
			if (Tinside != null && Tinside != "insideLine") {
				// ��group��
				if (Tinside == "insideGroup") {
					shape = shape.getSelectedShape();
					portIndex = Integer.parseInt(shape.inside(p));
				}
				else portIndex = Integer.parseInt(Tinside);
				
				// ���mPort&���m��m
				selectedLine.resetPort(shape.getPort(portIndex), selectedLine);
				selectedLine.resetLocation();
			}
		}

	}
}
