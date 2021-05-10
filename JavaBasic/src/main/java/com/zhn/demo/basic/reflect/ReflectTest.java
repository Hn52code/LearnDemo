package com.zhn.demo.basic.reflect;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReflectTest {

    public static void main(String[] args) {
        String packagePath = "com.zhn.demo.basic";
//        List<Class<?>> list = getClassFromPackage(packagePath, true);
//        for (Class aClass : list) {
//            System.out.println(aClass.getName());
//        }
    }

    /**
     * 获得包下面的所有的class
     *
     * @param packageName package完整名称
     * @param recursive   是否循环子包
     * @return List包含所有class的实例
     */
    public static List<Class<?>> getClassFromPackage(String packageName, boolean recursive) {
        // 包名对应的路径名称
        String packageDirName = packageName.replace('.', '/');
        List<Class<?>> classList = new ArrayList<>();
        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    System.out.println("file类型的扫描");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(packageName, filePath, recursive, classList);
                } else if ("jar".equals(protocol)) {
                    System.out.println("jar类型的扫描");
                    String path = url.getPath();
                    path = path.replace("file:/", "").substring(0, path.indexOf("!"));
                    return getClassFromJarFile(path, packageDirName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }

    /**
     * 在package对应的路径下找到所有的class
     *
     * @param packageName package名称
     * @param filePath    package对应的路径
     * @param recursive   是否查找子package
     */
    private static void findClassInPackageByFile(String packageName, String filePath,
                                                 final boolean recursive, List<Class<?>> classList) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) return;
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(file -> {
            boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
            boolean acceptClass = file.getName().endsWith("class");// 接受class文件
            return acceptDir || acceptClass;
        });
        if (dirFiles == null) return;
        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findClassInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(), recursive, classList);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    classList.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从jar文件中读取指定目录下面的所有的class文件
     *
     * @param jarPath  jar文件存放的位置
     * @param filePath 指定的文件目录
     * @return 所有的的class的对象
     */
    public static List<Class<?>> getClassFromJarFile(String jarPath, String filePath) {
        List<Class<?>> classList = new ArrayList<>();
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<JarEntry> jarEntryList = new ArrayList<>();
        if (jarFile == null) return classList;
        Enumeration<JarEntry> ee = jarFile.entries();
        while (ee.hasMoreElements()) {
            JarEntry entry = ee.nextElement();
            // 过滤我们出满足我们需求的东西
            if (entry.getName().startsWith(filePath) && entry.getName().endsWith(".class")) {
                jarEntryList.add(entry);
            }
        }
        for (JarEntry entry : jarEntryList) {
            String className = entry.getName().replace('/', '.');
            className = className.substring(0, className.length() - 6);
            try {
                classList.add(Thread.currentThread().getContextClassLoader().loadClass(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classList;
    }
}
