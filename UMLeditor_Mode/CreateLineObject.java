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
		// 尋找相關聯的物件
		shapes = painty.getShapeList();
		start = findConnectedObj(e.getPoint(), "first");
	}

	public void mouseDragged(MouseEvent e) {
		if (start != null) {
			// 拖拉&創建線段
			Line line = shapeform.createLine(lineType, start, e.getPoint());
			painty.Line = line;
			
			painty.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		Point endP = null;
		if (start != null) {
			
			// 找放開滑鼠所在的位置是否有物件
			endP = findConnectedObj(e.getPoint(), "second");

			// 在物件內
			if (endP != null) {
				Line line = shapeform.createLine(lineType, start, endP);
				painty.addShape(line);

				// 將線端與此物件相連 port的部分
				line.setPorts(shape1.getPort(portIndex1), shape2.getPort(portIndex2));
				// 創造關聯性，port相連
				shape1.getPort(portIndex1).addLine(line);
				shape2.getPort(portIndex2).addLine(line);
			}
			
			// 初始畫參數
			painty.Line = null;
			painty.repaint();
			start = null;
		}
	}

	// 尋找是否在物件內
	private Point findConnectedObj(Point p, String target) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);

			int portIndex;
			String Tinside = shape.inside(p);
			// 在物件內
			if (Tinside != null && Tinside != "insideLine") {
				
				// 在group中
				if(Tinside == "insideGroup"){  
					shape = shape.getSelectedShape();
					portIndex = Integer.parseInt(shape.inside(p));
				}
				else
					portIndex = Integer.parseInt(Tinside);
			
				// 開始物件或結尾物件
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
