package UMLeditor_Shape;

import java.awt.*;

public class AssociationLine extends Line {
	
	
	// 設定線段
	public AssociationLine(int x1, int y1, int x2, int y2) {		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	// 畫線
	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}

	
}
