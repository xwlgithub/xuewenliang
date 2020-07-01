package tt.statics;

/**
 * @Auther: 薛
 * @Date: 2020/6/30 14:31
 * @Description:
 */
public class StaticDemo {
    private static  String end,sta;

    static {
        System.out.println("我执行了静态代码块");
        end="张三";
        sta="李四";
    }
    public StaticDemo(){
        System.out.println("我执行构造方法");
        end="7854";
    }

    public static void main(String[] args) {
      //  dd();
        StaticDemo staticDemo = new StaticDemo();
        System.out.println(end);
    }
}
