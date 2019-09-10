package apache.commons.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Ftp {

    private static final Logger logger = LoggerFactory.getLogger(Ftp.class);
    private static String SERVER = "139.155.83.148";
    private static FTPClient ftpClient;
    private static String USER = "";
    private static String PASS = "";

    public static void main(String[] args) {
        ftpConnect();
        disConnect();
    }

    /**
     * 建立连接
     */
    private static void ftpConnect() {
        int status;
        if (ftpClient == null)
            ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER, 21);
            ftpClient.login(USER, PASS);
            status = ftpClient.getReplyCode();
            logger.info("ftp连接状态{}",  status);
            if ( !FTPReply.isPositiveCompletion(status)) {
                logger.error("FTP连接失败{}", SERVER);
            }else{
                logger.info("FTP连接服成功{}", SERVER);
            }
        }catch (Exception e) {
            logger.error("连接FTP服务器失败{}", SERVER,e);
        }
    }

    /**
     * 断开连接
     */
    private static void disConnect() {
        try {
            ftpClient.logout();
        } catch (IOException e) {
            logger.error("登出失败" + SERVER,e);
        }finally {
            if (ftpClient != null) {
                if (ftpClient.isConnected()){
                    try {
                        logger.info("关闭FTP连接{}" , SERVER);
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        logger.error("关闭FTP连接失败{}", SERVER);
                    }
                }
            }
        }
    }
}
