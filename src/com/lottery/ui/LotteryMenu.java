package com.lottery.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class LotteryMenu extends JMenuBar {
	private final String[] fileItems = new String[] {"开始抽奖", "结束抽奖", "开始下一次抽奖", "退出"};
	private final String[] settingItems = new String[] {"参数","显示"};
	private final String[] helpItems = new String[] {"使用方法" ,"关于"};
	
	private final char[] fileShortcuts = new char[] {'B','O','N','E'};
	private final char[] settingShortcuts = new char[] {'P', 'D'};
	private final char[] helpShortcuts = new char[] {'U', 'A'};
	
	private JMenu fileMenu;
	private JMenu settingMenu;
	private JMenu helpMenu;
	
	public LotteryMenu(){
		init();
		this.add(fileMenu);
		this.add(settingMenu);
		this.add(helpMenu);
	}
	
	public void init(){
		this.fileMenu = new JMenu("文件");
		this.settingMenu = new JMenu("设置");
		this.helpMenu = new JMenu("帮助");
		MenuListener menuListener = new MenuListener();
		
		for(int i = 0; i < fileItems.length; i++){
			JMenuItem fileItem = new JMenuItem(fileItems[i]);
			fileItem.setAccelerator(KeyStroke.getKeyStroke(fileShortcuts[i], InputEvent.CTRL_MASK));
			fileItem.addActionListener(menuListener);
			fileMenu.add(fileItem);
		}
		
		for(int i = 0; i < settingItems.length; i++){
			JMenuItem settingItem = new JMenuItem(settingItems[i]);
			settingItem.setAccelerator(KeyStroke.getKeyStroke(settingShortcuts[i], InputEvent.CTRL_MASK));
			settingItem.addActionListener(menuListener);
			settingMenu.add(settingItem);
		}
		
		for(int i = 0; i < helpItems.length; i++){
			JMenuItem helpItem = new JMenuItem(helpItems[i]);
			helpItem.setAccelerator(KeyStroke.getKeyStroke(helpShortcuts[i], InputEvent.CTRL_MASK));
			helpItem.addActionListener(menuListener);
			helpMenu.add(helpItem);
		}
		
	}
	
	class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			if(command.equals("退出")){
				int result = JOptionPane.showConfirmDialog(LotteryMenu.this, "确认退出抽奖程序吗？", "确认", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){
					System.exit(0);
				} else {
					return;
				}
			} else if (command.equals("参数")) {
				ParamSettingFrame.getInstance().setVisible(true);
			} else if (command.equals("关于")) {
				AboutFrame.getInstance().setVisible(true);
			} else if (command.equals("显示")) {
				DisplaySettingFrame.getInstance().setVisible(true);
			} else if (command.equals("开始下一次抽奖")) {
				//需要将Frame的list清空
			}
			System.out.println("Menu item [" + event.getActionCommand( ) +"] was pressed.");
		}
		
	}
}
