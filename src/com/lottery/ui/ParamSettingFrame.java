package com.lottery.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.lottery.common.StringRsc;
import com.lottery.util.ParamUtil;
import com.lottery.util.XMLUtil;

public class ParamSettingFrame extends JFrame {

	private static ParamSettingFrame singleton;
	
	private ParamSettingFrame(){
		super("参数设置");
		//this.setLayout(new GridLayout(2,1));
		this.setLayout(new FlowLayout());
		final JSpinner minSpinner = new JSpinner(new SpinnerNumberModel(ParamUtil.getMinFromConfig(), 0, 100000, 1));
		final JSpinner maxSpinner = new JSpinner(new SpinnerNumberModel(ParamUtil.getMaxFromConfig(), 1, 100000, 1));
		JLabel minText = new JLabel("最小值");
		JLabel maxText = new JLabel("最大值");
		JButton saveBtn = new JButton("确认");
		JButton cancelBtn = new JButton("取消");
		saveBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int min = (Integer) minSpinner.getValue();
				int max = (Integer) maxSpinner.getValue();
				
				int result = JOptionPane.showConfirmDialog(ParamSettingFrame.this, 
						"抽奖数字将被设置为"+min+"到"+max, 
						"是否确认", 
						JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){
					ParamUtil.writeParamSetting(min, max);	//写参数到配置文件中
					ParamSettingFrame.this.setVisible(false);
					try {
						ParamSettingFrame.this.finalize();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						ParamSettingFrame.this.finalize();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
					return;
				}
			}
		});
		cancelBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ParamSettingFrame.this.setVisible(false);
			}
		});
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2,2));
		inputPanel.add(minText);
		inputPanel.add(minSpinner);
		inputPanel.add(maxText);
		inputPanel.add(maxSpinner);
		
		JPanel btnJPanel = new JPanel();
		btnJPanel.setLayout(new GridLayout(1,2));
		btnJPanel.add(saveBtn);
		btnJPanel.add(cancelBtn);
		
		Container c = this.getContentPane();
		c.add(inputPanel);
		c.add(btnJPanel);
		
		
		this.setResizable(false);
		this.setSize(250, 130);
		this.setLocationRelativeTo(null);
	}
	
	public static ParamSettingFrame getInstance(){
		if(singleton == null){
			singleton = new ParamSettingFrame();
		}
		return singleton;
	}
	
	
}
