package com.lottery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Lottery {
	
	private Random random;
	
	private List<Integer> numberList;
	
	public Lottery(){
		this.random = new Random(System.currentTimeMillis());
		this.numberList = new ArrayList<Integer>();
	}
	
	/**
	 * 返回begin到end的随机数（包括end）
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public int nextInt(int begin, int end) throws Exception{
		if(begin < 0 || begin >= end){
			throw new Exception("数字非法");
		}
		
		int num = this.random.nextInt(end+1 - begin);
		
		return num + (begin - 0);
	}
}
