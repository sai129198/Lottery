package com.lottery.util;

import java.awt.Color;

import javax.swing.JOptionPane;

import com.lottery.common.StringRsc;

public class ParamUtil {
	/**
	 * 将参数写到配置文件中
	 * @param min
	 * @param max
	 */
	public static void writeParamSetting(int min, int max){
		try {
			XMLUtil xmlUtil = XMLUtil.getXMLUtil(StringRsc.CONFIG_PATH);
			xmlUtil.setParam("min", min+"");
			xmlUtil.setParam("max", max+"");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "写入配置文件失败，请检查config.xml文件是否丢失。", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void writeColorSetting(String color){
		try {
			XMLUtil xmlUtil = XMLUtil.getXMLUtil(StringRsc.CONFIG_PATH);
			xmlUtil.setParam("color", color);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "写入配置文件失败，请检查config.xml文件是否丢失。", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public static int getMinFromConfig(){
		XMLUtil xmlUtil;
		try {
			xmlUtil = XMLUtil.getXMLUtil(StringRsc.CONFIG_PATH);
			return Integer.parseInt(xmlUtil.getParam("min"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "读取配置文件失败，请检查config.xml文件是否丢失。", "错误", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
	}
	
	public static int getMaxFromConfig(){
		XMLUtil xmlUtil;
		try {
			xmlUtil = XMLUtil.getXMLUtil(StringRsc.CONFIG_PATH);
			return Integer.parseInt(xmlUtil.getParam("max"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "读取配置文件失败，请检查config.xml文件是否丢失。", "错误", JOptionPane.ERROR_MESSAGE);
			return 500;
		}
	}
	
	public static String getColorFromConfig(){
		XMLUtil xmlUtil;
		try {
			xmlUtil = XMLUtil.getXMLUtil(StringRsc.CONFIG_PATH);
			return xmlUtil.getParam("color").toLowerCase();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "读取配置文件失败，请检查config.xml文件是否丢失。", "错误", JOptionPane.ERROR_MESSAGE);
			return "red";
		}
	}
}
