package java_net.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//TCP
class Client {
    private static String ADD = "127.0.0.1";
    private static int port = 7777;

    public static void main(String[] args) {
        System.out.println("client start");
        try (Socket socket = new Socket(ADD, port);
             OutputStream socketOut = socket.getOutputStream();
             InputStream socketIn = socket.getInputStream()){
            socketOut.write("message".getBytes());
            socketOut.flush();
            // 关闭输出流，不然对端会一直等待
            socket.shutdownOutput();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = socketIn.read(bytes)) != -1) {
                sb.append(new String(bytes));
                System.out.println(sb.toString());
            }
            System.out.println("mess:" + sb);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
