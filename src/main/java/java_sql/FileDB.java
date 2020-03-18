package java_sql;

import apache.dom4j.ReadFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class FileDB {

    private Date date;
    //private DateTimeFormatter format = DateTimeFormatter.ofPattern("");
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private Map<String, String> sqlMap;
    private String tableName;

    private FileDB() {
        String fileName = System.getProperty("user.dir") +"/src/main/java/java_sql/sql.xml";
        sqlMap = ReadFile.read(fileName);
        //date = Date.from(Instant.now());
        date = Calendar.getInstance().getTime();
    }

    private void createTable() {
        tableName = "file_" + format.format(date);
        String sql = replaceTableName(sqlMap.get("createTable"), tableName);
        String dropSql = replaceTableName(sqlMap.get("dropTable"), tableName);
        try (Connection connection = ConnManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             PreparedStatement dropPs = connection.prepareStatement(dropSql)){
            dropPs.executeUpdate();
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert() {
        if (tableName == null) {
            tableName = "file_" + format.format(date);
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        String sql = replaceTableName(sqlMap.get("insertFileTable"), tableName);
        try (Connection connection = ConnManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "log.log");
            ps.setTimestamp(2, timestamp);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * {@link PreparedStatement#setString(int, String)} 无法设置表名，该方法用以替换表名
     */
    private static String replaceTableName(String sql, String tableName) {
       return sql.replaceAll("tableName", tableName);
    }

    public static void main(String[] args) {
        FileDB db = new FileDB();
        //db.createTable();
        db.insert();
    }
}
