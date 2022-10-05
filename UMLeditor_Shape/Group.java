package UMLeditor_Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group extends Shape {
	private Rectangle bounds = new Rectangle(); // 圖形範圍
	private Shape selectedShape = null;	// 目前選取之圖形
	private List<Shape> shapes = new ArrayList<Shape>();


	
	// 做圖
	public void draw(Graphics g) { 
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.draw(g);
		}
	}

	// 加入圖形
	public void addShapes(Shape shape) {
		shapes.add(shape);
	}

		
	// group之圖形list
	public List<Shape> getShapes() {
		return shapes;
	}

	public void resetLocation(int moveX, int moveY) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.resetLocation(moveX, moveY);
		}
		resetBounds(moveX, moveY);
	}

	public String inside(Point p) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			String judgeInside = shape.inside(p);
			if (judgeInside != null) {
				selectedShape = shape;
				return "insideGroup";
			}
		}
		return null;
	}

	// 更改名稱
	public void changeName(String name) {
		selectedShape.changeName(name);
	}

	// 重設選取圖形
	public void resetSelectedShape() {
		selectedShape = null;
	}
	
	// 取得選取之圖形
	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	// 顯示圖形
	public void show(Graphics g) {
		int offset = 8;
		g.setColor(new Color(110, 219, 181, 50));
		g.fillRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
		g.setColor(new Color(110, 219, 181));
		g.drawRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
		g.setColor(Color.white);
		if (selectedShape != null) {
			selectedShape.show(g);
		}
	}	

	// 重設邊界
	protected void resetBounds(int moveX, int moveY) {
		bounds.setLocation(bounds.x + moveX, bounds.y + moveY);
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}
	
	// 設定group邊界
	public void setBounds() {
		Point upLeftP, bottomRightP;
		int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
		int up = Integer.MAX_VALUE, bottom = Integer.MIN_VALUE;

		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			if (shape.getX1() < left) {
				left = shape.getX1();
			}
			if (shape.getX2() > right) {
				right = shape.getX2();
			}
			if (shape.getY1() < up) {
				up = shape.getY1();
			}
			if (shape.getY2() > bottom) {
				bottom = shape.getY2();
			}
		}

		upLeftP = new Point(left, up);
		bottomRightP = new Point(right, bottom);
		bounds.setBounds(upLeftP.x, upLeftP.y, Math.abs(upLeftP.x - bottomRightP.x),
				Math.abs(upLeftP.y - bottomRightP.y));
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}	

}
