package java_lang.autocloseable;

public class MySocket1 implements AutoCloseable{

    public MySocket1() {
        throw new NullPointerException();
    }

    @Override
    public void close() throws Exception {
        System.out.println("my socket 1 close!");
    }
}
