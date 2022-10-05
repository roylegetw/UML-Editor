package UMLeditor_Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Port extends Rectangle{
	private List<Line> lines = new ArrayList<Line>(); 

	// 加入線段
	public void addLine(Line line) {
		lines.add(line);
	}
	
	// 刪除端點上線段
	public void removeLine(Line line) {
		lines.remove(line);
	}
	
	// 重設端點上線段
	public void resetLines() {
		for(int j = 0; j < lines.size(); j++){
			Line line = lines.get(j);
			line.resetLocation();
		}
	}
	
	// 設定端點
	public void setPort(int c_x, int c_y, int offset) {
		int x = c_x - offset;
		int y = c_y - offset;
		int w = offset * 2;
		int h = offset * 2;
		setBounds(x, y, w, h);
	}
}
