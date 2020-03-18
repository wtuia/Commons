package java_net.datagram_socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//UDP
class Server {
    private static int PORT = 6666;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String mess = new String(data);
            System.out.println(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
