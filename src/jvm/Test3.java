package jvm;

/**
 * 演示堆内存
 * VM参数： -Xms10m -Xmx10m -XX:+PrintGCDetails
 */
public class Test3 {

    public static void main(String[] args) {
        //设置了堆内存后看效果
        System.out.println("-Xmx:MAX_MEMORY = " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
        System.out.println("-Xms:TOTAL_MEMORY = " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");

        //演示OOM
        byte[] bytes = new byte[40*1024*1024];
    }

}
