package practice;

import java.util.Arrays;
import java.util.Scanner;

public class Demo2 {

    public static void main(String[] args) {

        //输入字符串
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split(" ");
        String[] strings = new String[0];

        //去空格
        for(int i=0;i<split.length;i++){
            String str = split[i];
            if("".equals(str)){
                continue;
            }else {
                strings = Arrays.copyOf(strings, strings.length + 1);
                strings[strings.length-1]=str;
            }
        }
        split = strings;
        //输入start
        int start = scanner.nextInt();
        //输入end
        int end = scanner.nextInt();
        if (split == null || split.length <= 1 || start < 0 || start >= split.length || end <= start || end >= split.length) {
            System.out.println("EMPTY");
        }
        for (int i = 0; i < start; i++) {
            System.out.print(split[i] + " ");
        }
        String[] copyOfRange = Arrays.copyOfRange(split, start, end+1);
        for(int i=copyOfRange.length-1;i>=0;i--){
            System.out.print(copyOfRange[i]+" ");
        }
        for (int i = end+1; i < split.length; i++) {
            System.out.print(split[i] + " ");
        }
    }


}
