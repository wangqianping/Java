package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Demo1 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char[] chars = line.toCharArray();
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;
        int num6 = 0;
        for (int i = 0; i < chars.length; i++) {
            String val = String.valueOf(chars[i]);
            if ("(".equals(val)) {
                num1++;
            } else if (")".equals(val)) {
                if (num1 - num2 <= 0 || (i > 0 && "[{".contains(String.valueOf(chars[i - 1])))) {
                    System.out.println(0);
                    return;
                }
                num2++;
            } else if ("[".equals(val)) {
                num3++;
            } else if ("]".equals(val)) {
                if (num3 - num4 <= 0 || (i > 0 && "({".contains(String.valueOf(chars[i - 1])))) {
                    System.out.println(0);
                    return;
                }
                num4++;
            } else if ("{".equals(val)) {
                num5++;
            } else if ("}".equals(val)) {
                if (num5 - num6 <= 0 || (i > 0 && "[(".contains(String.valueOf(chars[i - 1])))) {
                    System.out.println(0);
                    return;
                }
                num6++;
            } else {
                //判断是否含有其他字符
                System.out.println(0);
                return;
            }
        }

        //判断是否同类型括号不对称
        if (num1 != num2 || num3 != num4 || num5 != num6) {
            System.out.println(0);
            return;
        }
        List<Integer> integerList = new ArrayList<Integer>();
        String left = "({[";
        //括号的最大嵌套深度
        for (int i = 0; i < chars.length; i++) {
            int result = 1;
            String val1 = String.valueOf(chars[i]);
            if (left.indexOf(val1) != -1) {
                for (int j = i + 1; j < chars.length; j++) {
                    String val2 = String.valueOf(chars[j]);
                    if (left.indexOf(val2) != -1) {
                        result++;
                    } else {
                        integerList.add(result);
                        result=1;
                        continue;
                    }
                }
            }
        }
        integerList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        System.out.println(integerList.get(integerList.size()-1));
    }

}
