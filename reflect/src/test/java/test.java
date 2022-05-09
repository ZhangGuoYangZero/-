import del.ccc;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test {

    @Test
    public void test() {
        //无法自动生成cct 必须手动添加cct
        //del.cct cct = new del.cct();
        ccc ccc = new ccc();
        Integer a = ccc.show;
        ccc.show();
    }

    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //获取字节码对象 不知道类名的时候用的方法
        // Class clas = Class.forName("del.ccc");

        //知道类名获取字节码
         Class clas = ccc.class;
        //获得公共public属性
        Field field = clas.getField("show");
        //输出
        // System.out.println(field);public java.lang.Integer del.ccc.show
        //输出内容
        // System.out.println(field.get(clas.getConstructor().newInstance())); 因为没有内容所以输出null
        //设置 内容
        //设置宿主
        Object g = clas.getConstructor().newInstance();
        //读取和写入的宿主要保持相同
            field.set(g, Integer.valueOf(9));
            field.set(g, 9);
            Object o = 9;
            field.set(g, o);
        //再次读取内容
        System.out.println(field.get(g));
        //获取所有的 public属性
        Field[] fields = clas.getFields();

        for (int i =  0 ; i < fields.length ;i++)
            System.out.println(fields[i].getName());

        //获取私有单个属性
        Field field1 = clas.getDeclaredField("PRIVATEELEMENT");
        Field field2 = clas.getDeclaredField("PROTECTED");
        //生成宿主
        Object o1 =  clas.getConstructor().newInstance();
        //给破解权限
        field1.setAccessible(true);
        field2.setAccessible(true);
        //显示数据
        System.out.println( field1.getName() + " " + field1.get(o1));
        System.out.println( field2.getName() + " " + field2.get(o1));

        //获取所有属性
        Field[] fields1 = clas.getDeclaredFields();
        //给破解权限
        for (Field field3 : fields1){
            field3.setAccessible(true);
            System.out.println(field3.get(o1));
        }
        //获取 单个公共方法
        Method method  = clas.getMethod("show");


        //使用前面的O1宿主，懒了a
        method.invoke(o1);
        System.out.println("---------------------------------");
        //获取所有公共方法
        Method[] methods = clas.getMethods();
        for (Method m : methods){
            System.out.println(m.getName());
        }

        //获取单个保护或者私有方法

        Method method1 = clas.getDeclaredMethod("show",Integer.class);
        Method method2 = clas.getDeclaredMethod("show",Integer.class,String.class);

        //给破解权限
        method1.setAccessible(true);
        method2.setAccessible(true);
        //启动方法
        method1.invoke(o1,Integer.valueOf(9));
        method2.invoke(o1,Integer.valueOf(9),String.valueOf("9"));


        //获取所有的方法
        System.out.println("----------------------------------");
        Method[] methods1 = clas.getDeclaredMethods();
        for( Method m : methods1){
             m.setAccessible(true);
            System.out.println(m.getName());
        }

        //获取单公共个构造器
        Constructor constructor = clas.getConstructor(Integer.class);
        Constructor[] constructors = clas.getConstructors();

        for(Constructor c:  constructors)
            System.out.println(c.getName() + " " + c.getParameterTypes());
        System.out.println("-------------------");

        Constructor constructor1 = clas.getDeclaredConstructor(Integer.class,String.class);

        //破解权限
        constructor1.setAccessible(true);
        //实例化一下
        constructor1.newInstance(null,null);

        System.out.println("-------------");
        Constructor[] constructor2 = clas.getDeclaredConstructors();

        for(Constructor<ccc> c: constructor2)
        {
            c.setAccessible(true);
            System.out.println(c.getName());
        }
    }
}
