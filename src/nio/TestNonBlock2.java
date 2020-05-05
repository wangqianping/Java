package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

public class TestNonBlock2 {


    @Test
    public void send() throws IOException {
        //创建一个socket
        DatagramChannel datagramChannel = DatagramChannel.open();
        //设置非阻塞
        datagramChannel.configureBlocking(false);
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(new Date().toString().getBytes());

        byteBuffer.flip();
        //注意这里用的是send方法
        datagramChannel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 9898));
        byteBuffer.clear();

        datagramChannel.close();

    }

    @Test
    public void receive() throws IOException {

        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(9898));
        datagramChannel.configureBlocking(false);

        //创建选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上并指定监听模式
        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    //注意这里用的是receive方法
                    datagramChannel.receive(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                    byteBuffer.clear();

                }
            }

            iterator.remove();
        }

    }

}
