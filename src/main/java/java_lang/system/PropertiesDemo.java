package java_lang.system;

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
}
