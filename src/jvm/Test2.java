package jvm;

/**
 * 演示获取java虚拟机对象
 */
public class Test2 {


    public static void main(String[] args) {
        //查看JVNM可用的处理器数量
        System.out.println("可用的处理器数量: " + Runtime.getRuntime().availableProcessors());
        //JVM试图使用的最大内训量
        System.out.println("-Xmx:MAX_MEMORY = " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
        //JVM能使用的最大内存
        System.out.println("-Xms:TOTAL_MEMORY = " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");
    }

}
