package cn.bcaiw.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity代码生成器
 * 
 * @author 95
 * 
 */
public class ActivityGenerator {

	private static final String packagePath = "code\\";
	
	private static final String copyRight="copyRight";

	private static final String surfix = ".java";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "activity_verification_code.xml";

		// 解析XML，得到node节点
		List<Node> nodes = LayoutParser.parse(filePath);

		// 根据node节点，生成class类
		String className = "UserLoginActivity";
		String clazzContent = generate(nodes, className, filePath);

		try {
			FileUtil.writeTxtFile(clazzContent, new File(packagePath+className + surfix));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String generate(List<Node> nodes, String className,
			String fileName) {
		StringBuilder sb = new StringBuilder();
		StringBuilder findBuilder = new StringBuilder();
		StringBuilder clickBuilder = new StringBuilder();
		StringBuilder textChangeBuilder = new StringBuilder();
		// copyright
		String copyright=FileUtil.readTxtFile(new File(copyRight));
		sb.append(copyright).append("\r\n");

		// 导入的包

		// 类名
		sb.append("@EActivity(R.layout.").append(fileName).append(")")
				.append("\r\n");
		sb.append("public class ").append(className)
				.append(" extends BaseActivity{").append("\r\n	")
				.append("\r\n	");

		List<Node> extentionsNodes = createContent(nodes, findBuilder,
				clickBuilder, textChangeBuilder);

		while (extentionsNodes.size() > 0) {
			extentionsNodes = createContent(extentionsNodes, findBuilder,
					clickBuilder, textChangeBuilder);
		}
		// 7.将生成的节点注入到模板中
		sb.append(findBuilder).append(clickBuilder).append(textChangeBuilder);

		// 6.继承的方法
		sb.append("@SupposeBackground\r\n	")
				.append("public void onRequestComplete(String json, String tag) {\r\n	")
				.append(" super.onRequestComplete(json, tag);\r\n	")
				.append("\r\n	}\r\n");

		sb.append("}");

		return sb.toString();
	}

	private static List<Node> createContent(List<Node> nodes,
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

	public static boolean isInclude(String nodeName) {
		return NODE_INCLUDE.equals(nodeName);
	}

	public static boolean canClick(String nodeName) {
		return NODE_BUTTON.equals(nodeName)
				|| NODE_IMAGEBUTTON.equals(nodeName);
	}

	public static boolean canTextChanged(String nodeName) {
		return NODE_EDITTEXT.equals(nodeName);
	}

	public static boolean canCheckedChanged(String nodeName) {
		return NODE_CHECKBOX.equals(nodeName);
	}

	public static final String NODE_IMAGEBUTTON = "ImageButton";

	public static final String NODE_BUTTON = "Button";

	public static final String NODE_EDITTEXT = "EditText";

	public static final String NODE_CHECKBOX = "CheckBox";

	public static final String NODE_INCLUDE = "include";

	public static final String NODE_OTHER = "Other";
}
