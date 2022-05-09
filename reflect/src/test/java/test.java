import del.ccc;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test {

    @Test
    public  void test(){
        //无法自动生成cct 必须手动添加cct
        //del.cct cct = new del.cct();
        ccc ccc = new ccc();
        Integer a = ccc.show;
        ccc.show();
    }

    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            //获取字节码对象
        Class clas = Class.forName("del.ccc");
        //通过字节码对象获取身上的任意属性  --我想获取方法
        Method method = clas.getMethod("show");

        System.out.println(method);//public void del.ccc.show()
        //破解对象
        method.setAccessible(true);
        //生成方法
        //invoke要仍个对象进去
        //因为原版的newInstance()不建议使用，现在是通过字节码对象获取构造器 然后通过构造器获取实例化对象
        //仍这个实例化对象进去，让这个method方法，有宿主可以用
        method.invoke(clas.getDeclaredConstructor().newInstance());

    }
}
