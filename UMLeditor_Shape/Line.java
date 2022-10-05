package UMLeditor_Shape;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class Line extends Shape{
	protected Port[] ports = new Port[2];	// 線的兩端
	
	private String selectedFlag = null;
	public abstract void draw(Graphics g);
	
	// 設定端點
	public void setPorts(Port port1, Port port2) {
		this.ports[0] = port1;
		this.ports[1] = port2;
	}
	
	// 做圖
	public void show(Graphics g) {
		g.setColor(new Color(50, 171, 175));
		this.draw(g);
		g.setColor(Color.white);
	}
	
	// 重設線的位置
	public void resetLocation(){
		this.x1 = (int) ports[0].getCenterX();
		this.y1 = (int) ports[0].getCenterY();
		this.x2 = (int) ports[1].getCenterX();
		this.y2 = (int) ports[1].getCenterY();
	}
	
	// 重設端點
	public void resetPort(Port port, Line line) {
		port.addLine(line);
		if(selectedFlag == "start"){
			this.ports[0].removeLine(line);
			this.ports[0] = port;
		}
		else if(selectedFlag == "end"){
			this.ports[1].removeLine(line);
			this.ports[1] = port;
		}		
	}
	
	
	// 選取到線
	public String inside(Point p) {
		int no = 5;
		if(distance(p) < no) {
			
			// 計算距離
			double distToStart = Math.sqrt(Math.pow((p.x - x1),2) + Math.pow((p.y - y1), 2));
			double distToEnd = Math.sqrt(Math.pow((p.x - x2),2) + Math.pow((p.y - y2), 2));
			if(distToStart < distToEnd) {
				selectedFlag = "start";
			}
			else{
				selectedFlag = "end";
			}
			return "insideLine";
		}
		else
			return null;
	}
	
	// 重設線的起始終點
	public void resetStartEnd(Point point) {
		if(selectedFlag == "start"){
			this.x1 = point.x;
			this.y1 = point.y;
		}
		else if(selectedFlag == "end") {
			this.x2 = point.x;
			this.y2 = point.y;
		}
	}
	
	// 計算線長距離
	private double distance(Point p) {
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		return line.ptLineDist(p.getX(), p.getY());
	}
}
 