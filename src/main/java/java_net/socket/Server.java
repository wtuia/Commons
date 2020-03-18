package java_net.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes));
            }
            System.out.println("mess:" + sb.toString());
            outputStream.write("re message".getBytes());
            outputStream.flush();
            // 关闭输出流，不然对端会一直等待
            socket.shutdownOutput();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
