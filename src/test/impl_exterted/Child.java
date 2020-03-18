public class Child extends Parent {


    public Child() {
        System.out.println("child constructor");
    }

    private static void staPrint() {
        System.out.println("static print");
    }

    static {
        System.out.println("Child static");
    }

    @Override
    public void sysPrint() {
        System.out.println("Child print");
        super.sysPrint();
    }

    public static void main(String[] args) {
       /* Child child = new Child();
        System.out.println("new Child");
        child.sysPrint();*/
       Child.staPrint();
    }
}
