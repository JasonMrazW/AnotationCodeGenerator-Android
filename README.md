# AnotationCodeGenerator-Android

*基于jdom，读取layout文件（xml)形式，生成符合androidAnnotaion规范的代码。*

代码包括：

  1. Activity.@EAActivity;
 
  2. Fragment.@Fragment;

  3. 类.@EBean;
 
xml解析规则：

  1. 解析所有XML节点，id字段赋值以后即命中；

  2. 读取的节点数据包括：标签名（控件名）、android:id属性，layout属性。

  3. 支持嵌套xml，支持include标签解析。

  4. 生成的代码中包括以下几种类型：

    + 类描述.包括@EActivity,@EFragment,@EBean
  
    + @viewById
  
    + @Click事件
  
    + @TextChanged事件
 
    + 基于父类访问网络的回调

嘿嘿，可以提高开发效率，不用老是重复复制粘贴。
