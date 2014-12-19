package com.lottery.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.lottery.model.LotteryThread;
import com.lottery.util.ParamUtil;
import com.lottery.util.ResourceUtil;

public class MainFrame extends JFrame {
	
	private static List<Integer> numberList;
	private Color color = ParamUtil.getColorFromConfig();
	
	private static int fontSize = ParamUtil.getFontSizeFromConfig();
	private static JLabel numLabel;
	
	public MainFrame(){
		super("抽奖机");
		this.numberList = new ArrayList<Integer>();
		
		this.setJMenuBar(new LotteryMenu());
		
		//this.setBounds(320, 180, 400, 400);
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
		
		//抽奖相关
		
		numLabel = new JLabel("0", JLabel.CENTER);
		numLabel.setForeground(color);
		numLabel.setFont(new Font("New Romas Times", Font.PLAIN, fontSize));
		
		final JButton nextNumberBtn = new JButton("下一个");
		final boolean btnFlag = true;
		nextNumberBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				nextNumberBtn.setEnabled(!nextNumberBtn.isEnabled());
				
				int min = ParamUtil.getMinFromConfig();
				int max = ParamUtil.getMaxFromConfig();
				LotteryThread thread = LotteryThread.getInstance(min, max, numLabel, nextNumberBtn, numberList);
				thread.start();
			}
		});
		
		Container c = this.getContentPane();
		c.add(numLabel);
		c.add(nextNumberBtn, "South");
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(true);
		
		ResourceUtil.setIcon(this);
		//this.setMinimumSize(this.getMinimumSize());
	}
	
	public static void setLabelSize(int size){
		if(size < 10 || size > 1000) {
			size = 576;
		}
		numLabel.setFont(new Font("Times New Roma", Font.PLAIN, size));
	}
	
	public static void setLabelColor(Color color){
		numLabel.setForeground(color);
	}
	
	/**
	 * 清空numList
	 */
	public static void replay(){
		numberList.clear();
	}
}
