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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.lottery.util.ParamUtil;

public class DisplaySettingFrame extends JFrame {
	
	private static DisplaySettingFrame singleton;
	
	private Color color = this.getColorFromConfig();
	
	private int fontSize = this.getFontSizeFromConfig();
	
	private DisplaySettingFrame(){
		super("显示设置");
		this.setLayout(new FlowLayout());
		
		
		//颜色设置相关组件
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
		
		//放置颜色设置相关组件的面板
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new GridLayout(1,3));
		colorPanel.add(text1);
		colorPanel.add(colorLabel);
		colorPanel.add(selectColorBtn);
		
		//字体大小设置
		JPanel sizePanel = new JPanel();
		sizePanel.setLayout(new GridLayout(1, 2));
		JLabel text2 = new JLabel("字体大小             ");
		final JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(576, 10, 1000, 1));
		sizeSpinner.setValue(fontSize);
		sizePanel.add(text2);
		sizePanel.add(sizeSpinner);
		
		
		//按钮面板
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,2));
		JButton saveBtn = new JButton("确认");
		JButton cancelBtn = new JButton("取消");
		saveBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.setLabelColor(color);
				fontSize = (Integer) sizeSpinner.getValue();
				MainFrame.setLabelSize(fontSize);
				setColorToConfig(color);
				setFontSizeToConfig(fontSize);
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
		c.add(colorPanel);
		c.add(sizePanel);
		c.add(btnPanel);
		
		this.setVisible(true);
		this.setSize(200, 140);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
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
	
	private int getFontSizeFromConfig(){
		return ParamUtil.getFontSizeFromConfig();
	}
	
	private void setFontSizeToConfig(int size){
		ParamUtil.setFontSizeToConfig(size);
	}
	
	public static void main(String a[]){
		new DisplaySettingFrame();
	}
}
