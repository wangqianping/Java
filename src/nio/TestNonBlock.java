package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

/**
 * 演示TCP
 */
public class TestNonBlock {


    @Test
    public void client() throws IOException {

        //创建一个客户端socket
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //开启非阻塞
        socketChannel.configureBlocking(false);
        //建立缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(new Date().toString().getBytes());
        //向服务端发送
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();


        //关闭通道
        socketChannel.close();
    }


    @Test
    public void server() throws IOException {
        //创建一个服务端socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //开启非阻塞
        serverSocketChannel.configureBlocking(false);
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上并且指定监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //实时监测选择器上的事件
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                //获取准备就绪的事件
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    //如果是接受状态
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置非阻塞并注册到选择器上
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 如果是读状态,则先获取选择器上读就绪的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                //取消选择键selectionKey
                iterator.remove();
            }
        }
    }


}
