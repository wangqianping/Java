package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * NIO 中的Channel：通道，只负责链接，需配合缓冲区完成数据的传输
 *
 * 通道的主要实现类有：
 * FileChannel
 * SocketChannel
 * ServerSocketChannel
 * DatagramChannel
 *
 * 获取通道
 * 1.Java针对支持通道的类提供了getChannel()
 *  FileInputStream/FileOutputStream
 *  RandomAccessFile
 *  Socket
 *  ServerSocket
 *  DatagramSocket
 * 2.JDK1.7中针对各个通道提供了静态方法open()
 * 3.JDK1.7中的Files工具类提供了newByteChannel()
 */
public class TestChannel {

    @Test
    public void test1() throws Exception {

        FileInputStream fis = new FileInputStream("source/pizza.jpeg");
        FileOutputStream fos = new FileOutputStream("source/pizza2.jpeg");

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(fisChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            fosChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        fosChannel.close();
        fisChannel.close();
        fos.close();
        fis.close();
    }

    @Test
    public void test2() throws Exception {
        //这种方式使用的是直接缓冲区
        FileInputStream fis = new FileInputStream("source/pizza.jpeg");
        FileOutputStream fos = new FileOutputStream("source/pizza3.jpeg");

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

//        fisChannel.transferTo(0,fisChannel.size(),fosChannel);
        fosChannel.transferFrom(fisChannel,0,fisChannel.size());

        fosChannel.close();
        fisChannel.close();
        fos.close();
        fis.close();

    }

    @Test
    public void test3() throws IOException {

        FileChannel inChannel = FileChannel.open(Paths.get("source/pizza.jpeg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("source/pizza4.jpeg"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);

        //通过内存映射文件（物理内存）
        MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对内存中的数据进行操作，不用缓冲区
        byte[] dsc = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(dsc);
        outMappedByteBuffer.put(dsc);

        inChannel.close();
        outChannel.close();

    }


}
