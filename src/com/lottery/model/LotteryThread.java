package com.lottery.model;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class LotteryThread extends Thread {
	private int begin;
	
	private int end;
	
	private JLabel label;
	
	private JButton button;
	
	private static LotteryThread singleton;
	
	private List<Integer> numberList;
	
	private LotteryThread (int begin, int end, JLabel label, JButton button, List<Integer> numberList){
		this.begin = begin;
		this.end = end;
		this.label = label;
		this.setButton(button);
		this.numberList = numberList;
	}
	
	public static LotteryThread getInstance(int begin, int end, JLabel label, JButton button, List<Integer> numberList){
		singleton = new LotteryThread(begin, end, label, button, numberList);
		return singleton;
	}
	
	@Override
	public void run() {
		long sleepTime = 0;
		int count = 0;
		int num = 0;
		Lottery lottery = new Lottery();
		do{
			if(this.numberList.size() >= end - begin + 1){
				JOptionPane.showMessageDialog(null, "范围内的数字已经抽完了。");
				break;
			}
			
			try {
				do{
					num = lottery.nextInt(begin, end);
				} while (this.numberList.contains(num));
				label.setText(num+"");
				this.sleep(sleepTime);
				sleepTime += 10;
				count++;
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
				return;
			}
		} while(count<40);
		
		this.numberList.add(num);	//出现过的数字放到列表中
		
		button.setEnabled(true);
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
	/*
	public static void main(String[] args){
		new LotteryThread(1, 500).start();
	}
	*/
}
