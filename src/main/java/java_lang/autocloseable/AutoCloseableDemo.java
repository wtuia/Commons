package java_lang.autocloseable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * try-catch-resources 自动释放资源与 try-catch-finally的比较
 */
public class AutoCloseableDemo {

    public static void main(String[] args) {
       // since 1.7
        try (InputStream in = new FileInputStream("")){
            // balabala
        }catch (Exception e){
            e.printStackTrace();
        }

        // before 1.7
        InputStream in = null;
        try {
            in = new FileInputStream("C:/Users/14244/Desktop/new.txt");
            // balabala
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
