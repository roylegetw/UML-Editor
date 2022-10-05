package UMLeditor_Shape;

import java.awt.*;

public class ClassObject extends BasicObject {
	
	
	// class����
	public ClassObject(int x1, int y1) {
		this.width = 120;
		this.height = 140;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		createPorts();
	}
	
	// �eclass����
	public void draw(Graphics g) {
		
		// �e�d��
		g.drawRect(x1, y1, width, height);
		
		// ��graphic�e
		int temp = height / 3;
		g.drawLine(x1, y1 + temp, x2, y1 + temp);
		g.drawLine(x1, y1 + temp * 2, x2, y1 + temp * 2);
		
		// find the width for the specified string.
		int stringW = g.getFontMetrics(font).stringWidth(objectName);
		double empty = (Math.abs(x1-x2) - stringW)/2;
		g.setFont(font);	
		g.drawString(objectName, x1 + (int)empty, y1 + 25);
	}
}
