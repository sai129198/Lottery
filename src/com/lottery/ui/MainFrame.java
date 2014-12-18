package com.lottery.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
	
	public MainFrame(){
		super("Lottery");
		
		this.setJMenuBar(new LotteryMenu());
		
		this.setBounds(320, 180, 400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				int result = JOptionPane.showConfirmDialog(MainFrame.this, "确认退出抽奖程序吗？", "确认", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){
					System.exit(0);
				} else {
					return;
				}
			}
		});
	}
}
