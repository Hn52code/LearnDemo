package com.zhn.demo.basic.path;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PathTest {
    /**
     * 首先，Java中的getResourceAsStream有以下几种：
     * 1. Class.getResourceAsStream(String path) ： path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从
     * ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
     * 2. Class.getClassLoader.getResourceAsStream(String path) ：默认则是从ClassPath根下获取，path不能以’/'开头，最终是由
     * ClassLoader获取资源。
     * 3. ServletContext. getResourceAsStream(String path)：默认从WebAPP根目录下取资源，Tomcat下path是否以’/'开头无所谓，
     * 当然这和具体的容器实现有关。
     * 4. Jsp下的application内置对象就是上面的ServletContext的一种实现。
     * 其次，getResourceAsStream 用法大致有以下几种：
     * 第一： 要加载的文件和.class文件在同一目录下，例如：com.x.y 下有类me.class ,同时有资源文件myfile.xml
     * 那么，应该有如下代码：
     * me.class.getResourceAsStream("myfile.xml");
     * 第二：在me.class目录的子目录下，例如：com.x.y 下有类me.class ,同时在 com.x.y.file 目录下有资源文件myfile.xml
     * 那么，应该有如下代码：
     * me.class.getResourceAsStream("file/myfile.xml");
     * 第三：不在me.class目录下，也不在子目录下，例如：com.x.y 下有类me.class ,同时在 com.x.file 目录下有资源文件myfile.xml
     * 那么，应该有如下代码：
     * me.class.getResourceAsStream("/com/x/file/myfile.xml");
     * 总结一下，可能只是两种写法
     * 第一：前面有 “   / ”
     * “ / ”代表了工程的根目录，例如工程名叫做myproject，“ / ”代表了myproject
     * me.class.getResourceAsStream("/com/x/file/myfile.xml");
     * 第二：前面没有 “   / ”
     * 代表当前类的目录
     * me.class.getResourceAsStream("myfile.xml");
     * me.class.getResourceAsStream("file/myfile.xml");
     */

    public static void main(String[] args) throws IOException {
        // 第一种：获取类加载的根路径
        File f = new File(PathTest.class.getResource("./").getPath());
        System.out.println(f);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录
        // E:\develop\Workspace\idea\LearnDemo\JavaBasic\target\classes\com\zhn\demo\basic\path
        File f2 = new File(PathTest.class.getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径    E:\develop\Workspace\idea\LearnDemo
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);


        // 第三种：  file:/E:/develop/Workspace/idea/LearnDemo/JavaBasic/target/classes/
        URL xmlpath = PathTest.class.getClassLoader().getResource("");
        System.out.println(xmlpath);


        // 第四种： E:\develop\Workspace\idea\LearnDemo
        System.out.println(System.getProperty("user.dir"));
        /*
         * 结果： C:\Documents and Settings\Administrator\workspace\projectName
         * 获取当前工程路径
         */

        /* C:\Users\Administrator */
        System.out.println(System.getProperty("user.home"));

        // 第五种：  获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));
    }

}
