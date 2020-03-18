package static_test;

public class StaticClass {

    static {
        System.out.println("静态域加载。。。");
    }

    public static void staticMethod() {
        System.out.println("调用static 方法");
    }
}
