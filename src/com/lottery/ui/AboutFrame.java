package com.lottery.ui;


import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AboutFrame extends JFrame {
	
	private static AboutFrame singleton;
	
	private AboutFrame(){
		super("关于");
		this.setLayout(new GridLayout(4,2));
		JLabel label1 = new JLabel("名 称：");
		JLabel name = new JLabel("抽奖机");
		JLabel label2 = new JLabel("版 本：");
		JLabel version = new JLabel("v1.0");
		JLabel label3 = new JLabel("作 者：");
		JLabel author = new JLabel("陈祖煌");
		JLabel label4 = new JLabel("Q Q：");
		JTextField qq = new JTextField("564923716");
		qq.setEditable(false);
		
		Font font = new Font("Times New Roma", Font.PLAIN, 32);
		label1.setFont(font);
		label2.setFont(font);
		label3.setFont(font);
		label4.setFont(font);
		name.setFont(font);
		version.setFont(font);
		author.setFont(font);
		qq.setFont(font);
		
		Container c= this.getContentPane();
		c.add(label1);
		c.add(name);
		c.add(label2);
		c.add(version);
		c.add(label3);
		c.add(author);
		c.add(label4);
		c.add(qq);
		
		this.setResizable(false);
		this.setSize(380, 360);
		this.setLocationRelativeTo(null);
		
	}
	
	public static AboutFrame getInstance(){
		if(singleton == null){
			singleton = new AboutFrame();
		}
		 return singleton;
	}
}
