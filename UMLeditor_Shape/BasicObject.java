package UMLeditor_Shape;

import java.awt.*;

public abstract class BasicObject extends Shape{

	protected int width, height; 
	
	// 基本物件名稱
	protected String objectName = "Object Name";
	
	// 字型
	protected Font font = new Font(Font.DIALOG, Font.BOLD, 15);
	
	// 基本物件有4個Port
	protected Port[] ports = new Port[4];

	// 畫
	public abstract void draw(Graphics g);
	
	// 取得Port
	public Port getPort(int portIndex) {
		return ports[portIndex];
	}
	
	// 圖形建構
	public void show(Graphics g) {
		for(int i = 0; i < ports.length; i++) {
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
	}
	
	
	// 設定圖形範圍 
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
	
	// 重設位置
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
	
	// 更改名稱
	public void changeName(String name){
		this.objectName = name;
	}
	
	// 為物件創造Port
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
