package itxwl.email;

import java.util.*;

/**
 * @Auther: 薛
 * @Date: 2020/9/8 13:24
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("zhangsan", "lisi");
        Iterator<String> bidNoIterators = strings.iterator();
        Map<String, String> bidWithNameMaps=new HashMap<>();
        while (bidNoIterators.hasNext()){
            String project = bidNoIterators.next();
            bidWithNameMaps.put(project,project);
        }
    }

}
