package UMLeditor_Mode;

import java.awt.* ;
import java.awt.event.MouseEvent;
import java.util.List;

import UMLeditor_Mode.ShapeForm;
import UMLeditor_Shape.*;
import UMLeditor_Shape.Shape;

public class CreateLineObject extends Mode {
	private String lineType = null;
	private ShapeForm shapeform = new ShapeForm();
	private Point start = null;
	private List<Shape> shapes;
	private int portIndex1 = -1, portIndex2 = -1;
	private Shape shape1 = null, shape2 = null;

	public CreateLineObject(String lineType) {
		this.lineType = lineType;
	}

	public void mousePressed(MouseEvent e) {
		// �M������p������
		shapes = painty.getShapeList();
		start = findConnectedObj(e.getPoint(), "first");
	}

	public void mouseDragged(MouseEvent e) {
		if (start != null) {
			// ���&�Ыؽu�q
			Line line = shapeform.createLine(lineType, start, e.getPoint());
			painty.Line = line;
			
			painty.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		Point endP = null;
		if (start != null) {
			
			// ���}�ƹ��Ҧb����m�O�_������
			endP = findConnectedObj(e.getPoint(), "second");

			// �b����
			if (endP != null) {
				Line line = shapeform.createLine(lineType, start, endP);
				painty.addShape(line);

				// �N�u�ݻP������۳s port������
				line.setPorts(shape1.getPort(portIndex1), shape2.getPort(portIndex2));
				// �гy���p�ʡAport�۳s
				shape1.getPort(portIndex1).addLine(line);
				shape2.getPort(portIndex2).addLine(line);
			}
			
			// ��l�e�Ѽ�
			painty.Line = null;
			painty.repaint();
			start = null;
		}
	}

	// �M��O�_�b����
	private Point findConnectedObj(Point p, String target) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);

			int portIndex;
			String Tinside = shape.inside(p);
			// �b����
			if (Tinside != null && Tinside != "insideLine") {
				
				// �bgroup��
				if(Tinside == "insideGroup"){  
					shape = shape.getSelectedShape();
					portIndex = Integer.parseInt(shape.inside(p));
				}
				else
					portIndex = Integer.parseInt(Tinside);
			
				// �}�l����ε�������
				switch (target) {
				case "first":
					shape1 = shape;
					portIndex1 = portIndex;
					break;
				case "second":
					shape2 = shape;
					portIndex2 = portIndex;
					break;
				}
				
				
				Point portLocation = new Point();
				portLocation.setLocation(shape.getPort(portIndex).getCenterX(), shape.getPort(portIndex).getCenterY());
				return portLocation;
			}

		}
		return null;
	}
}
