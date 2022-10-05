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
	protected Mode currentMode = null;	// 目前的模式

	private List<Shape> shapes = new ArrayList<Shape>();	// 形狀的list

	public Shape Line = null;
	public Rectangle SelectedArea = new Rectangle();	// 建立選取空間
	public Shape selectedObject = null;
		
	private static Painty paint = null;
	
	// 新增唯一一個作畫面板
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
		// 新增滑鼠事件
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	// 重置整個選取物件
	public void reset() {
		if(selectedObject != null){
			selectedObject.resetSelectedShape();   
			selectedObject = null;
		}
		SelectedArea.setBounds(0, 0, 0, 0);
	}
	
	// 取得此形狀
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	// 取得形狀的list		 
	public List<Shape> getShapeList() {
		return this.shapes;
	}

	// 選取Group範圍
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
	
	// 刪除Group
	public void removeGroup() {
		Group group = (Group) selectedObject;
		List<Shape> groupShapes = group.getShapes();
		for(int i = 0; i < groupShapes.size(); i++){
			Shape shape = groupShapes.get(i);
			shapes.add(shape);
		}
		shapes.remove(selectedObject);
	}

	// 改物件的名稱
	public void changeObjName(String name) {
		if(selectedObject != null){
			selectedObject.changeName(name);
			repaint();
		}
	}

	// 確認目前的選取範圍
	private boolean checkSelectedArea(Shape shape) {
		Point upperleft = new Point(shape.getX1(), shape.getY1());
		Point lowerright = new Point(shape.getX2(), shape.getY2());
		if (SelectedArea.contains(upperleft) && SelectedArea.contains(lowerright)) {
			return true;
		}
		return false;
	}

	// 畫圖
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
			
			// 看目前選取到的範圍有沒有圖形(Group)
			if (!SelectedArea.isEmpty() && checkSelectedArea(shape)) {
				shape.show(g);
				shape.group_selected = true;
			}
			
		}

		// 選取的有沒有線
		if (Line != null) {
			Line.draw(g);
		}
		
		// 如果有選取到物件
		if (this.selectedObject != null) {
			selectedObject.show(g);	
		}

		// 目前的選取區域不是空的
		if (!SelectedArea.isEmpty()) {
			g.setColor(new Color(40, 140, 200, 35));
			g.fillRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
			g.setColor(new Color(20, 150, 216));
			g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);

		}
	}
}
