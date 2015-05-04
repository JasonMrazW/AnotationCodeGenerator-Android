/*
 * The MIT License (MIT)
 * Copyright (c) 2015 JasonMrazW (https://github.com/JasonMrazW)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cn.bcaiw.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 基类，承载代码生成主流程
 * @author 95
 *
 */
public abstract class AbstractGenerator {

	private static final String copyRight = "copyRight";
	
	protected static final String packagePath = "code\\";
	
	protected static final String surfix = ".java";
	/**
	 * 代码生成
	 * @param nodes
	 * @param className
	 * @param fileName
	 * @return
	 */
	protected String generate(List<Node> nodes, String className,
			String fileName) {
		StringBuilder sb = new StringBuilder();
		StringBuilder findBuilder = new StringBuilder();
		StringBuilder clickBuilder = new StringBuilder();
		StringBuilder textChangeBuilder = new StringBuilder();
		// copyright
		String copyright = FileUtil.readTxtFile(new File(copyRight));
		sb.append(copyright).append("\r\n");

		// 导入的包

		// 类名
		generateClazzHead(className, fileName, sb);

		List<Node> extentionsNodes = createContent(nodes, findBuilder,
				clickBuilder, textChangeBuilder);

		while (extentionsNodes.size() > 0) {
			extentionsNodes = createContent(extentionsNodes, findBuilder,
					clickBuilder, textChangeBuilder);
		}
		// 将生成的节点注入到模板中
		sb.append(findBuilder).append(clickBuilder).append(textChangeBuilder);
		
		//子类实现特殊代码生成
		generateCustom(sb);
		
		//生成afterviews
		sb.append("	@AfterViews\r\n	")
			.append("public void init(){\r\n	//TODO do something\r\n	}\r\n");
		
		
		// 继承的方法
		sb.append("\r\n	@SupposeBackground\r\n	")
				.append("public void onRequestComplete(String json, String tag) {\r\n	")
				.append(" super.onRequestComplete(json, tag);\r\n	")
				.append("\r\n	}\r\n");

		sb.append("}");

		return sb.toString();
	}

	/**
	 * 生成类头部，public class ...
	 * @param className
	 * @param fileName
	 * @param sb
	 */
	protected void generateClazzHead(String className, String fileName,
			StringBuilder sb) {
		sb.append("@EActivity(R.layout.").append(fileName).append(")")
				.append("\r\n");
		sb.append("public class ").append(className)
				.append(" extends BaseActivity{").append("\r\n	")
				.append("\r\n	");
	}
	
	/**
	 * 子类重写，添加子类自定义内容
	 * @param sb
	 */
	public abstract void generateCustom(StringBuilder sb);

	private List<Node> createContent(List<Node> nodes,
			StringBuilder findBuilder, StringBuilder clickBuilder,
			StringBuilder textChangeBuilder) {

		List<Node> extentionNode = new ArrayList<Node>();
		for (Node node : nodes) {
			// 3.控件名
			if (isInclude(node.nodeName)) {// include
				// 解析layout文件
				extentionNode.addAll(LayoutParser.parse(node.nodeLayout
						+ ".xml"));
				continue;
			}

			// viewbyId
			findBuilder.append("@ViewById(R.id.").append(node.nodeId)
					.append(")").append("\r\n	");
			findBuilder.append(node.nodeName).append("	").append("m")
					.append(node.nodeId).append(";").append("\r\n	")
					.append("\r\n	");

			// 4.click事件
			if (canClick(node.nodeName)) {

				clickBuilder.append("@Click(R.id.").append(node.nodeId)
						.append(")").append("\r\n	");
				clickBuilder
						.append(String.format("public void on%sClicked",
								node.nodeId))
						.append("(View view){\r\n	ViewClicker.ondelay(view);\r\n	}")
						.append("\r\n	").append("\r\n	");
			}

			// 5.textWatcher
			if (canTextChanged(node.nodeName)) {
				textChangeBuilder.append("@TextChange(R.id.")
						.append(node.nodeId).append(")").append("\r\n	");
				textChangeBuilder
						.append(String.format("public void onTextChangedOn%s",
								node.nodeId))
						.append("(CharSequence s, TextView hello, int before, int start, int count){\r\n	\r\n	}")
						.append("\r\n	").append("\r\n	");
			}

		}

		return extentionNode;
	}

	public boolean isInclude(String nodeName) {
		return NODE_INCLUDE.equals(nodeName);
	}

	public boolean canClick(String nodeName) {
		return NODE_BUTTON.equals(nodeName)
				|| NODE_IMAGEBUTTON.equals(nodeName);
	}

	public boolean canTextChanged(String nodeName) {
		return NODE_EDITTEXT.equals(nodeName);
	}

	public boolean canCheckedChanged(String nodeName) {
		return NODE_CHECKBOX.equals(nodeName);
	}

	public final String NODE_IMAGEBUTTON = "ImageButton";

	public final String NODE_BUTTON = "Button";

	public final String NODE_EDITTEXT = "EditText";

	public final String NODE_CHECKBOX = "CheckBox";

	public final String NODE_INCLUDE = "include";

	public final String NODE_OTHER = "Other";
}
