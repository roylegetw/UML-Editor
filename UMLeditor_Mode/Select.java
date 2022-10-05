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
		start = e.getPoint(); // 取得滑鼠事件起始
		shapes = painty.getShapeList();

		// 重置面板(代表不動)
		painty.reset();

		
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			
			Tinside = shape.inside(e.getPoint());
			// 找目前選取的物件
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
				selectedLine = (Line) painty.selectedObject;	// 轉型
				selectedLine.resetStartEnd(e.getPoint());
			}
			else {
				painty.selectedObject.resetLocation(moveX, moveY);
			}
			start.x = e.getX();
			start.y = e.getY();
		}
		else {
			// 抓左上到右下的位置
			if (e.getX() > start.x)
				painty.SelectedArea.setBounds(start.x, start.y, Math.abs(moveX), Math.abs(moveY));
			else
				painty.SelectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));

		}
		painty.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		
		if (painty.selectedObject != null) {
			// 看此物件是否為線段
			if (Tinside == "insideLine") {
				selectedLine = (Line) painty.selectedObject;
				reconnectLine(e.getPoint()); // 重新拉線
				
			}
		}
		else { // 是group的部分
			painty.SelectedArea.setSize(Math.abs(e.getX() - start.x), Math.abs(e.getY() - start.y));
		}
		
		painty.repaint();
	}

	// 重新畫線
	private void reconnectLine(Point p) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			int portIndex;
			String Tinside = shape.inside(p);
			// 非線
			if (Tinside != null && Tinside != "insideLine") {
				// 為group內
				if (Tinside == "insideGroup") {
					shape = shape.getSelectedShape();
					portIndex = Integer.parseInt(shape.inside(p));
				}
				else portIndex = Integer.parseInt(Tinside);
				
				// 重置Port&重置位置
				selectedLine.resetPort(shape.getPort(portIndex), selectedLine);
				selectedLine.resetLocation();
			}
		}

	}
}
