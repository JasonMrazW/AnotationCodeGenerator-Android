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
 * Activity代码生成器
 * 
 * @author 95
 * 
 */
public class FragmentGenerator extends AbstractGenerator{

	private static final String packagePath = "code\\";

	private static final String surfix = ".java";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "activity_user_personal_center_ui_layout.xml";

		// 解析XML，得到node节点
		List<Node> nodes = LayoutParser.parse(filePath);

		// 根据node节点，生成class类
		String className = "PersonalInfoFragment";
		FragmentGenerator generator=new FragmentGenerator();
		String clazzContent = generator.generate(nodes, className, filePath);

		try {
			FileUtil.writeTxtFile(clazzContent, new File(packagePath
					+ className + surfix));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成类头部，public class ...
	 * @param className
	 * @param fileName
	 * @param sb
	 */
	@Override
	protected void generateClazzHead(String className, String fileName,
			StringBuilder sb) {
		sb.append("@EFragment(R.layout.").append(fileName).append(")")
				.append("\r\n");
		sb.append("public class ").append(className)
				.append(" extends BaseFragment{").append("\r\n	")
				.append("\r\n	");
	}
	

	/**
	 * 子类重写，添加子类自定义内容
	 * @param sb
	 */
	@Override
	public void generateCustom(StringBuilder sb){
		sb.append("@Override\r\n	")
		.append("public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {")
		.append("\r\n	return null;\r\n}\r\n\r\n");
	}
}
