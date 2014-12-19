package com.lottery.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.lottery.common.StringRsc;


public class XMLUtil {
	
	private Document dom;
	
	public static XMLUtil getXMLUtil(Object obj) throws Exception{
		if(obj instanceof String){
			return new XMLUtil((String)obj);
		} else if(obj instanceof File) {
			return new XMLUtil((File)obj);
		} else if(obj instanceof InputStream) {
			return new XMLUtil((InputStream)obj);
		} else {
			throw new Exception("不支持类型");
		}
	}
	
	
	private XMLUtil(String fileName) throws DocumentException{
		SAXReader reader = new SAXReader();
		dom = reader.read(new File(fileName));
	}
	
	private XMLUtil(File file) throws DocumentException{
		SAXReader reader = new SAXReader();
		dom = reader.read(file);
	}
	
	private XMLUtil(InputStream in) throws DocumentException{
		SAXReader reader = new SAXReader();
		dom = reader.read(in);
	}
	
	/**
	 * ��ȡ�����ļ�ָ���ڵ�Ĳ���
	 * @param elementName
	 * @return ����
	 */
	public String getParam(String elementName){
		Element root = dom.getRootElement();
		return root.element(elementName).getTextTrim();
	}
	
	public void setParam(String elementName, String value) throws IOException{
		Element root = dom.getRootElement();
		root.element(elementName).setText(value);
	}
	
	
	public String getAttribute(String elementName, String attributeName){
		Element root = dom.getRootElement();
		return root.element(elementName).attributeValue(attributeName);
	}
	
	public void setAttribute(String elementName, String attributeName, String attributeValue) throws IOException{
		Element root = dom.getRootElement();
		//root.element(elementName).setAttributeValue(attributeName, attributeValue);
		root.element(elementName).attribute(attributeName).setValue(attributeValue);
		
	}
	
	/**
	 * 将对dom的改变保存到配置文件中
	 */
	public void commit() throws IOException{
		XMLWriter writer = new XMLWriter();
		writer.setOutputStream(new FileOutputStream(new File(StringRsc.CONFIG_PATH)));
		writer.write(dom);
		writer.flush();
		writer.close();
	}

	public Document getDom() {
		return dom;
	}

	public void setDom(Document dom) {
		this.dom = dom;
	}
}
