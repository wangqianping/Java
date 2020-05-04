package reflect;

import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestRefect {


    @Test
    public void reflect() throws Exception {

        Class clazz = Person.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        Person person = (Person) constructor.newInstance();
        person.setName("tom");
        person.age = 25;
        System.out.println(person);

        Constructor constructor2 = clazz.getDeclaredConstructor(String.class, int.class);
        Person jack = (Person) constructor2.newInstance("jack", 23);
        System.out.println(jack);

        Method print = clazz.getMethod("print");
        print.invoke(person);
        Method getAge = clazz.getMethod("getAge");
        int age =(int) getAge.invoke(person);
        System.out.println(age);
        //调用私有方法
        Method show = clazz.getDeclaredMethod("show");
        show.setAccessible(true);
        show.invoke(person);

        Field age1 = clazz.getDeclaredField("age");
        age1.set(person,23);
        System.out.println(person);

    }

    @Test
    public void creatClass() throws ClassNotFoundException {
        //方式一：通过类名
        Class c1 = Person.class;
        //方式二：通过实例对象
        Class c2 = new Person().getClass();
        //方式三:通过路径名
        Class c3 = Class.forName("reflect.Person");
        //方式四：通过类加载器获取
        ClassLoader classLoader = TestRefect.class.getClassLoader();
        Class<?> c4 = classLoader.loadClass("reflect.Person");

        System.out.println(c1==c2);
        System.out.println(c2==c3);
        System.out.println(c3==c4);//表明Class对象在内存中只有一个

    }

    @Test
    public void readPropertis() throws Exception {

        Properties pos = new Properties();

        //方式一：通过流的方式
//        FileInputStream fileInputStream = new FileInputStream("src/reflect/jdbc.properties");

        //方式二：通过类加载器
        ClassLoader classLoader = TestRefect.class.getClassLoader();
        InputStream fileInputStream = classLoader.getResourceAsStream("reflect/jdbc.properties");

        pos.load(fileInputStream);
        String user = pos.getProperty("user");
        String password = pos.getProperty("password");

        System.out.println("user="+user);
        System.out.println("password="+password);


    }

    @Test
    public void getInstance(){
        //通过反射创建运行时对象

    }


}
