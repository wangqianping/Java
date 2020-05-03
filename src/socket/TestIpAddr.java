package socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIpAddr {

    public static void main(String[] args) {

        try {
            InetAddress inetAddress1 = InetAddress.getByName("192.168.199.255");
            InetAddress inetAddress2 = InetAddress.getByName("www.baidu.com");
            InetAddress inetAddress3 = InetAddress.getLocalHost();
            System.out.println(inetAddress1);
            System.out.println(inetAddress2);
            System.out.println(inetAddress3);

            //获取域名
            System.out.println(inetAddress2.getHostName());
            //获取IP地址
            System.out.println(inetAddress2.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }



    }


}
