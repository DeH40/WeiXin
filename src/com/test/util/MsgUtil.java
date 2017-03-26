package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import javax.servlet.http.HttpServletRequest;








import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.test.po.TextMessage;
import com.thoughtworks.xstream.*;

public class MsgUtil {
	
	public static  Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map=new HashMap<String, String>();
		SAXReader reader =new SAXReader();
		
		InputStream ins=request.getInputStream();
		org.dom4j.Document doc=reader.read(ins);
		org.dom4j.Element root=doc.getRootElement();
		List<Element> list=root.elements();	
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xStream=new XStream();
		xStream.alias("xml",textMessage.getClass());
		return xStream.toXML(textMessage);
		
	}
	public static Map<String, String> stringXmlToMap(String xmlMingwen) throws DocumentException{
		Map<String, String> map=new HashMap<String, String>();
		//SAXReader reader=new SAXReader();
		Document doc=DocumentHelper.parseText(xmlMingwen);
		Element rootElement=doc.getRootElement();
		List<Element> list=rootElement.elements();
		for (Element element : list) {
			map.put(element.getName(), element.getText());		
		}
		return map;
	}
	
}
