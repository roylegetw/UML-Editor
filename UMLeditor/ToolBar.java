package UMLeditor;

import java.awt.*;
import javax.swing.*;

import UMLeditor_Mode.CreateLineObject;
import UMLeditor_Mode.CreateObject;
import UMLeditor_Mode.Mode;
import UMLeditor_Mode.Select;

import java.awt.event.*;


public class ToolBar extends JToolBar {
	private int ToolNum = 6;
	private Color myColor = new Color(25, 100, 50);
	private JButton holdButton = null;
	private Painty painty;

	public ToolBar() {
		painty = Painty.getP();   // Canvas is singleton 
	    // 工具列格式設定
		setLayout(new GridLayout(ToolNum, 1));
		
		// 按鈕、圖案加入面板
		ImageIcon selectIcon = new ImageIcon("img/Select.png");
		ToolBtn selectButton = new ToolBtn("select", selectIcon, new Select());
		add(selectButton);
		
		ImageIcon associateIcon = new ImageIcon("img/AssociationLine.png");
		ToolBtn associateButton = new ToolBtn("associate", associateIcon, new CreateLineObject("associate"));
		add(associateButton);
		
		ImageIcon generalIcon = new ImageIcon("img/GeneralizationLine.png");
		ToolBtn generalButton = new ToolBtn("general", generalIcon, new CreateLineObject("general"));
		add(generalButton);
		
		ImageIcon compositeIcon = new ImageIcon("img/CompositionLine.png");
		ToolBtn compositeButton = new ToolBtn("composite", compositeIcon, new CreateLineObject("composite"));
		add(compositeButton);
		
		ImageIcon classIcon = new ImageIcon("img/ClassObject.png");
		ToolBtn classButton = new ToolBtn("class", classIcon, new CreateObject("class"));
		add(classButton);
		
		ImageIcon usecaseIcon = new ImageIcon("img/UseCaseObject.png");
		ToolBtn usecaseButton = new ToolBtn("usecase", usecaseIcon, new CreateObject("usecase"));
		add(usecaseButton);

	}
	
	// 將按鈕包成一個可用之類別
	private class ToolBtn extends JButton {
		Mode ToolMode;
		
		public ToolBtn(String ToolName, ImageIcon icon, Mode ToolMode) {
			this.ToolMode = ToolMode;
			
			// 工具圖案格式設定
			setToolTipText(ToolName);
			setIcon(icon);
			setFocusable(false);
			setFloatable(false);
			setBackground(new Color(0, 0, 0));
			setBorderPainted(false);
			setRolloverEnabled(true);
			
			// 聆聽按鈕事件
			addActionListener(new toolListener());
		}
		class toolListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				// 按鈕有按下去
				if(holdButton != null)
					holdButton.setBackground(new Color(20, 20, 20));
				holdButton = (JButton) e.getSource();
				holdButton.setBackground(myColor);
				
				// 設定繪圖模式
				painty.currentMode = ToolMode;
				painty.setCurrentMode();
				painty.reset();
				painty.repaint();
			}
		}
	}
}
