package UMLeditor_Shape;

import java.awt.*;
import javax.swing.*;

public abstract class Shape {
	protected int x1, y1, x2, y2;
	public boolean group_selected = false;
		
	// ���]�u�q
	public void resetLocation(){}   // line 
	
	// ���]����B����
	public void resetLocation(int movX, int movY){}  // basicObj and Group
	
	// ��W
	public void changeName(String name){}
	
	// ����
	public void show(Graphics g){}
	
	// �d�ݥثe����O�_�b���Ϊ���
	public String inside(Point p){
		return null;
	}
	
	public abstract void draw(Graphics g);
	
	// �y��
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
	
	// ���o����
	public Port getPort(int index){
		return null;
	}
	
	// ���m�j�p
	public void resetSelectedShape() {}
	
	// �Ϊ��j�p
	public Shape getSelectedShape() {
		return null;
	}
	
	
}
