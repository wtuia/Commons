package java_lang.enums.enum_extend;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class Demo {


    @Test
    public void demo() {
        double[] doubles = {1, 2};
        operation(Arrays.asList(ExtendedOperation.values()), doubles[0], doubles[1]);
        System.out.println("----------");
        operation(BasicOperation.class, doubles[0], doubles[1]);
    }

    private void operation(Collection<? extends Operation> operations, double x, double y) {
        for (Operation operation : operations) {
            System.out.printf("%f %s %f = %f%n", x ,  operation, y, operation.apply(x, y));
        }
    }

    private <T extends Enum<T> & Operation> void operation(Class<T> enumType, double x, double y){
        for (Operation operation : enumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x ,  operation, y, operation.apply(x, y));
        }
    }
}
