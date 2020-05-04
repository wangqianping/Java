package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestNormal {



    @Test
    public void client() throws IOException {
        //建立一个客户端的通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //获取本地文件
        FileChannel fileChannel = FileChannel.open(Paths.get("source/pizza.jpeg"), StandardOpenOption.READ);

        //建立一个缓冲区
        ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);
        while(fileChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.close();
        fileChannel.close();
    }



    @Test
    public void server() throws IOException {
        //获取一个服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //开启监听
        SocketChannel socketChannel = serverSocketChannel.accept();

        //创建一个文件输出通道
        FileChannel fileChannel = FileChannel.open(Paths.get("source/pizza3.jpeg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);
        //创建一个缓冲区用来接受客户端信息
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //读取客户端信息
        while(socketChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //关闭通道
        fileChannel.close();
        socketChannel.close();
        serverSocketChannel.close();

    }



}
