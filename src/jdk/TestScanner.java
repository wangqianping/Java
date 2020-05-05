package jdk;

import org.junit.Test;

import java.util.Scanner;

/**
 * junit和scanner不兼容
 */
public class TestScanner {


    public static void main(String[] args) {
        System.out.println("please input: ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            System.out.println(str);
        }
    }


    @Test
    public void test(){
        System.out.println("please input: ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            System.out.println(str);
        }

    }


}
