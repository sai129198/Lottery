package com.lottery.util;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.lottery.common.StringRsc;

public class ResourceUtil {
	public static void setIcon(JFrame frame){
		URL url = frame.getClass().getResource(StringRsc.ICON_PATH);
		System.out.println(url);
		ImageIcon imageIcon = new ImageIcon(url);
		frame.setIconImage(imageIcon.getImage());
	}
}
