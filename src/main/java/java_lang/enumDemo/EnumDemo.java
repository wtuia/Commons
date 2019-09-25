package java_lang.enumDemo;

import org.junit.Test;

import static java_lang.enumDemo.Operation.*;

public class EnumDemo {

    public static void main(String[] args) {

        int a = 123_456_789;
        double d = 0.123_456_789;

        System.out.println(a);
        System.out.println(d);

    }

    @Test
    public void Demo1() {
        double plus = PLUS.apply(1,2);
        System.out.println(plus);
    }

}
