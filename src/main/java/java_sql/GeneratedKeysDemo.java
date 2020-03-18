package java_sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GeneratedKeysDemo {

    public static void main(String[] args) {
        updateId();
    }

    /**
     * 返回自动生成的主键
     */
    private static void updateId() {
        String sql = "insert into user(user) values (?)";
        try (Connection connection = ConnManager.getConnection();
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

}
