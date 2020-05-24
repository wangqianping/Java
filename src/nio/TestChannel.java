package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
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

    @Test
    public void test4() throws Exception {
        //分散读取与聚集写入
        FileInputStream fis = new FileInputStream("source/hello.txt");
        FileOutputStream fos = new FileOutputStream("source/hello3.txt");

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();


        ByteBuffer[] byteBuffers = new ByteBuffer[]{ByteBuffer.allocate(10),ByteBuffer.allocate(1024)};
        fisChannel.read(byteBuffers);

        for(ByteBuffer byteBuffer:byteBuffers){
            byteBuffer.flip();
        }

        System.out.println(new String(byteBuffers[0].array(),0,byteBuffers[0].limit()));
        System.out.println(new String(byteBuffers[1].array(),0,byteBuffers[1].limit()));

        //聚集写出
        fosChannel.write(byteBuffers);

        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();

    }

    @Test
    public void test5() throws Exception {
        //字符集，编码与解码
        Charset charset = Charset.forName("GBK");
        //编码器
        CharsetEncoder encoder = charset.newEncoder();
        //解码器
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("你好中国");
        charBuffer.flip();

        //编码
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        for(int i=0;i<8;i++){
            System.out.println(byteBuffer.get());
        }

        //解码
        byteBuffer.flip();
        CharBuffer charBuffer1 = decoder.decode(byteBuffer);
        System.out.println(charBuffer1.toString());


    }


}
