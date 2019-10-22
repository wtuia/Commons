package since.since_8.lambda;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class LamdbaClass<E,T,F,G> {

    private E e;
    private T t;

    public void setValue(E e){
        this.e = e;
    }

    public E getValue() {
        return e;
    }
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        LamdbaClass lamdbaClass = new LamdbaClass<>();
        lamdbaClass.setValue("1");
        Class clz = lamdbaClass.getClass();
        Type[] t = clz.getTypeParameters();
        Field[] fields = clz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getName());
            System.out.println(f.get(lamdbaClass).getClass());
            System.out.println(f.getGenericType());
        }
    }
}
