package java_net.datagram_socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// UDP
class Client {

    private static int PORT = 6666;
    private static String ADD = "127.0.0.1";

    public static void main(String[] args) {
        byte[] data = "message".getBytes();
        try (DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(ADD);
            DatagramPacket packet =
                    new DatagramPacket(data, data.length, address, PORT);
            socket.send(packet);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
