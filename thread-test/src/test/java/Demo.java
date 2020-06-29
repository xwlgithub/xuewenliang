import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tt.AppRunLocation;
import tt.demo1.MyThreadWithCallable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @Auther: 薛
 * @Date: 2020/6/28 14:47
 * @Description:
 */
@SpringBootTest(classes = AppRunLocation.class)
@RunWith(SpringRunner.class)
public class Demo {
    @Autowired
    private ExecutorService executorService;

    @Test
    public void tt() throws Exception{
        List<Future> futures=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MyThreadWithCallable runThread = new MyThreadWithCallable();
            Future<String> submit = executorService.submit(runThread);
            futures.add(submit);
        }
        System.out.println(futures.size()+"长");
            for (int i = 0; i < futures.size(); i++) {
                System.out.println(futures.get(i).get());
        }
    }
}
