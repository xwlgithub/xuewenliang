package itxwl.email;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/9/8 13:24
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        List<String> userList = Arrays.asList(
                "115","167","188","168"
        );
        List<String> userLists = Arrays.asList(
                "115","167","168"
        );
        List<String> ss=new LinkedList<>();
        ss.add("115");
        ss.add("167");
        ss.add("188");
        ss.add("168");
        System.out.println(ss.remove("115"));
        System.out.println(ss.toString());
        System.out.println("------------------");
        List<String> ssss=new LinkedList<>();
        ssss.add("115");
        ssss.add("167");
        ssss.add("168");
        ss.removeAll(ssss);
        System.out.println(ss.toString());
    }
}
