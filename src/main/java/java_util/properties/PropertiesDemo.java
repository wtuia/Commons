package java_util.properties;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.*;

public class PropertiesDemo {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Enumeration e = properties.propertyNames();
        Map<String,String> propertiesMap = new HashMap<>();
        String key, value;
        while (e.hasMoreElements()) {
             key = String.valueOf(e.nextElement());
             value = properties.getProperty(key);
             propertiesMap.put(key, value);
        }
        //
        System.out.println(propertiesMap.size());
        for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    public void initProperties2FileStream() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc.properties"));
        properties.list(System.out);

        System.out.println(properties.getProperty("con.DBMS..url"));
        System.out.println(properties.get("con.DBMS..url"));
    }


    /**
     * 当使用带有默认的属性列表的构造Properties时，
     * 默认的属性列表会初始化Properties内部的一个名为default的Properties对象
     * Properties的getProperty内部调用hashTable个get方法，get方法继承自hashTable,
     * 当通过get获取到对象为null时，则会从default读取, 此时会出现get与getProperty获取不一致的问题
     * @see java.util.Properties
     * @see java.util.Properties#getProperty(String)
     *
     * @see <a href="https://sjsdfg.github.io/effective-java-3rd-chinese/#/notes/18.%20%E7%BB%84%E5%90%88%E4%BC%98%E4%BA%8E%E7%BB%A7%E6%89%BF">
     *     Effctive Java 18</a>
     */
    @Test
    public void getProperty() {
        Properties sys = System.getProperties();
        Properties properties = new Properties(sys);
        System.out.println(properties.getProperty("os.name")); // Windows 10
        System.out.println(properties.get("os.name")); // null
    }

    /**
     */
    @Test
    public void setProperty() {
        Properties properties = System.getProperties();
        //properties.put("os.name", "linux");
        //properties.setProperty("os.name", "linux");
        System.out.println(properties.getProperty("os.name"));
    }

}


