package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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


}
