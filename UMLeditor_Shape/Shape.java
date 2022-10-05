package UMLeditor_Shape;

import java.awt.*;
import javax.swing.*;

public abstract class Shape {
	protected int x1, y1, x2, y2;
	public boolean group_selected = false;
		
	// 重設線段
	public void resetLocation(){}   // line 
	
	// 重設物件、團體
	public void resetLocation(int movX, int movY){}  // basicObj and Group
	
	// 改名
	public void changeName(String name){}
	
	// 做圖
	public void show(Graphics g){}
	
	// 查看目前選取是否在此形狀內
	public String inside(Point p){
		return null;
	}
	
	public abstract void draw(Graphics g);
	
	// 座標
	public int getX1(){
		return x1;
	}
	public int getY1(){
		return y1;
	}
	public int getX2(){
		return x2;
	}
	public int getY2(){
		return y2;
	}
	
	// 取得坐標
	public Port getPort(int index){
		return null;
	}
	
	// 重置大小
	public void resetSelectedShape() {}
	
	// 形狀大小
	public Shape getSelectedShape() {
		return null;
	}
	
	
}
