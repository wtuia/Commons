public class StaticClass {

    private void setInnerValue(int value) {
        StaticInnerClass staticInnerClass = new StaticInnerClass();
        staticInnerClass.setA(value);
    }


    private int getInnerValue() {
        StaticInnerClass staticInnerClass = new StaticInnerClass();
       return staticInnerClass.getA();
    }

    static class StaticInnerClass {
        int a = 10;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    public static void main(String[] args) {
        StaticClass staticClass = new StaticClass();
        staticClass.setInnerValue(20);
        System.out.println(staticClass.getInnerValue());

    }
}
