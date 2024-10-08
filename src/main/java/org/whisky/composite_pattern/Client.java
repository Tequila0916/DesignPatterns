package org.whisky.composite_pattern;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午10:02
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {

        //根节点
        Directory rootDir = new Directory("root");

        //树枝节点
        Directory binDir = new Directory("bin");
        //向bin目录中添加叶子节点
        binDir.add(new File("vi",10000));
        binDir.add(new File("test",20000));

        Directory tmpDir = new Directory("tmp");

        Directory usrDir = new Directory("usr");
        Directory mysqlDir = new Directory("mysql");
        mysqlDir.add(new File("my.cnf",30));
        mysqlDir.add(new File("test.db",25000));
        usrDir.add(mysqlDir);

        rootDir.add(binDir);
        rootDir.add(tmpDir);
        rootDir.add(usrDir);

        rootDir.printList("");
    }
}
