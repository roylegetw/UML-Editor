package UMLeditor_Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group extends Shape {
	private Rectangle bounds = new Rectangle(); // �ϧνd��
	private Shape selectedShape = null;	// �ثe������ϧ�
	private List<Shape> shapes = new ArrayList<Shape>();


	
	// ����
	public void draw(Graphics g) { 
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.draw(g);
		}
	}

	// �[�J�ϧ�
	public void addShapes(Shape shape) {
		shapes.add(shape);
	}

		
	// group���ϧ�list
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

	// ���W��
	public void changeName(String name) {
		selectedShape.changeName(name);
	}

	// ���]����ϧ�
	public void resetSelectedShape() {
		selectedShape = null;
	}
	
	// ���o������ϧ�
	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	// ��ܹϧ�
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

	// ���]���
	protected void resetBounds(int moveX, int moveY) {
		bounds.setLocation(bounds.x + moveX, bounds.y + moveY);
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}
	
	// �]�wgroup���
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
