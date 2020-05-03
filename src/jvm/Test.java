package jvm;

/**
 * 演示栈内存的加载顺序
 */
public class Test {

    static int a = 1;

    static {
        a = 2;
        number = 10;
        System.out.println("先执行我");
    }

    static int number = 20;

    public static void main(String[] args) {
        System.out.println(a);//2
        System.out.println(number);//20
    }

}


