package UMLeditor_Shape;

import java.awt.*;

public abstract class BasicObject extends Shape{

	protected int width, height; 
	
	// �򥻪���W��
	protected String objectName = "Object Name";
	
	// �r��
	protected Font font = new Font(Font.DIALOG, Font.BOLD, 15);
	
	// �򥻪���4��Port
	protected Port[] ports = new Port[4];

	// �e
	public abstract void draw(Graphics g);
	
	// ���oPort
	public Port getPort(int portIndex) {
		return ports[portIndex];
	}
	
	// �ϧΫغc
	public void show(Graphics g) {
		for(int i = 0; i < ports.length; i++) {
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
	}
	
	
	// �]�w�ϧνd�� 
		public String inside(Point p) {
			Point center = new Point();
			center.x = (x1 + x2) / 2;
			center.y = (y1 + y2) / 2;
			Point[] points = { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) };
			
			
			
			for (int i = 0; i < points.length; i++) {
				Polygon t = new Polygon();
				int secondIndex = ((i + 1) % 4);
				t.addPoint(points[i].x, points[i].y);
				t.addPoint(points[secondIndex].x, points[secondIndex].y);
				t.addPoint(center.x, center.y);

				if (t.contains(p)) {
					return Integer.toString(i);
				}
			}
			return null;
		}	
	
	
	private int offset = 3;
	
	// ���]��m
	public void resetLocation(int moveX, int moveY) {
		int x1 = this.x1 + moveX;
		int y1 = this.y1 + moveY;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		int[] xpoint = {(x1+x2)/2, x2 + offset, (x1+x2)/2, x1 - offset};
		int[] ypoint = {y1 - offset, (y1+y2)/2, y2+offset, (y1+y2)/2};
		
		for(int i = 0; i < ports.length; i++) {
			ports[i].setPort(xpoint[i], ypoint[i], offset);
			ports[i].resetLines();
		}
	}
	
	// ���W��
	public void changeName(String name){
		this.objectName = name;
	}
	
	// ������гyPort
	protected void createPorts() {
		int[] x = {(x1+x2)/2, x2 + offset, (x1+x2)/2, x1 - offset};
		int[] y = {y1 - offset, (y1+y2)/2, y2+offset, (y1+y2)/2};

		for(int i = 0; i < ports.length; i++) {
			Port port = new Port();
			port.setPort(x[i], y[i], offset);
			ports[i] = port;
		}
	}
}
