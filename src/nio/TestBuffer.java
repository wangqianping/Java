package nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * NIO 中Buffer负责数据的存取
 */
public class TestBuffer {

    @Test
    public void test(){
        String str = "abc";

        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("=====allocate()=======");
        System.out.println(byteBuffer.capacity());//容量
        System.out.println(byteBuffer.limit());//界限，limit之后的不能读写
        System.out.println(byteBuffer.position());//可操作的位置

        //将数据写入缓冲区
        byteBuffer.put(str.getBytes());
        System.out.println("=====put()===");
        System.out.println(byteBuffer.capacity());//容量
        System.out.println(byteBuffer.limit());//界限，limit之后的不能读写
        System.out.println(byteBuffer.position());//可操作的位置

        //读取数据之前要切换到读模式
        byteBuffer.flip();
        System.out.println("====flip()====");
        System.out.println(byteBuffer.capacity());//容量
        System.out.println(byteBuffer.limit());//界限，limit之后的不能读写
        System.out.println(byteBuffer.position());//可操作的位置

        //读取数据
        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);
        System.out.println("====get()====");
        System.out.println(byteBuffer.capacity());//容量
        System.out.println(byteBuffer.limit());//界限，limit之后的不能读写
        System.out.println(byteBuffer.position());//可操作的位置

        //清除缓冲区数据，数据并没有被清除掉，就是逻辑上的一个假清除
        byteBuffer.clear();
        System.out.println("====clear()====");
        System.out.println(byteBuffer.capacity());//容量
        System.out.println(byteBuffer.limit());//界限，limit之后的不能读写
        System.out.println(byteBuffer.position());//可操作的位置


    }

    @Test
    public void test2(){

        String str = "abc";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.mark();//标记此时position的位置
        System.out.println(byteBuffer.position());//可操作的位置
        byteBuffer.put(str.getBytes());
        System.out.println(byteBuffer.position());//可操作的位置
        byteBuffer.reset();//恢复到标记的位置
        System.out.println(byteBuffer.position());

    }



}
