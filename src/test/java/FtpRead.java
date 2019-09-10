import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.ParseException;

public class FtpRead {

    public static void main(String[] args) throws Exception {
        FTPClient ftp=FtpRead.getFTPClient("139.155.83.148",
                "yj357322","yj", 21);
        System.out.println(ftp.isConnected());
        String result=FtpRead.readFile(ftp,"/home/yj/ftp/PON_INFO_201907.csv");
        System.out.println(result);
        ftp.disconnect();
        }



    /**
     * 获取FTPClient对象
     * 
     * @param ftpHost
     *            FTP主机服务器
     * @param ftpPassword
     *            FTP 登录密码
     * @param ftpUserName
     *            FTP登录用户名
     * @param ftpPort
     *            FTP端口 默认为21
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpPassword,
                                         String ftpUserName, int ftpPort) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
            } else {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setControlEncoding("UTF-8"); // 中文支持
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    /**
  * @param fileName
  * @return function:从服务器上读取指定的文件
  * @throws ParseException
  * @throws IOException
  */
    public static String readFile(FTPClient ftpClient,String fileName) {
        InputStream ins = null;
        StringBuilder builder = null;
        try {
         // 从服务器上读取指定的文件
            System.out.println(fileName);
            ins = ftpClient.retrieveFileStream(fileName);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(ins,"UTF-8"));
            InputStreamReader reader = new InputStreamReader(ins,"UTF-8");
            String line;
            builder = new StringBuilder(150);
            /*reader.read();
            while ((line = reader.read() != null) {
                System.out.println(line);
                builder.append(line);
            }*/
            int c;
            while((c=reader.read())!=-1){
                //if(c!=13&&c!=10){    // \n回车的Ascall码是10 ，\r换行是Ascall码是13
                    System.out.print((char)c);
                //}
            }
            reader.close();
            // 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
            //ftpClient.getReply();
        } catch (IOException e) {
         e.printStackTrace();
        }finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return builder.toString();
    }
}
