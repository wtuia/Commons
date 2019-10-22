package java_sql;


import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class sqlDemo {

    public static void main(String[] args) {
        updateId();
    }

    /**
     * 返回自动生成的主键
     */
    private static void updateId() {
        String sql = "insert into user(user) values (?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ){
            ps.setString(1, "1");
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                rs.next();
                int id = rs.getInt(1);
                System.out.println(id);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载数据库驱动的jar即可，而无需手动指定:
     *String driverClass =properties.getProperty("driverClass");
     * Class.forName(driverClass)
     */
    private static Connection getConnection() {
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
