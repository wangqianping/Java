package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * NIO 提供了管道Pipe
 * 用于两个线程之间的单向通信
 */
public class TestPipe {

    @Test
    public void test() throws IOException {
        //创建一个管道
        Pipe pipe = Pipe.open();
        //获取管道的写通道
        Pipe.SinkChannel sink = pipe.sink();
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("你好中国！".getBytes());
        byteBuffer.flip();
        sink.write(byteBuffer);

        //获取管道的读通道
        Pipe.SourceChannel sourceChannel = pipe.source();
        byteBuffer.flip();
        int len = sourceChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),0,len));

        sink.close();
        sourceChannel.close();

    }



}
