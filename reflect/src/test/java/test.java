import del.ccc;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;

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

        for (int i = 0; i < fields.length; i++)
            System.out.println(fields[i].getName());

        //获取私有单个属性
        Field field1 = clas.getDeclaredField("PRIVATEELEMENT");
        Field field2 = clas.getDeclaredField("PROTECTED");
        //生成宿主
        Object o1 = clas.getConstructor().newInstance();
        //给破解权限
        field1.setAccessible(true);
        field2.setAccessible(true);
        //显示数据
        System.out.println(field1.getName() + " " + field1.get(o1));
        System.out.println(field2.getName() + " " + field2.get(o1));

        //获取所有属性
        Field[] fields1 = clas.getDeclaredFields();
        //给破解权限
        for (Field field3 : fields1) {
            field3.setAccessible(true);
            System.out.println(field3.get(o1));
        }
        //获取 单个公共方法
        Method method = clas.getMethod("show");


        //使用前面的O1宿主，懒了a
        method.invoke(o1);
        System.out.println("---------------------------------");
        //获取所有公共方法
        Method[] methods = clas.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }

        //获取单个保护或者私有方法

        Method method1 = clas.getDeclaredMethod("show", Integer.class);
        Method method2 = clas.getDeclaredMethod("show", Integer.class, String.class);

        //给破解权限
        method1.setAccessible(true);
        method2.setAccessible(true);
        //启动方法
        method1.invoke(o1, Integer.valueOf(9));
        method2.invoke(o1, Integer.valueOf(9), String.valueOf("9"));


        //获取所有的方法
        System.out.println("----------------------------------");
        Method[] methods1 = clas.getDeclaredMethods();
        for (Method m : methods1) {
            m.setAccessible(true);
            System.out.println(m.getName());
        }

        //获取单公共个构造器
        Constructor constructor = clas.getConstructor(Integer.class);
        Constructor[] constructors = clas.getConstructors();

        for (Constructor c : constructors)
            System.out.println(c.getName() + " " + c.getParameterTypes());
        System.out.println("-------------------");

        Constructor constructor1 = clas.getDeclaredConstructor(Integer.class, String.class);

        //破解权限
        constructor1.setAccessible(true);
        //实例化一下
        constructor1.newInstance(null, null);

        System.out.println("-------------");
        Constructor[] constructor2 = clas.getDeclaredConstructors();

        for (Constructor<ccc> c : constructor2) {
            c.setAccessible(true);
            System.out.println(c.getName());
        }

        System.out.println("-----------------");
        //获取 ccc的类加载器
        //ClassLoader classLoader = Class.forName("del.ccc").getClassLoader();
        ClassLoader classLoader = ccc.class.getClassLoader();
        //通过这个类加载器生成对象
        Class<?> aClass = classLoader.loadClass("del.ccc");
        System.out.println(aClass);
        System.out.println("-----------------");
        System.out.println(Integer.class);
        System.out.println(Integer.TYPE);
        System.out.println("-----------------");
        Class c = Integer.TYPE;
        //int.Class=Integer.TYPE
        System.out.println(c.getName());

        System.out.println("-----------------");
        //error
        // Class<Integer[]> c1  = Class.forName("java.lang.Integer[]");
        Class<Integer[]> c1 = Integer[].class;//class [Ljava.lang.Integer;
        System.out.println(c1);

        Class<Integer[][]> c2 = Integer[][].class;//class [[Ljava.lang.Integer;
        System.out.println(c2);
        //二位数组在JAVA里面是 一维数组引用的引用，所以二维数组的大小不是固定的，在静态赋值下
        Integer[][] c3 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8,},
                {9, 10, 11, 12, 13},
                {}
        };

        for (int i = 0; i < c3.length; i++) {
            for (int j = 0; j < c3[i].length; j++) {
                System.out.print("*");
            }
            System.out.println();
        }


        c3 = new Integer[8][15];
        for (int i = 0; i < c3.length; i++)
            for (int j = 0; j < c3[i].length; j++) {
                c3[i][j] = 1;
            }

        for (int i = 0; i < c3.length; i++) {
            for (int j = 0; j < c3[i].length; j++) {
                if (c3[i][j] == 1)
                    System.out.print("*");
            }
            System.out.println();
        }


        Integer[][] c4 = new Integer[][]{//这种方式跟静态赋值一样，获得的是不规则的二维数组
                {Integer.valueOf(1), Integer.valueOf(2)},
                {Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6)},
                {Integer.valueOf(7), Integer.valueOf(8)},
                {}
        };

        System.out.println(c4.length);//4
        System.out.println(c4[0].length);//2
        System.out.println(c4[1].length);//3
        System.out.println(c4[2].length);//2
        System.out.println(c4[3].length);//0

        System.out.println("-----------------------------------");

        Integer[][] c5 = new Integer[8][8];//这种new空间 得到的是规则的二维数组
        System.out.println(c5.length);
        System.out.println(c5[0].length);
        System.out.println(c5[1].length);
        System.out.println(c5[2].length);
        System.out.println(c5[3].length);

        for (int i = 0; i < c5.length; i++) {
            for (int j = 0; j < c5[i].length; j++) {
                System.out.print(c5[i][j]);
            }
            System.out.println();
        }

        System.out.println("---------------");
        //测试动态加载
        //Class c7 = Class.forName("SB");  命名没有 SB这个类

    }

    @Test
    public void test2() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //反射api测试
        Class<cd> c = cd.class;
        //获取全类名
        System.out.println(c.getName());
        //获取简单类名
        System.out.println(c.getSimpleName());
        //获取父类的变量 不行
       /* Field field = c.getField("fa");
        field.setAccessible(true);
        System.out.println(field.getName());*/
        //但是可以通过返回数组 拿到

        //拿到包
        System.out.println(c.getPackage());
        //拿到父类
        System.out.println(c.getSuperclass().getName());
        //拿到接口
        System.out.println(c.getInterfaces().getClass());
        //拿到注解
        System.out.println( c.getAnnotations());

        //返回访问修饰符 对于field 默认0 public 1 private 2 protect 4
        // static 8 final 16
        //返回值是各种权限组合的值

        //通过getModifiers()获取
        Field field = c.getDeclaredField("d");
        field.setAccessible(true);
        System.out.println( field.getModifiers());

        field = c.getField("s");
        System.out.println( field.getModifiers());

        //返回类的属性 所对应的属性的class  Inter a  返回Integer  返回Integer所对应的字节码

        field = c.getField("d");
        System.out.println( field.getType());

        //获取方法的返回值类型 getReturnType
        Method method1 = c.getDeclaredMethod("show");
        method1.setAccessible(true);
        System.out.println( method1.getReturnType().getSimpleName());//lang.Interge class对象 在寻找名字

        //获取方法的访问修饰符  getModifiers
        Method method = c.getDeclaredMethod("show",String.class,int.class,Integer.class);
        System.out.println( method.getModifiers());

        //以数组的方式获取参数
        System.out.println("------------------");
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getParameterizedType());
            System.out.println( parameter.getName());
            System.out.println( parameter.getModifiers());
            System.out.println("------------------");
        }

        System.out.println("-------私有-----------");
        //扔参数类型是class
        Constructor constructor =  c.getDeclaredConstructor(Integer.class);
        constructor.setAccessible(true);
        //实际运行要给对象 默认不能给null.因为形参接受实参不能为null
        //调用构造器后给一个返回一个宿主对象
        Object o  = constructor.newInstance(Integer.valueOf(0));


    }

}

class cdF {
    protected Integer fa;
}

interface te{

}

@Deprecated
class cd extends cdF implements te{
    public static Double d = 9.0;
    protected Integer i;
    public String s;

    protected String show(){return  new String();};
    private  cd(Integer e){
        System.out.println("私有构造器被调用了");
    };

    private  Integer show(String s,int a, Integer b){ return  Integer.valueOf(null);}
}
