package jvm;


public class Test {

    static int a = 1;

    static {
        a = 2;
        number = 10;
        System.out.println("先执行我");
    }

    static int number = 20;

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(number);
    }

}


