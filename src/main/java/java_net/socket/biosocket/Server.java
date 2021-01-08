package java_net.socket.biosocket;

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
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()){
            byte[] bytes = new byte[1024];
            int len;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           while (true) {
			   while (inputStream.available()  != 0) {
				   len = inputStream.read(bytes);
				   System.out.println("接受到客户端的消息："+ new String(bytes, 0, len));
			   }
			   for (int i = 0; i < 4; i++) {
			   	Thread.sleep(1000);
				   outputStream.write(format.format(System.currentTimeMillis()).getBytes());
				   outputStream.flush();
			   }
			   Thread.sleep(10000);
		   }
            // 关闭输出流，不然对端会一直等待
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
