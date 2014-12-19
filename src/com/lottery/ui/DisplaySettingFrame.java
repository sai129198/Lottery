package com.lottery.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lottery.util.ParamUtil;

public class DisplaySettingFrame extends JFrame {
	
	private static DisplaySettingFrame singleton;
	
	private Color color;
	
	private DisplaySettingFrame(){
		super("显示设置");
		this.setLayout(new FlowLayout());
		
		
		//颜色设置相关组件
		this.color = this.getColorFromConfig();
		JLabel text1 = new JLabel("颜色");
		final JLabel colorLabel = new JLabel("8888");
		colorLabel.setForeground(color);
		JButton selectColorBtn = new JButton("更改");
		selectColorBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(DisplaySettingFrame.this, "选择颜色", color);
				if(color != null) {
					colorLabel.setForeground(color);
				}
			}	
		});
		
		//放置所有设置相关组件的面板
		JPanel settingPanel = new JPanel();
		settingPanel.setLayout(new GridLayout(1,3));
		settingPanel.add(text1);
		settingPanel.add(colorLabel);
		settingPanel.add(selectColorBtn);
		
		//按钮面板
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,2));
		JButton saveBtn = new JButton("确认");
		JButton cancelBtn = new JButton("取消");
		saveBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setColorToConfig(color);
				DisplaySettingFrame.this.setVisible(false);
				try {
					DisplaySettingFrame.this.finalize();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		cancelBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DisplaySettingFrame.this.setVisible(false);
				try {
					DisplaySettingFrame.this.finalize();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPanel.add(saveBtn);
		btnPanel.add(cancelBtn);
		
		Container c = this.getContentPane();
		c.add(settingPanel);
		c.add(btnPanel);
		
		this.setVisible(true);
		this.setBounds(480, 320, 200, 140);
		this.setResizable(false);
		
	}
	
	public static DisplaySettingFrame getInstance(){
		if(singleton == null){
			singleton = new DisplaySettingFrame();
		}
		return singleton;
	}
	
	private void setColorToConfig(Color color){
		ParamUtil.writeColorSetting(color);
	}
	
	private Color getColorFromConfig(){
		return ParamUtil.getColorFromConfig();
	}
	
	public static void main(String a[]){
		new DisplaySettingFrame();
	}
}
