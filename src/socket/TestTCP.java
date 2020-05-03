package socket;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTCP {

    @Test
    public void client() {

        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fis = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8989);
            outputStream = socket.getOutputStream();
            fis = new FileInputStream(new File("source/pizza.jpeg"));
            byte[] text = new byte[1024];
            int len;
            while ((len = fis.read(text)) != -1) {
                outputStream.write(text, 0, len);
            }

            //read方法是阻塞的，如果不明确给服务器一个数据发送完毕的指令服务器将一直在等待客服端发送数据
            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            byte[] reply = new byte[1024];
            baos = new ByteArrayOutputStream();
            int len2;
            while ((len2 = inputStream.read(reply)) != -1){
                baos.write(reply,0,len2);
            }
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Test
    public void server() {

        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        FileOutputStream fis = null;
        OutputStream outputStream = null;

        try {
            serverSocket = new ServerSocket(8989);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            fis = new FileOutputStream(new File("source/pizza2.jpeg"));

            byte[] text = new byte[10];
            int len;
            while ((len = inputStream.read(text)) != -1) {
                fis.write(text, 0, len);
            }

            outputStream = socket.getOutputStream();
            outputStream.write("披萨看起来不错".getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
