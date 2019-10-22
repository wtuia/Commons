package method_overloading_overiding.overiding;

public class Child extends Parent{

    @Override
    public void setName() {
        this.name = "child";
    }

    public static void main(String[] args) {
        Parent p1 = new Parent();
        p1.setName();
        p1.getName();

        System.out.println("-----重写后--------");

        Parent p2 = new Child();
        p2.setName();
        p2.getName();

        System.out.println("-----匿名内部类重写--------");
        Parent p3 = new Parent(){

            @Override
            public void setName() {
                name = "匿名内部类重写";
            };
        };

        p3.setName();
        p3.getName();

    }
}
