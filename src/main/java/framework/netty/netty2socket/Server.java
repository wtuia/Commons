package framework.netty.netty2socket;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

// TCP
class Server {

    private static int port = 7777;
    public static void main(String[] args) {
        System.out.println("server start...");
		try (ServerSocket serverSocket = new ServerSocket(port);
			 Socket socket = serverSocket.accept();
			 InputStream stream = socket.getInputStream();
			 OutputStream outputStream = socket.getOutputStream()){
            byte[] bytes = new byte[1024];
            int len;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int i = 0;
           while (true) {
           	   Thread.sleep(1000);
			   outputStream.write(format.format(System.currentTimeMillis()).getBytes());
			   outputStream.flush();
			   len = stream.read(bytes);
			   System.out.println("接受到客户端的消息："+ new String(bytes, 0, len));
			   if (++i % 4 ==0) {
			   	 Thread.sleep(12000);
			   }
		   }
            // 关闭输出流，不然对端会一直等待
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
