public class Parent {

    public Parent() {
        System.out.println("---Parent constructor");
    }

    private static void staPrint() {
        System.out.println("static print");
    }

    static {
        System.out.println("---Parent static");
    }

    public void sysPrint() {
        System.out.println("---Parent print");
    }

}
