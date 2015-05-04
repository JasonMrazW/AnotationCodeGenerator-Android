package cn.bcaiw.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class LayoutParser {

	private static List<Node> mNodes;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		parse("activity_user_login.xml");

	}

	public static List<Node> parse(String filePath) {
		filePath="xml\\"+filePath;
		File layoutFile = new File(filePath);

		try {
			String content = FileUtil.readTxtFile(layoutFile);
			FileUtil.writeTxtFile(content.replace("android:", ""), layoutFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File layoutFile2 = new File(filePath);
		SAXBuilder builder = new SAXBuilder();
		mNodes = new ArrayList<Node>();
		try {
			Document dc = builder.build(layoutFile2);
			parse(dc);
			return mNodes;
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static void parse(Document dc) {
		Element root = dc.getRootElement();
		parseChildren(root);
	}

	private static final String ID = "id";
	static final String LAYOU = "layout";
	static final String ID_DEF = "@+id/";
	static final String LAYOUT_DEF = "@layout/";

	private static void parseChildren(Element root) {
		String name = root.getName();
		String id = readIdAttr(root);

		if (id != null) {
			Node node = new Node();
			node.nodeName = name;
			node.nodeId = id;
			node.nodeLayout = readLayoutAttr(root);
			mNodes.add(node);
		}
		for (Iterator<Element> subIterator = root.getChildren().iterator(); subIterator
				.hasNext();) {
			Element e = subIterator.next();
			parseChildren(e);
		}
	}

	private static String readIdAttr(Element e) {
		// 读取ID属性
		String id = e.getAttributeValue(ID);
		if (id != null){
			id = id.replace(ID_DEF, "");
			String c=id.substring(0, 1);
			id=id.replaceFirst(c,c.toUpperCase())  ; 
		}
		return id;
	}

	private static String readLayoutAttr(Element e) {
		// 读取ID属性
		String id = e.getAttributeValue(LAYOU);
		if (id != null)
			id = id.replace(LAYOUT_DEF, "");
		return id;
	}

	
}
