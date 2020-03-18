package java_sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnManager {
 public static Connection getConnection() {
        Connection connection = null;
        try (FileInputStream fis = new FileInputStream("src/main/resources/jdbc.properties")
        ){
            Properties properties = new Properties();
            properties.load(fis);
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            /*Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
