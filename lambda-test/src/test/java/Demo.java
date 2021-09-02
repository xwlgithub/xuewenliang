import entity.Mach;
import entity.User;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: 薛
 * @Date: 2020/4/21 13:25
 * @Description:
 */
public class Demo {
    List<User> userList = Arrays.asList(
            new User("1", null, 950965.55, "55"),
            new User("2", "李四", 123111.48, "66"),
            new User("3", "王五", 789456.89, "77")
    );

    public static void main(String[] args) {
        List<User> userList = Arrays.asList(
                new User("1", "张三", 950965.55, "55"),
                new User("2", "李四", 123111.48, "66"),
                new User("3", "王五", 789456.89, "77")
        );
        List<Integer> userLists = Arrays.asList(1,4,5,2);
        List<Integer> collect = userLists.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
        List<User> collect1 = userList.stream().sorted(((user, t1) -> user.getMoney().compareTo(t1.getMoney()))).collect(Collectors.toList());
        collect1.stream().forEach(System.out::println);
//        List<User> collect = userList.stream().filter(user -> {
//            user.setMoney(0.0);
//            return true;
//        }).collect(Collectors.toList());
//        System.out.println(collect.toString());
    }

//    public static void main(String[] args) {
//        List<User> userList = Arrays.asList(
//                new User("1", null, 950965.55, "55"),
//                new User("2", "李四", 123111.48, "66"),
//                new User("3", "王五", 789456.89, "77")
//        );
//        DoubleSummaryStatistics ds = userList.stream().mapToDouble(user -> user.getMoney()).summaryStatistics();
//        System.out.println(ds.getAverage()+"平均");
//        System.out.println(ds.getSum()+"总和");
//        System.out.println(ds.getCount()+"总数");
//        System.out.println(ds.getMax()+"最大");
//        System.out.println(ds.getMin()+"最小");
//    }
    @Test
    public void ddd() {
        List<Mach> collect = userList.stream().map(user -> {
            Mach mach = new Mach();
            mach.setId(user.getId());
            mach.setName(user.getName());
            return mach;
        }).collect(Collectors.toList());
    }

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
    @Test
    public void TTTT() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String ss="2020-09-14 13:33:33";
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.DAY_OF_MONTH,-1);
        String format = simpleDateFormat.format(instance.getTime());
        System.out.println(format);
    }

    @Test
    public void TTTTT(){
        List<String> strings = Arrays.asList("zhangsan", "lisi", "wangwu");
        String name="lisi";
        String result = strings.stream().filter(s -> s.equals(name)).collect(Collectors.joining());
        System.out.println(result);
    }

}
