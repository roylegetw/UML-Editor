package UMLeditor_Mode;

import java.awt.*;

import UMLeditor_Shape.*;

public class ShapeForm {
	
	// 建立物件
	public BasicObject createObj(String objType, Point point) {
		if(objType.equals("class")){
			return new ClassObject(point.x, point.y);
		}
		else if(objType.equals("usecase")){
			return new UsecaseObject(point.x, point.y);
		}
		else return null;
	}

	
	// 建立線段
	public Line createLine(String lineType, Point start, Point end) {
		if(lineType.equals("associate")){
			return new AssociationLine(start.x, start.y, end.x, end.y);
		}
		else if(lineType.equals("general")){
			return new GeneralizationLine(start.x, start.y, end.x, end.y);
		}
		else if(lineType.equals("composite")) {
			return new CompositionLine(start.x, start.y, end.x, end.y);
		}
		return null;
	}
}
