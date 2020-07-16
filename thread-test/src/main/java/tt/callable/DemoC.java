package tt.callable;

import java.util.concurrent.Callable;

/**
 * @Auther: 薛
 * @Date: 2020/7/16 17:11
 * @Description:
 */
public class DemoC  implements Callable {

    @Override
    public Object call() throws Exception {
        return "张三";
    }

    public static void main(String[] args) throws Exception {
        DemoC demoC = new DemoC();
        System.out.println(demoC.call());
    }
}
