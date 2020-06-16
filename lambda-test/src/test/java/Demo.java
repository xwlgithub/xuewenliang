import entity.User;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Auther: 薛
 * @Date: 2020/4/21 13:25
 * @Description:
 */
public class Demo {
    List<User> userList = Arrays.asList(
            new User("1", "张三", 950965.55),
            new User("2", "李四", 123111.48),
            new User("3", "王五", 789456.89)
    );

    @Test
    public void De() {
        List<User> collect = userList.stream().filter(user -> user.getMoney() > 123111.48)
                .sorted((u1, u2) -> u1.getMoney().compareTo(u2.getMoney()))
                .collect(Collectors.toList());
        collect.forEach(System.out::print);
    }

    @Test
    public void TestReduce() {
        List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
        //        Set<String> setList = userList.stream().map(User::getName).collect(Collectors.toSet());
        //        setList.forEach(System.out::println);
        //        //集合基本汇总分析
        DoubleSummaryStatistics DS = userList.stream().mapToDouble(User::getMoney).summaryStatistics();
        System.out.println("平均值" + DS.getAverage() + "最小" + DS.getMin() + "最大" + DS.getMax() + "总和" + DS.getSum());
        //最大值
        User user = userList.stream().collect(Collectors.maxBy((S1, S2) -> S1.getMoney().compareTo(S2.getMoney()))).get();
        System.out.println(user.toString());
    }

    @Test
    @SuppressWarnings("all")
    public void TestStream() {
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> collect = Arrays.stream(integers).map(s -> s * s).collect(Collectors.toList());
        collect.forEach(System.out::println);
        Map<String, User> collect1 = userList.stream().filter(user -> user.getMoney() < 789456.89)
                .sorted((s1, s2) -> s1.getMoney().compareTo(s2.getMoney()))
                .collect(Collectors.toMap(s -> s.getName(), s -> s));

    }

    @Test
    public void TT() {
        Map<String, String> collect = userList.stream().filter(user ->
                user.getMoney() >= 250965.55 ||
                        user.getName().equals("王五")
        ).collect(Collectors.toMap(User::getId, User::getName));
        collect.entrySet().stream().forEach(user -> {
            System.out.println(user.getKey() + "\t\t\t" + user.getValue());
        });
    }

}
