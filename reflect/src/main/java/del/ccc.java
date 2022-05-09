package del;

public class ccc {
    public  Integer show  = 10;
    private Integer PRIVATEELEMENT  = 1;
    protected   Integer PROTECTED =  2;

    public ccc() {
    }

    protected ccc(Integer e, String s){

    }

    private ccc(Integer e, String s,Double d){

    }

    public ccc(Integer PRIVATEELEMENT) {
        System.out.println("单个元素的构造器");
    }


    public  void show(){
        System.out.println("show 的公共方法");
    }

    public  void show(String s){
        System.out.println("show 的公共方法--s");
    }


    protected   void show(Integer e){
        System.out.println("show 的重载 保护方法" + e);
    }


    private   void  show(Integer e, String s){
        System.out.println("show 的重载 私有方法" + e + "000" + s);
    }


}
