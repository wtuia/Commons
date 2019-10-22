package java_lang.autocloseable;

import org.junit.Test;

/**
 * try-catch-resources 只有实现了AutoCloseable的类才能调用，
 * AutoCloseable是try-catch-resources能够自动释放资源的原因。
 */
public class MySocket implements AutoCloseable{


    public MySocket() {
    }

    public void getValue() {
        throw  new NullPointerException();
    }

    @Override
    public void close() throws Exception {
        System.out.println("socket close!");
    }


    /**
     * 在创建资源时抛出错误，会关闭之前的资源
     */
    @Test
    public void closeTestTryException() {
        try (MySocket socket = new MySocket();
             MySocket1 socket1 = new MySocket1()){
            // balabala
        }catch (Exception e){
            System.out.println("try exception:");
            e.printStackTrace();
        }
    }

    /**
     * 在调用时抛出错误，会关闭之前的资源
     */
    @Test
    public void closeTestTryResourceException() {
        try (MySocket socket = new MySocket()){
            socket.getValue();
            // balabala
        }catch (Exception e){
            System.out.println("try exception:");
            e.printStackTrace();
        }
    }

}

