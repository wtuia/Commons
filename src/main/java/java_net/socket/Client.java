package java_net.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

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
			byte[] buff = new byte[22];
			readMess(22, socket, buff);
			System.out.println("mess:"+ new String(buff));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

	private static String readMess(int len, Socket socket, byte[] buff) {
		int readLen, lastLen;
		byte[] lastBuff;
		String alarm = "";
		try {
			readLen = socket.getInputStream().read(buff);
			if (readLen != len) { // 接收到的告警内容与报文的实际长度不符，预测为分包发送，读取剩下的报文
				for (int i = 0; i < 3; i++) {
					lastBuff = new byte[len - readLen];
					lastLen = socket.getInputStream().read(lastBuff);
					if (lastLen == -1) {
						alarm = new String(buff);
						break;
					}
					System.arraycopy(lastBuff, 0, buff, readLen, lastLen);
					if (lastLen == len - readLen) {
						alarm = new String(buff);
						break;
					}else {
						readLen += lastLen;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return alarm;
	}
}
