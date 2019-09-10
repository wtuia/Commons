package java_lang.system;

import java.io.Console;

/**
 * 没有重定向标准输入和输出流，那么其控制台将存在
 * 使用IDE返回null
 */
public class ConsoleDemo {

    public static void main(String[] args) {
        Console cons = System.console();
        if (cons != null) {
            System.out.println("input:");
            // 监听输入
            String str1 = cons.readLine();
            cons.format("%s", str1);
        }

    }
}
