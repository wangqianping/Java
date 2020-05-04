package socket;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * UDP网络编程
 */
public class TestUDP {


    @Test
    public void send() throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket();
        String str = "我是通过UDP发送的";
        DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(),0,str.length(), InetAddress.getByName("127.0.0.1"),9090);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();

    }


    @Test
    public void receive() throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(9090);
        byte[] text = new byte[24];
        DatagramPacket datagramPacket = new DatagramPacket(text,0,text.length);
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData(),0,datagramPacket.getData().length));
        datagramSocket.close();

    }
}
