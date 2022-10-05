package UMLeditor_Shape;

import java.awt.* ;

public class UsecaseObject extends BasicObject {
	
	public UsecaseObject(int x, int y) {
		
		// 固定圖形大小
		this.width = 120;
		this.height = 90;
		
		this.x1 = x;
		this.y1 = y;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		
		// 建立此圖形之端點
		createPorts();
	}
	public void draw(Graphics g) {
		
		// 做圖
		g.drawOval(x1, y1, width, height);
		
		// 為圖形設名字
		int stringWidth = g.getFontMetrics(font).stringWidth(objectName);
		double empty = (Math.abs(x1-x2) - stringWidth)/2;
		g.setFont(font);	
		g.drawString(objectName, x1 + (int)empty, y1 + 25);
	}
}
