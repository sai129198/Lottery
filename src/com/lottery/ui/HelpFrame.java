package com.lottery.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HelpFrame extends JFrame {
	
	private static HelpFrame singleton;
	
	private HelpFrame(){
		super("帮助");
		
		JTextArea text = new JTextArea(
				  "\n点“下一个数字”，10秒内抽出一个随机数字；每次抽出的数字会"
				+ "被系统记录，之前已出现过的数字将不再出现，若需重新开始抽奖，"
				+ "清除掉已出现过的数字记录，可以点“文件”---“重新开始抽奖”。");
		text.setEditable(false);
		text.setLineWrap(true);
		this.getContentPane().add(text);
		
		this.setSize(400, 120);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static HelpFrame getInstance(){
		if(singleton == null){
			singleton = new HelpFrame();
		}
		return singleton;
	}
}
